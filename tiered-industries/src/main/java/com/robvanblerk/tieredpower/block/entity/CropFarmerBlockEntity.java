package com.robvanblerk.tieredpower.block.entity;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;

import com.robvanblerk.tieredpower.Config;
import com.robvanblerk.tieredpower.block.MachineBlock;
import com.robvanblerk.tieredpower.menu.CropFarmerMenu;
import com.robvanblerk.tieredpower.registry.ModBlockEntities;
import com.robvanblerk.tieredpower.util.ItemUtil;

/**
 * Tends crops around itself at its own height: harvests ripe crops (wheat, carrots, potatoes, beetroot, nether wart)
 * and replants them, harvests melons and pumpkins, cuts sugar cane above the bottom block, and plants seeds from its
 * input slots on empty farmland / soul sand. Harvest goes to its output slots and is pushed into neighbouring inventories.
 * Slots: 0-2 seeds, 3-11 output, 12-13 upgrades.
 */
public class CropFarmerBlockEntity extends MachineBlockEntity {
	public static final int CAPACITY = 40_000;
	public static final int MAX_INPUT = 2_000;
	public static final int ENERGY_PER_TICK = 20;
	public static final int TICKS_PER_SPOT = 4;
	public static final int SEED_SLOTS = 3, OUTPUT_START = 3, OUTPUT_END = 12;

	private int index; // which spot in the area is next
	private int progress;
	private int harvested;

	private final ContainerData data = new ContainerData() {
		@Override
		public int get(int i) {
			return switch (i) {
				case 0 -> energy.getEnergyStored() & 0xFFFF;
				case 1 -> (energy.getEnergyStored() >>> 16) & 0xFFFF;
				case 2 -> harvested & 0xFFFF;
				case 3 -> energyCost(ENERGY_PER_TICK);
				default -> 0;
			};
		}

		@Override
		public void set(int i, int value) {}

		@Override
		public int getCount() {
			return CropFarmerMenu.DATA_COUNT;
		}
	};

