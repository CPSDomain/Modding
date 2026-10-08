package com.robvanblerk.tieredpower.stargate;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.GlobalPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;

import com.robvanblerk.tieredpower.energy.ModEnergyStorage;
import com.robvanblerk.tieredpower.registry.ModBlockEntities;

/**
 * Gate Interface: put one within 8 blocks of a Stargate. Set to Send, it takes items, fluids (and gases) and power from
 * pipes and cables; while the gate is open it passes them through to the Gate Interfaces set to Receive at the other
 * end, which push them out into whatever is next to them (or let pipes pull them). Works both ways at once.
 */
public class GateInterfaceBlockEntity extends BlockEntity {
	public static final int SLOTS = 9, TANK = 64_000, ENERGY = 32_000_000;
	public static final int ITEMS_PER_TICK = 32, FLUID_PER_TICK = 8_000, ENERGY_PER_TICK = 8_000_000;

	public final ItemStackHandler items = new ItemStackHandler(SLOTS) {
		@Override protected void onContentsChanged(int slot) { setChanged(); }
	};
	public final FluidTank tank = new FluidTank(TANK) {
		@Override protected void onContentsChanged() { setChanged(); }
	};
	public final ModEnergyStorage energy = new ModEnergyStorage(ENERGY, ENERGY, ENERGY, this::setChanged);

	private LazyOptional<IItemHandler> itemCap = LazyOptional.of(() -> items);
	private LazyOptional<IFluidHandler> fluidCap = LazyOptional.of(() -> tank);
	private LazyOptional<IEnergyStorage> energyCap = LazyOptional.of(() -> energy);

	private @Nullable GlobalPos gate;
	private int ticks;
	/** 0 no gate nearby, 1 gate closed, 2 connected. */
	private int status;

