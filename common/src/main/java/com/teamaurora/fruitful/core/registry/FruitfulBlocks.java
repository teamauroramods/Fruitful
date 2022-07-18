package com.teamaurora.fruitful.core.registry;

import com.teamaurora.fruitful.common.block.FruitLeavesBlock;
import com.teamaurora.fruitful.common.block.LeafCarpetBlock;
import com.teamaurora.fruitful.common.block.OakBlossomBlock;
import com.teamaurora.fruitful.common.block.OakFlowerLeavesBlock;
import com.teamaurora.fruitful.common.block.trees.FloweringOakTree;
import com.teamaurora.fruitful.core.Fruitful;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class FruitfulBlocks {
    public static final PollinatedRegistry<Block> BLOCKS = PollinatedRegistry.create(Registry.BLOCK, Fruitful.MOD_ID);

    /* Apple Oak Trees */
    public static final Supplier<Block> FLOWERING_OAK_LEAVES = registerBlock("flowering_oak_leaves", ()-> new OakFlowerLeavesBlock(Properties.FLOWERING_OAK_LEAVES), CreativeModeTab.TAB_DECORATIONS);
    public static final Supplier<Block> BUDDING_OAK_LEAVES = registerBlock("budding_oak_leaves", ()-> new OakFlowerLeavesBlock(Properties.FLOWERING_OAK_LEAVES), CreativeModeTab.TAB_DECORATIONS);
    public static final Supplier<Block> BLOSSOMING_OAK_LEAVES = registerBlock("blossoming_oak_leaves", ()-> new OakBlossomBlock(Properties.FLOWERING_OAK_LEAVES), CreativeModeTab.TAB_DECORATIONS);
    public static final Supplier<Block> APPLE_OAK_LEAVES = registerBlock("apple_oak_leaves", ()-> new FruitLeavesBlock(Properties.FLOWERING_OAK_LEAVES, BUDDING_OAK_LEAVES.get(), ()-> Items.APPLE), CreativeModeTab.TAB_DECORATIONS);

    public static final Supplier<Block> FLOWERING_OAK_SAPLING = registerBlock("flowering_oak_sapling", ()-> new SaplingBlock(new FloweringOakTree(), Properties.FLOWERING_OAK_SAPLING), CreativeModeTab.TAB_DECORATIONS);
    public static final Supplier<Block> POTTED_FLOWERING_OAK_SAPLING = registerPotted("potted_flowering_oak_sapling", () -> new FlowerPotBlock(FLOWERING_OAK_SAPLING.get(), BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM)));

    private static Supplier<Block> registerBlock(String id, Supplier<Block> block, CreativeModeTab tab) {
        Supplier<Block> register = BLOCKS.register(id, block);
        FruitfulItems.ITEMS.register(id, () -> new BlockItem(register.get(), new Item.Properties().tab(tab)));
        return register;
    }

    private static Supplier<Block> registerPotted(String id, Supplier<Block> block) {
        return BLOCKS.register(id, () -> new FlowerPotBlock(block.get(), Properties.POTTED_PLANT));
    }

    public static final class Properties {
        public static final BlockBehaviour.Properties APPLE_OAK_LEAVES = BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.GRASS).noOcclusion().strength(0.2F).randomTicks().sound(SoundType.GRASS).isValidSpawn(Properties::allowsSpawnOnLeaves).isSuffocating(Properties::isNotSolid).isViewBlocking(Properties::isNotSolid);
        public static final BlockBehaviour.Properties FLOWERING_OAK_LEAVES = BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.GRASS).noOcclusion().strength(0.2F).randomTicks().sound(SoundType.CROP).isValidSpawn(FruitfulBlocks.Properties::allowsSpawnOnLeaves).isSuffocating(FruitfulBlocks.Properties::isNotSolid).isViewBlocking(FruitfulBlocks.Properties::isNotSolid);
        public static final BlockBehaviour.Properties FLOWERING_OAK_CARPET = BlockBehaviour.Properties.of(Material.WOOL, MaterialColor.GRASS).strength(0.0F).sound(SoundType.CROP).noOcclusion();
        public static final BlockBehaviour.Properties FLOWERING_OAK_SAPLING = Block.Properties.of(Material.PLANT, MaterialColor.GRASS).noCollission().randomTicks().strength(0.0F).sound(SoundType.CROP);
        public static final BlockBehaviour.Properties POTTED_PLANT = BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM);

        public static boolean allowsSpawnOnLeaves(BlockState state, BlockGetter access, BlockPos pos, EntityType<?> entity) {
            return entity == EntityType.OCELOT || entity == EntityType.PARROT;
        }

        public static boolean isNotSolid(BlockState state, BlockGetter reader, BlockPos pos) {
            return false;
        }
    }
}
