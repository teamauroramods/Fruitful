package com.teamaurora.fruitful.core;

import com.teamaurora.fruitful.core.other.FruitfulData;
import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import com.teamaurora.fruitful.core.registry.FruitfulEffects;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.FoliageColor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TODO: Events
public class Fruitful {
    public static final String MOD_ID = "fruitful";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
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
        RenderTypeRegistry.register(FruitfulBlocks.APPLE_OAK_LEAVES.get(), RenderType.cutoutMipped());
        RenderTypeRegistry.register(FruitfulBlocks.FLOWERING_OAK_LEAVES.get(), RenderType.cutoutMipped());
        RenderTypeRegistry.register(FruitfulBlocks.BUDDING_OAK_LEAVES.get(), RenderType.cutoutMipped());
        RenderTypeRegistry.register(FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get(), RenderType.cutoutMipped());
        RenderTypeRegistry.register(FruitfulBlocks.FLOWERING_OAK_SAPLING.get(), RenderType.cutoutMipped());
        RenderTypeRegistry.register(FruitfulBlocks.POTTED_FLOWERING_OAK_SAPLING.get(), RenderType.cutoutMipped());
    }

    public static void onCommonInit() {
        FruitfulBlocks.load(PLATFORM);
        FruitfulItems.load(PLATFORM);
        FruitfulEffects.load(PLATFORM);
        FruitfulFeatures.Configured.load(PLATFORM);
        ModifyTradesEvents.WANDERER.register((event) -> event.getGeneric().add(FruitfulBlocks.FLOWERING_OAK_SAPLING, 5, 1, 8, 1, 0.15F, true));
    }

    public static void onCommonPostInit(Platform.ModSetupContext ctx) {
       ctx.enqueueWork(FruitfulData::init);
    }

    public static ResourceLocation location(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}