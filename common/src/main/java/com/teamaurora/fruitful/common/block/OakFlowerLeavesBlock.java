package com.teamaurora.fruitful.common.block;

import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;

/**
 * @author Exoplanetary, Steven
 */
public class OakFlowerLeavesBlock extends LeavesBlock {
    public OakFlowerLeavesBlock(Properties properties) {
        super(properties);
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
        if (serverLevel.getMoonBrightness() == 1.0 && !blockState.getValue(LeavesBlock.PERSISTENT)) {
            boolean canBlossom = true;
            for (Direction dir : Direction.values()) {
                if (serverLevel.getBlockState(blockPos.relative(dir)).getBlock() instanceof OakBlossomBlock) canBlossom = false;
            }
            if (canBlossom) {
                serverLevel.setBlockAndUpdate(blockPos, FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, false).setValue(LeavesBlock.DISTANCE, blockState.getValue(LeavesBlock.DISTANCE)).setValue(OakBlossomBlock.POLLINATED, serverLevel.getRandom().nextInt(250) == 0));
            } else if (blockState.getBlock() != FruitfulBlocks.FLOWERING_OAK_LEAVES.get()) {
                serverLevel.setBlockAndUpdate(blockPos, FruitfulBlocks.FLOWERING_OAK_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, false).setValue(LeavesBlock.DISTANCE, blockState.getValue(LeavesBlock.DISTANCE)));
            }
        }

        super.randomTick(blockState, serverLevel, blockPos, random);
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, Random random) {
        serverLevel.setBlockAndUpdate(blockPos, updateDistance(blockState, serverLevel, blockPos));
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
}