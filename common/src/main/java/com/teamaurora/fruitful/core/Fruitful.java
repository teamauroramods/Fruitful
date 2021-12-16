package com.teamaurora.fruitful.core;

import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import com.teamaurora.fruitful.core.registry.FruitfulItems;
import gg.moonflower.pollen.api.platform.Platform;

public class Fruitful {
    public static final String MOD_ID = "fruitful";
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(Fruitful::onClientInit)
            .clientPostInit(Fruitful::onClientPostInit)
            .commonInit(Fruitful::onCommonInit)
            .commonPostInit(Fruitful::onCommonPostInit)
            .build();

    public static void onClientInit() {
    }

    public static void onClientPostInit(Platform.ModSetupContext ctx) {
    }

    public static void onCommonInit() {
        FruitfulBlocks.BLOCKS.register(Fruitful.PLATFORM);
        FruitfulItems.ITEMS.register(Fruitful.PLATFORM);
    }

    public static void onCommonPostInit(Platform.ModSetupContext ctx) {
    }
}
