package com.teamaurora.fruitful.common.block;

import com.minecraftabnormals.abnormals_core.common.blocks.wood.AbnormalsLeavesBlock;
import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Supplier;

public class FruitLeavesBlock extends AbnormalsLeavesBlock {
    private final LeavesBlock leavesBlock;
    private final Supplier<Item> fruitItem;

    public FruitLeavesBlock(Properties properties, LeavesBlock leaves, Supplier<Item> fruit) {
        super(properties);
        leavesBlock = leaves;
        fruitItem = fruit;
    }

    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        spawnAsEntity(worldIn, pos, new ItemStack(fruitItem.get(), 1));
        worldIn.playSound(null, pos, SoundEvents.ITEM_SWEET_BERRIES_PICK_FROM_BUSH, SoundCategory.BLOCKS, 1.0F, 0.8F + worldIn.rand.nextFloat() * 0.4F);
        worldIn.setBlockState(pos, leavesBlock.getDefaultState().with(LeavesBlock.PERSISTENT, state.get(LeavesBlock.PERSISTENT)).with(LeavesBlock.DISTANCE, state.get(LeavesBlock.DISTANCE)), 2);
        return ActionResultType.func_233537_a_(worldIn.isRemote);
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
        if (worldIn.getMoonFactor() <= 0.25 && !state.get(LeavesBlock.PERSISTENT)) {
            spawnAsEntity(worldIn, pos, new ItemStack(fruitItem.get(), 1));
            worldIn.setBlockState(pos, FruitfulBlocks.BUDDING_OAK_LEAVES.get().getDefaultState().with(LeavesBlock.PERSISTENT, false).with(LeavesBlock.DISTANCE, state.get(LeavesBlock.DISTANCE)));
        }
        super.randomTick(state, worldIn, pos, random);
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

    @Override
    public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        worldIn.setBlockState(pos, updateDistance(state, worldIn, pos), 3);
    }
}