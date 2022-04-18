package com.teamaurora.fruitful.common.block;

import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.server.ServerWorld;

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
                serverLevel.setBlockState(blockPos, FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, false).setValue(LeavesBlock.DISTANCE, blockState.getValue(LeavesBlock.DISTANCE)).setValue(OakBlossomBlock.POLLINATED, serverLevel.getRandom().nextInt(250) == 0));
            } else if (state.getBlock() != FruitfulBlocks.FLOWERING_OAK_LEAVES.get()) {
                serverLevel.setBlockState(blockPos, FruitfulBlocks.FLOWERING_OAK_LEAVES.get().defaultBlockState().setValue(LeavesBlock.PERSISTENT, false).setValue(LeavesBlock.DISTANCE, blockState.getValue(LeavesBlock.DISTANCE)));
            }
        }

        super.randomTick(blockState, serverLevel, blockPos, random);
    }

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);
    }

    private static BlockState updateDistance(BlockState state, IWorld worldIn, BlockPos pos) {
        int i = 7;
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(Direction direction : Direction.values()) {
            blockpos$mutable.setAndMove(pos, direction);
            i = Math.min(i, getDistance(worldIn.getBlockState(blockpos$mutable)) + 1);
            if (i == 1) {
                break;
            }
        }

        return state.with(DISTANCE, i);
    }

    private static int getDistance(BlockState neighbor) {
        if (BlockTags.LOGS.contains(neighbor.getBlock())) {
            return 0;
        } else {
            return neighbor.getBlock() instanceof LeavesBlock || neighbor.getBlock() instanceof OakBlossomBlock ? neighbor.get(DISTANCE) : 7;
        }
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return updateDistance(this.getDefaultState().with(PERSISTENT, Boolean.TRUE), context.getWorld(), context.getPos());
    }
}