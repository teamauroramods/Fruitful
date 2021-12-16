package com.teamaurora.fruitful.client.fabric;

import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;

public class FruitfulClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();

        BlockColor oakLeafColor = (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor();
        blockColors.register(oakLeafColor, FruitfulBlocks.APPLE_OAK_LEAVES.get(), FruitfulBlocks.FLOWERING_OAK_LEAVES.get());

        /*ItemColor oakLeafItemColor = (color, items) -> FoliageColor.getDefaultColor();
        itemColors.register(oakLeafItemColor, FruitfulBlocks.APPLE_OAK_LEAVES.get(), FruitfulBlocks.FLOWERING_OAK_LEAVES.get());*/

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutoutMipped(),
                FruitfulBlocks.APPLE_OAK_LEAVES.get(),
                FruitfulBlocks.FLOWERING_OAK_LEAVES.get()
        );
    }
}