	public CropFarmerBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.CROP_FARMER.get(), pos, state, 14, CAPACITY, MAX_INPUT, 0);
		enableUpgrades(12);
	}

	public static void tick(Level level, BlockPos pos, BlockState state, CropFarmerBlockEntity be) {
		if (!(level instanceof ServerLevel server)) return;
		if (be.preTick(level, pos, state)) return;

		if (level.getGameTime() % 10 == 0) {
			for (int i = OUTPUT_START; i < OUTPUT_END; i++) {
				ItemStack stack = be.items.get(i);
				if (!stack.isEmpty()) be.items.set(i, ItemUtil.pushToNeighbours(level, pos, stack));
			}
		}

		int cost = be.energyCost(ENERGY_PER_TICK);
		boolean hasRoom = be.items.subList(OUTPUT_START, OUTPUT_END).stream().anyMatch(ItemStack::isEmpty);
		boolean working = hasRoom && be.energy.getEnergyStored() >= cost;
		if (working) {
			be.energy.removeInternal(cost);
			be.progress += be.progressStep();
			while (be.progress >= TICKS_PER_SPOT * 100) {
				be.progress -= TICKS_PER_SPOT * 100;
				be.tendNext(server, pos);
			}
			be.setChanged();
		}
		if (state.getValue(MachineBlock.LIT) != working) {
			level.setBlock(pos, state.setValue(MachineBlock.LIT, working), Block.UPDATE_ALL);
		}
	}

	private void tendNext(ServerLevel level, BlockPos origin) {
		int r = Config.get(Config.FARMER_RADIUS);
		int size = r * 2 + 1;
		index = Math.floorMod(index, size * size);
		BlockPos spot = origin.offset(index % size - r, 0, index / size - r);
		index = (index + 1) % (size * size);
		if (spot.equals(origin) || !level.isLoaded(spot)) return;

		BlockState state = level.getBlockState(spot);
		Block block = state.getBlock();

		if (block instanceof CropBlock crop) {
			if (crop.isMaxAge(state)) harvest(level, spot, state, crop.getStateForAge(0));
			else fertilize(level, spot, state, crop);
		} else if (block instanceof NetherWartBlock) {
			if (state.getValue(NetherWartBlock.AGE) >= 3) harvest(level, spot, state, state.setValue(NetherWartBlock.AGE, 0));
		} else if (block == Blocks.MELON || block == Blocks.PUMPKIN) {
			harvest(level, spot, state, Blocks.AIR.defaultBlockState());
		} else if (block == Blocks.SUGAR_CANE) {
			// Leave the bottom block so it regrows; cut everything above it.
			for (BlockPos up = spot.above(); level.getBlockState(up).is(Blocks.SUGAR_CANE); up = up.above()) {
				harvest(level, up, level.getBlockState(up), Blocks.AIR.defaultBlockState());
			}
		} else if (state.isAir()) {
			plant(level, spot);
		}
	}

	/** Fertiliser in a seed slot: grow this unripe crop (as bone meal would), using one. */
	private void fertilize(ServerLevel level, BlockPos spot, BlockState state, CropBlock crop) {
		for (int i = 0; i < SEED_SLOTS; i++) {
			ItemStack s = items.get(i);
			if (!s.is(com.robvanblerk.tieredpower.registry.ModBlocks.FERTILIZER.get())) continue;
			crop.growCrops(level, spot, state);
			level.levelEvent(1505, spot, 0);
			s.shrink(1);
			setChanged();
			return;
		}
	}

	private void harvest(ServerLevel level, BlockPos spot, BlockState state, BlockState replacement) {
		List<ItemStack> drops = Block.getDrops(state, level, spot, null);
		level.levelEvent(2001, spot, Block.getId(state));
		level.setBlock(spot, replacement, Block.UPDATE_ALL);
		for (ItemStack drop : drops) store(drop);
		harvested++;
	}

	/** Plants the first seed (from the seed slots) that can grow here. */
	private void plant(ServerLevel level, BlockPos spot) {
		for (int i = 0; i < SEED_SLOTS; i++) {
			ItemStack seeds = items.get(i);
			if (!(seeds.getItem() instanceof BlockItem blockItem)) continue;
			Block block = blockItem.getBlock();
			if (!(block instanceof CropBlock) && !(block instanceof NetherWartBlock)) continue;
			BlockState planted = block.defaultBlockState();
			if (!planted.canSurvive(level, spot)) continue;
			level.setBlock(spot, planted, Block.UPDATE_ALL);
			seeds.shrink(1);
			return;
		}
	}

	private void store(ItemStack drop) {
		ItemStack remaining = drop;
		// Seeds top up the seed slots first.
		for (int i = 0; i < SEED_SLOTS && !remaining.isEmpty(); i++) remaining = merge(i, remaining);
		for (int i = OUTPUT_START; i < OUTPUT_END && !remaining.isEmpty(); i++) remaining = merge(i, remaining);
	}

	private ItemStack merge(int slot, ItemStack stack) {
		ItemStack existing = items.get(slot);
		if (slot < SEED_SLOTS) {
			if (existing.isEmpty() || !ItemStack.isSameItemSameTags(existing, stack)) return stack; // don't start new seed stacks
		}
		if (existing.isEmpty()) {
			items.set(slot, stack);
			return ItemStack.EMPTY;
		}
		if (!ItemStack.isSameItemSameTags(existing, stack)) return stack;
		int move = Math.min(stack.getCount(), existing.getMaxStackSize() - existing.getCount());
		existing.grow(move);
		stack.shrink(move);
		return stack;
	}

	@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		tag.putInt("index", index);
		tag.putInt("harvested", harvested);
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		index = tag.getInt("index");
		harvested = tag.getInt("harvested");
	}

	// Hoppers: seeds in from the top/sides, harvest out from the bottom.
	@Override
	public int[] getSlotsForFace(Direction side) {
		return side == Direction.DOWN ? new int[]{3, 4, 5, 6, 7, 8, 9, 10, 11} : new int[]{0, 1, 2};
	}

	@Override
	public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction side) {
		return canPlaceItem(slot, stack);
	}

	@Override
	public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {
		return slot >= OUTPUT_START && slot < OUTPUT_END;
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return slot < SEED_SLOTS && (isPlantable(stack) || stack.is(com.robvanblerk.tieredpower.registry.ModBlocks.FERTILIZER.get()));
	}

	public static boolean isPlantable(ItemStack stack) {
		return stack.getItem() instanceof BlockItem b && (b.getBlock() instanceof CropBlock || b.getBlock() instanceof NetherWartBlock);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("block.tieredpower.crop_farmer");
	}

	@Override
	public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
		return new CropFarmerMenu(containerId, inventory, this, data);
	}
}
