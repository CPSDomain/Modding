package com.robvanblerk.tieredpower.stargate;

import org.jetbrains.annotations.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

import com.robvanblerk.tieredpower.registry.ModBlockEntities;

/** Gate Interface: right-click with an empty hand to switch between Send and Receive. */
public class GateInterfaceBlock extends BaseEntityBlock {
	public static final BooleanProperty RECEIVE = BooleanProperty.create("receive");

	public GateInterfaceBlock(Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(RECEIVE, false));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(RECEIVE);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	@SuppressWarnings("deprecation")
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (!player.getItemInHand(hand).isEmpty()) return InteractionResult.PASS;
		if (level.isClientSide()) return InteractionResult.SUCCESS;
		BlockState next = state.cycle(RECEIVE);
		level.setBlock(pos, next, Block.UPDATE_ALL);
		if (level.getBlockEntity(pos) instanceof GateInterfaceBlockEntity be) be.modeChanged();
		level.playSound(null, pos, SoundEvents.COMPARATOR_CLICK, SoundSource.BLOCKS, 0.6f, next.getValue(RECEIVE) ? 0.6f : 0.9f);
		player.displayClientMessage(Component.literal(next.getValue(RECEIVE)
				? "Receive: takes what comes through the gate and pushes it into the blocks next to it"
				: "Send: passes what's put into it through the open gate").withStyle(ChatFormatting.AQUA), true);
		return InteractionResult.CONSUME;
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new GateInterfaceBlockEntity(pos, state);
	}

	@Override
	public <T extends BlockEntity> @Nullable BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
		return level.isClientSide() ? null : createTickerHelper(type, ModBlockEntities.GATE_INTERFACE.get(), GateInterfaceBlockEntity::tick);
	}

	@Override
	@SuppressWarnings("deprecation")
	public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean moving) {
		if (!state.is(newState.getBlock()) && level.getBlockEntity(pos) instanceof GateInterfaceBlockEntity be)
			for (int i = 0; i < be.items.getSlots(); i++)
				Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, be.items.getStackInSlot(i));
		super.onRemove(state, level, pos, newState, moving);
	}
}