	public GateInterfaceBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.GATE_INTERFACE.get(), pos, state);
	}

	public boolean receiving() {
		return getBlockState().getValue(GateInterfaceBlock.RECEIVE);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, GateInterfaceBlockEntity be) {
		if (!(level instanceof ServerLevel server)) return;
		if (be.ticks++ % 40 == 0) be.findGate(server);
		if (be.receiving()) {
			be.pushOut(server);
			return;
		}
		if (be.gate == null) { be.status = 0; return; }
		GlobalPos far = Wormholes.other(be.gate);
		if (far == null) { be.status = 1; return; }
		be.status = 2;
		ServerLevel there = server.getServer().getLevel(far.dimension());
		if (there == null) return;
		List<GateInterfaceBlockEntity> out = new ArrayList<>();
		for (BlockPos p : Wormholes.receivers(far))
			if (there.isLoaded(p) && there.getBlockEntity(p) instanceof GateInterfaceBlockEntity r && r.receiving()) out.add(r);
		if (!out.isEmpty()) be.send(out);
	}

	/** Re-checks which gate this interface belongs to, and (if receiving) signs up to be sent to. */
	private void findGate(ServerLevel level) {
		StargateRing.Shape s = StargateRing.near(level, worldPosition, StargateDialerBlockEntity.SEARCH);
		GlobalPos g = s == null ? null : GlobalPos.of(level.dimension(), s.master());
		Wormholes.removeReceiver(worldPosition, level.dimension().location().toString());
		gate = g;
		if (g != null && receiving()) Wormholes.addReceiver(g, worldPosition);
		if (receiving()) status = g == null ? 0 : Wormholes.other(g) != null ? 2 : 1;
	}

	/** Called when the mode is switched. */
	public void modeChanged() {
		if (level instanceof ServerLevel s) findGate(s);
	}

	private void send(List<GateInterfaceBlockEntity> out) {
		// items
		int budget = ITEMS_PER_TICK;
		for (int i = 0; i < SLOTS && budget > 0; i++) {
			ItemStack s = items.getStackInSlot(i);
			if (s.isEmpty()) continue;
			for (GateInterfaceBlockEntity r : out) {
				ItemStack try_ = items.extractItem(i, budget, true);
				if (try_.isEmpty()) break;
				ItemStack left = ItemHandlerHelper.insertItemStacked(r.items, try_, false);
				int moved = try_.getCount() - left.getCount();
				if (moved > 0) { items.extractItem(i, moved, false); budget -= moved; }
				if (budget <= 0) break;
			}
		}
		// fluid
		if (!tank.isEmpty()) {
			int left = Math.min(FLUID_PER_TICK, tank.getFluidAmount());
			for (GateInterfaceBlockEntity r : out) {
				if (left <= 0) break;
				int n = r.tank.fill(new FluidStack(tank.getFluid(), left), IFluidHandler.FluidAction.EXECUTE);
				if (n > 0) { tank.drain(n, IFluidHandler.FluidAction.EXECUTE); left -= n; }
			}
		}
		// power
		int e = Math.min(ENERGY_PER_TICK, energy.getEnergyStored());
		for (GateInterfaceBlockEntity r : out) {
			if (e <= 0) break;
			int n = Math.min(e, r.energy.getSpace());
			if (n > 0) { r.energy.addInternal(n); energy.removeInternal(n); e -= n; }
		}
	}

	/** Receiving: hand everything on to the neighbours (but never back into another Gate Interface). */
	private void pushOut(ServerLevel level) {
		for (Direction dir : Direction.values()) {
			BlockPos next = worldPosition.relative(dir);
			if (!level.isLoaded(next)) continue;
			BlockEntity n = level.getBlockEntity(next);
			if (n == null || n instanceof GateInterfaceBlockEntity) continue;
			IItemHandler ih = n.getCapability(ForgeCapabilities.ITEM_HANDLER, dir.getOpposite()).orElse(null);
			if (ih != null) for (int i = 0; i < SLOTS; i++) {
				ItemStack s = items.getStackInSlot(i);
				if (s.isEmpty()) continue;
				ItemStack left = ItemHandlerHelper.insertItemStacked(ih, s.copy(), false);
				if (left.getCount() != s.getCount()) items.setStackInSlot(i, left);
			}
			IFluidHandler fh = n.getCapability(ForgeCapabilities.FLUID_HANDLER, dir.getOpposite()).orElse(null);
			if (fh != null && !tank.isEmpty()) {
				int f = fh.fill(tank.getFluid().copy(), IFluidHandler.FluidAction.EXECUTE);
				if (f > 0) tank.drain(f, IFluidHandler.FluidAction.EXECUTE);
			}
			IEnergyStorage es = n.getCapability(ForgeCapabilities.ENERGY, dir.getOpposite()).orElse(null);
			if (es != null && es.canReceive() && energy.getEnergyStored() > 0) {
				int f = es.receiveEnergy(energy.getEnergyStored(), false);
				if (f > 0) energy.removeInternal(f);
			}
		}
	}

	/** Lines for Jade. */
	public List<String> info() {
		List<String> out = new ArrayList<>();
		out.add(receiving() ? "Receives from the gate's other end" : "Sends to the gate's other end");
		out.add(switch (status) {
			case 0 -> "!No Stargate within 8 blocks";
			case 1 -> "Gate closed - waiting";
			default -> "Connected";
		});
		int n = 0;
		for (int i = 0; i < SLOTS; i++) n += items.getStackInSlot(i).getCount();
		if (n > 0) out.add(n + " items buffered");
		if (!tank.isEmpty()) out.add(String.format("%,d mB %s", tank.getFluidAmount(), tank.getFluid().getDisplayName().getString()));
		if (energy.getEnergyStored() > 0) out.add(String.format("%,d FE", energy.getEnergyStored()));
		return out;
	}

	@Override
	public void setRemoved() {
		if (level != null && !level.isClientSide()) Wormholes.removeReceiver(worldPosition, level.dimension().location().toString());
		super.setRemoved();
	}

	@Override
	public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (cap == ForgeCapabilities.ITEM_HANDLER) return itemCap.cast();
		if (cap == ForgeCapabilities.FLUID_HANDLER) return fluidCap.cast();
		if (cap == ForgeCapabilities.ENERGY) return energyCap.cast();
		return super.getCapability(cap, side);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		itemCap.invalidate();
		fluidCap.invalidate();
		energyCap.invalidate();
	}

	@Override
	public void reviveCaps() {
		super.reviveCaps();
		itemCap = LazyOptional.of(() -> items);
		fluidCap = LazyOptional.of(() -> tank);
		energyCap = LazyOptional.of(() -> energy);
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.put("items", items.serializeNBT());
		tag.put("tank", tank.writeToNBT(new CompoundTag()));
		tag.putInt("energy", energy.getEnergyStored());
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		items.deserializeNBT(tag.getCompound("items"));
		tank.readFromNBT(tag.getCompound("tank"));
		energy.setEnergy(tag.getInt("energy"));
	}
}
