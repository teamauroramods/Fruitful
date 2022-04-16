package com.teamaurora.fruitful.common.block;

import com.minecraftabnormals.abnormals_core.common.blocks.wood.AbnormalsLeavesBlock;
import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class OakFlowerLeavesBlock extends AbnormalsLeavesBlock {
    public OakFlowerLeavesBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return true;
    }

    /**
     * Performs a random tick on a block.
     */
    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (worldIn.getMoonFactor() == 1.0 && !state.get(LeavesBlock.PERSISTENT)) {
            boolean canBlossom = true;
            for (Direction dir : Direction.values()) {
                if (worldIn.getBlockState(pos.offset(dir)).getBlock() instanceof OakBlossomBlock) canBlossom = false;
            }
            if (canBlossom) {
                worldIn.setBlockState(pos, FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get().getDefaultState().with(LeavesBlock.PERSISTENT, false).with(LeavesBlock.DISTANCE, state.get(LeavesBlock.DISTANCE)).with(OakBlossomBlock.POLLINATED, worldIn.getRandom().nextInt(250) == 0));
            } else if (state.getBlock() != FruitfulBlocks.FLOWERING_OAK_LEAVES.get()) {
                worldIn.setBlockState(pos, FruitfulBlocks.FLOWERING_OAK_LEAVES.get().getDefaultState().with(LeavesBlock.PERSISTENT, false).with(LeavesBlock.DISTANCE, state.get(LeavesBlock.DISTANCE)));
            }
        }

        super.randomTick(state, worldIn, pos, random);
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

        return state.with(DISTANCE, Integer.valueOf(i));
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