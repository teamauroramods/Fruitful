package com.teamaurora.fruitful.core;

import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import com.teamaurora.fruitful.core.registry.FruitfulItems;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.client.ColorRegistry;
import gg.moonflower.pollen.api.registry.client.RenderTypeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;

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
        ctx.enqueueWork(() -> {
            RenderTypeRegistry.register(FruitfulBlocks.APPLE_OAK_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.FLOWERING_OAK_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.BUDDING_OAK_LEAVES.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get(), RenderType.cutoutMipped());

            RenderTypeRegistry.register(FruitfulBlocks.APPLE_OAK_LEAF_CARPET.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.FLOWERING_OAK_LEAF_CARPET.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.BUDDING_OAK_LEAF_CARPET.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.BLOSSOMING_OAK_LEAF_CARPET.get(), RenderType.cutoutMipped());

            RenderTypeRegistry.register(FruitfulBlocks.FLOWERING_OAK_SAPLING.get(), RenderType.cutoutMipped());
            RenderTypeRegistry.register(FruitfulBlocks.POTTED_FLOWERING_OAK_SAPLING.get(), RenderType.cutoutMipped());

            BlockColors blockColors = Minecraft.getInstance().getBlockColors();
            BlockColor leafColor = (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor();
            ColorRegistry.register(leafColor,
                    FruitfulBlocks.BUDDING_OAK_LEAVES,
                    FruitfulBlocks.FLOWERING_OAK_LEAVES,
                    FruitfulBlocks.BLOSSOMING_OAK_LEAVES,
                    FruitfulBlocks.APPLE_OAK_LEAVES,
                    FruitfulBlocks.BUDDING_OAK_LEAF_CARPET,
                    FruitfulBlocks.FLOWERING_OAK_LEAF_CARPET,
                    FruitfulBlocks.BLOSSOMING_OAK_LEAF_CARPET,
                    FruitfulBlocks.APPLE_OAK_LEAF_CARPET
            );


            ItemColor itemLeafColor = (stack, tint) -> blockColors.getColor(((BlockItem) stack.getItem()).getBlock().defaultBlockState(), null, null, 0);
            ColorRegistry.register(itemLeafColor,
                    FruitfulBlocks.BUDDING_OAK_LEAVES,
                    FruitfulBlocks.FLOWERING_OAK_LEAVES,
                    FruitfulBlocks.BLOSSOMING_OAK_LEAVES,
                    FruitfulBlocks.APPLE_OAK_LEAVES,
                    FruitfulBlocks.BUDDING_OAK_LEAF_CARPET,
                    FruitfulBlocks.FLOWERING_OAK_LEAF_CARPET,
                    FruitfulBlocks.BLOSSOMING_OAK_LEAF_CARPET,
                    FruitfulBlocks.APPLE_OAK_LEAF_CARPET
            );
        });
    }

    public static void onCommonInit() {
        FruitfulBlocks.BLOCKS.register(Fruitful.PLATFORM);
        FruitfulItems.ITEMS.register(Fruitful.PLATFORM);
    }

    public static void onCommonPostInit(Platform.ModSetupContext ctx) {
    }
}
