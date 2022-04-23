package com.teamaurora.fruitful.common.block;

import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;
import java.util.function.Supplier;

/**
 * @author Exoplanetary, Steven
 */
public class FruitLeavesBlock extends LeavesBlock {
    private final LeavesBlock leavesBlock;
    private final Supplier<Item> fruitItem;

    public FruitLeavesBlock(Properties properties, Block leaves, Supplier<Item> fruit) {
        super(properties);
        leavesBlock = (LeavesBlock)leaves;
        fruitItem = fruit;
    }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        popResource(level, blockPos, new ItemStack(fruitItem.get(), 1));
        level.playSound(null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0F, 0.8F + level.random.nextFloat() * 0.4F);
        level.setBlock(blockPos, leavesBlock.defaultBlockState().setValue(LeavesBlock.PERSISTENT, blockState.getValue(LeavesBlock.PERSISTENT)).setValue(LeavesBlock.DISTANCE, blockState.getValue(LeavesBlock.DISTANCE)), 2);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }

    /**
     * Performs a random tick on a block.
     */
    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random) {
        if (serverLevel.getMoonBrightness() <= 0.25 && !blockState.getValue(LeavesBlock.PERSISTENT)) {
            popResource(serverLevel, blockPos, new ItemStack(fruitItem.get(), 1));
            serverLevel.setBlockAndUpdate(blockPos, FruitfulBlocks.BUDDING_OAK_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, false).setValue(LeavesBlock.DISTANCE, blockState.getValue(LeavesBlock.DISTANCE)));
        }
        super.randomTick(blockState, serverLevel, blockPos, random);
    }

    private static BlockState updateDistance(BlockState blockState, LevelAccessor level, BlockPos blockPos) {
        int i = 7;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            mutableBlockPos.setWithOffset(blockPos, direction);
            i = Math.min(i, getDistance(level.getBlockState(mutableBlockPos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return blockState.setValue(DISTANCE, i);
    }

    private static int getDistance(BlockState neighbor) {
        if (BlockTags.LOGS.contains(neighbor.getBlock())) {
            return 0;
        } else {
            return neighbor.getBlock() instanceof LeavesBlock || neighbor.getBlock() instanceof OakBlossomBlock ? neighbor.getValue(DISTANCE) : 7;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return updateDistance(this.defaultBlockState().setValue(PERSISTENT, Boolean.TRUE), blockPlaceContext.getLevel(), blockPlaceContext.getClickedPos());
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random) {
        serverLevel.setBlockAndUpdate(blockPos, updateDistance(blockState, serverLevel, blockPos));
    }
}