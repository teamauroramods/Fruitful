package com.teamaurora.fruitful.core.registry;

import com.teamaurora.fruitful.common.block.FruitLeavesBlock;
import com.teamaurora.fruitful.common.block.OakBlossomBlock;
import com.teamaurora.fruitful.common.block.OakFlowerLeavesBlock;
import com.teamaurora.fruitful.common.block.trees.FloweringOakTreeGrower;
import com.teamaurora.fruitful.common.item.TabInsertBlockItem;
import gg.moonflower.pollen.api.registry.PollinatedBlockRegistry;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Function;
import java.util.function.Supplier;

public class FruitfulBlocks {
    public static final PollinatedBlockRegistry BLOCKS = PollinatedRegistry.createBlock(FruitfulItems.ITEMS);

    /* Apple Oak Trees */
    public static final Supplier<Block> FLOWERING_OAK_LEAVES = BLOCKS.registerWithItem("flowering_oak_leaves", () -> new OakFlowerLeavesBlock(Properties.FLOWERING_LEAVES), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<Block> BUDDING_OAK_LEAVES = BLOCKS.registerWithItem("budding_oak_leaves", () -> new OakFlowerLeavesBlock(Properties.FLOWERING_LEAVES), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<Block> BLOSSOMING_OAK_LEAVES = BLOCKS.registerWithItem("blossoming_oak_leaves", () -> new OakBlossomBlock(Properties.FLOWERING_LEAVES), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<Block> APPLE_OAK_LEAVES = BLOCKS.registerWithItem("apple_oak_leaves", () -> new FruitLeavesBlock(Properties.FLOWERING_LEAVES, BUDDING_OAK_LEAVES.get(), ()-> Items.APPLE), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    public static final Supplier<Block> FLOWERING_OAK_SAPLING = BLOCKS.registerWithItem("flowering_oak_sapling", () -> new SaplingBlock(new FloweringOakTreeGrower(), Properties.FLOWERING_OAK_SAPLING), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<Block> POTTED_FLOWERING_OAK_SAPLING = BLOCKS.register("potted_flowering_oak_sapling", createFlowerPot(FLOWERING_OAK_SAPLING));

    private static Supplier<Block> createFlowerPot(Supplier<Block> block) {
        return () -> new FlowerPotBlock(block.get(), Properties.POTTED_PLANT);
    }

    private static Function<Block, Item> followItem(Item insertAfter, Item.Properties properties) {
        return object -> new TabInsertBlockItem(insertAfter, object, properties);
    }

    public static final class Properties {
        public static final BlockBehaviour.Properties FLOWERING_LEAVES = BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.GRASS).noOcclusion().strength(0.2F).randomTicks().sound(SoundType.CROP).isValidSpawn(Properties::allowsSpawnOnLeaves).isSuffocating(Properties::isNotSolid).isViewBlocking(Properties::isNotSolid);
        public static final BlockBehaviour.Properties FLOWERING_OAK_SAPLING = BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING);
        public static final BlockBehaviour.Properties POTTED_PLANT = BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM);

        public static boolean allowsSpawnOnLeaves(BlockState state, BlockGetter access, BlockPos pos, EntityType<?> entity) {
            return entity == EntityType.OCELOT || entity == EntityType.PARROT;
        }

        public static boolean isNotSolid(BlockState state, BlockGetter reader, BlockPos pos) {
            return false;
        }
    }
}
