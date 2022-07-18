package com.teamaurora.fruitful.core;

import com.teamaurora.fruitful.core.other.FruitfulData;
import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import com.teamaurora.fruitful.core.registry.FruitfulFeatures;
import com.teamaurora.fruitful.core.registry.FruitfulItems;
import gg.moonflower.pollen.api.config.ConfigManager;
import gg.moonflower.pollen.api.config.PollinatedConfigType;
import gg.moonflower.pollen.api.event.events.entity.ModifyTradesEvents;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.client.ColorRegistry;
import gg.moonflower.pollen.api.registry.client.RenderTypeRegistry;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;

//TODO: Events
public class Fruitful {
    public static final String MOD_ID = "fruitful";
    public static final FruitfulCommonConfig CONFIG = ConfigManager.register(MOD_ID, PollinatedConfigType.COMMON, FruitfulCommonConfig::new);
    public static final Platform PLATFORM = Platform.builder(MOD_ID)
            .clientInit(() -> Fruitful::onClientInit)
            .clientPostInit(() -> Fruitful::onClientPostInit)
            .commonInit(Fruitful::onCommonInit)
            .commonPostInit(Fruitful::onCommonPostInit)
            .build();

    public static void onClientInit() {
        ColorRegistry.register((state, level, pos, tintIndex) -> level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(),
                FruitfulBlocks.BUDDING_OAK_LEAVES,
                FruitfulBlocks.FLOWERING_OAK_LEAVES,
                FruitfulBlocks.BLOSSOMING_OAK_LEAVES,
                FruitfulBlocks.APPLE_OAK_LEAVES
        );
        ColorRegistry.register((stack, tintIndex) -> FoliageColor.getDefaultColor(),
                FruitfulBlocks.BUDDING_OAK_LEAVES,
                FruitfulBlocks.FLOWERING_OAK_LEAVES,
                FruitfulBlocks.BLOSSOMING_OAK_LEAVES,
                FruitfulBlocks.APPLE_OAK_LEAVES
        );
    }

    public static void onClientPostInit(Platform.ModSetupContext ctx) {
        ctx.enqueueWork(() -> {
            RenderTypeRegistry.register(FruitfulBlocks.APPLE_OAK_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.FLOWERING_OAK_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.BUDDING_OAK_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.FLOWERING_OAK_SAPLING.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.POTTED_FLOWERING_OAK_SAPLING.get(), RenderType.cutoutMipped());
        });
    }

    public static void onCommonInit() {
        FruitfulBlocks.BLOCKS.register(PLATFORM);
        FruitfulItems.ITEMS.register(PLATFORM);
        FruitfulFeatures.FEATURES.register(PLATFORM);
        FruitfulFeatures.CONFIGURED_FEATURES.register(PLATFORM);
        FruitfulFeatures.TREE_DECORATOR_TYPES.register(PLATFORM);
        FruitfulFeatures.PLACED_FEATURES.register(PLATFORM);

        ModifyTradesEvents.WANDERER.register((event) -> event.getGeneric().add(FruitfulBlocks.FLOWERING_OAK_SAPLING, 5, 1, 8, 1, 0.15F, true));
    }

    public static void onCommonPostInit(Platform.ModSetupContext ctx) {
       ctx.enqueueWork(FruitfulData::init);
    }
}