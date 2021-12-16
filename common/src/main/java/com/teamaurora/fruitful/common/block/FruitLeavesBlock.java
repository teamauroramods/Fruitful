package com.teamaurora.fruitful.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.function.Supplier;

public class FruitLeavesBlock extends LeavesBlock {
    private final Block leaves;
    private final Supplier<Item> fruit;

    public FruitLeavesBlock(Properties properties, Block leaves, Supplier<Item> fruit) {
        super(properties);
        this.leaves = leaves;
        this.fruit = fruit;
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        popResource(level, blockPos, new ItemStack(fruit.get(), 1));
        level.playSound(null, blockPos, SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, SoundSource.BLOCKS, 1.0f, 0.8f + level.random.nextFloat() * 0.4f);
        level.setBlock(blockPos, leaves.defaultBlockState().setValue(LeavesBlock.PERSISTENT, blockState.getValue(LeavesBlock.PERSISTENT)).setValue(LeavesBlock.DISTANCE, blockState.getValue(LeavesBlock.DISTANCE)), 2);
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
