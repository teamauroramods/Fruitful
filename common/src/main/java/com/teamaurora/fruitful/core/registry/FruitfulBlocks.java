package com.teamaurora.fruitful.core.registry;

import com.teamaurora.fruitful.common.block.FruitLeavesBlock;
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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

import java.util.function.Supplier;

public class FruitfulBlocks {
    public static final PollinatedRegistry<Block> BLOCKS = PollinatedRegistry.create(Registry.BLOCK, Fruitful.MOD_ID);

    /* Apple Oak Trees */
    public static final Supplier<Block> APPLE_OAK_LEAVES = registerBlock("apple_oak_leaves", () -> new FruitLeavesBlock(Properties.APPLE_OAK_LEAVES, Blocks.OAK_LEAVES, ()-> Items.APPLE), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));
    public static final Supplier<Block> FLOWERING_OAK_LEAVES = registerBlock("flowering_oak_leaves", () -> new LeavesBlock(Properties.APPLE_OAK_LEAVES), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS));

    private static Supplier<Block> registerBlock(String id, Supplier<Block> block, Item.Properties properties) {
        Supplier<Block> register = BLOCKS.register(id, block);
        FruitfulItems.ITEMS.register(id, () -> new BlockItem(register.get(), properties));
        return register;
    }

    public static final class Properties {
        public static final BlockBehaviour.Properties APPLE_OAK_LEAVES = BlockBehaviour.Properties.of(Material.LEAVES, MaterialColor.COLOR_GREEN).noOcclusion().strength(0.2F).randomTicks().sound(SoundType.GRASS).isValidSpawn(Properties::allowsSpawnOnLeaves).isSuffocating(Properties::isntSolid).isViewBlocking(Properties::isntSolid);

        public static boolean allowsSpawnOnLeaves(BlockState state, BlockGetter access, BlockPos pos, EntityType<?> entity) {
            return entity == EntityType.OCELOT || entity == EntityType.PARROT;
        }

        public static boolean isntSolid(BlockState state, BlockGetter reader, BlockPos pos) {
            return false;
        }
    }
}
