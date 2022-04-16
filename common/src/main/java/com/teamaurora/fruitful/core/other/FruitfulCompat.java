package com.teamaurora.fruitful.core.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.biome.BiomeColors;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class FruitfulCompat {

    public static void registerCompostables() {
        DataUtil.registerCompostable(FruitfulBlocks.APPLE_OAK_LEAVES.get(), 0.95f);
        DataUtil.registerCompostable(FruitfulBlocks.FLOWERING_OAK_LEAVES.get(), 0.3f);
        DataUtil.registerCompostable(FruitfulBlocks.BUDDING_OAK_LEAVES.get(), 0.3f);
        DataUtil.registerCompostable(FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get(), 0.3f);

        DataUtil.registerCompostable(FruitfulBlocks.APPLE_OAK_LEAF_CARPET.get(), 0.95f);
        DataUtil.registerCompostable(FruitfulBlocks.FLOWERING_OAK_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(FruitfulBlocks.BUDDING_OAK_LEAF_CARPET.get(), 0.3f);
        DataUtil.registerCompostable(FruitfulBlocks.BLOSSOMING_OAK_LEAF_CARPET.get(), 0.3f);

        DataUtil.registerCompostable(FruitfulBlocks.FLOWERING_OAK_SAPLING.get(), 0.3f);
    }

    public static void registerFlammables() {
        DataUtil.registerFlammable(FruitfulBlocks.APPLE_OAK_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(FruitfulBlocks.FLOWERING_OAK_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(FruitfulBlocks.BUDDING_OAK_LEAVES.get(), 30, 60);
        DataUtil.registerFlammable(FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get(), 30, 60);

        DataUtil.registerFlammable(FruitfulBlocks.APPLE_OAK_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(FruitfulBlocks.FLOWERING_OAK_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(FruitfulBlocks.BUDDING_OAK_LEAF_CARPET.get(), 30, 60);
        DataUtil.registerFlammable(FruitfulBlocks.BLOSSOMING_OAK_LEAF_CARPET.get(), 30, 60);

        DataUtil.registerFlammable(FruitfulBlocks.APPLE_OAK_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(FruitfulBlocks.FLOWERING_OAK_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(FruitfulBlocks.BUDDING_OAK_HEDGE.get(), 5, 20);
        DataUtil.registerFlammable(FruitfulBlocks.BLOSSOMING_OAK_HEDGE.get(), 5, 20);
    }
}