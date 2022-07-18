package com.teamaurora.fruitful.core.other;

import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import gg.moonflower.pollen.api.registry.content.CompostablesRegistry;
import gg.moonflower.pollen.api.registry.content.FlammabilityRegistry;
import net.minecraft.world.level.block.ComposterBlock;

public final class FruitfulData {

    private FruitfulData() {
    }

    public static void init() {
        registerCompostables();
        registerFlammables();
    }

    public static void registerCompostables() {
        CompostablesRegistry.register(FruitfulBlocks.APPLE_OAK_LEAVES.get(), 0.95f);
        CompostablesRegistry.register(FruitfulBlocks.FLOWERING_OAK_LEAVES.get(), 0.3f);
        CompostablesRegistry.register(FruitfulBlocks.BUDDING_OAK_LEAVES.get(), 0.3f);
        CompostablesRegistry.register(FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get(), 0.3f);
        CompostablesRegistry.register(FruitfulBlocks.FLOWERING_OAK_SAPLING.get(), 0.3f);
    }

    public static void registerFlammables() {
        FlammabilityRegistry.register(FruitfulBlocks.APPLE_OAK_LEAVES.get(), 30, 60);
        FlammabilityRegistry.register(FruitfulBlocks.FLOWERING_OAK_LEAVES.get(), 30, 60);
        FlammabilityRegistry.register(FruitfulBlocks.BUDDING_OAK_LEAVES.get(), 30, 60);
        FlammabilityRegistry.register(FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get(), 30, 60);
    }
}
