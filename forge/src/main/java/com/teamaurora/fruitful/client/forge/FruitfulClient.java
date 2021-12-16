package com.teamaurora.fruitful.client.forge;

import com.teamaurora.fruitful.core.Fruitful;
import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.FoliageColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Fruitful.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FruitfulClient {
    @SubscribeEvent
    public static void registerClient(final FMLClientSetupEvent event) {
        registerBlockColors();
        setupRenderLayer();
    }

    private static void setupRenderLayer() {
        ItemBlockRenderTypes.setRenderLayer(FruitfulBlocks.APPLE_OAK_LEAVES.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(FruitfulBlocks.FLOWERING_OAK_LEAVES.get(), RenderType.cutoutMipped());
    }

    private static void registerBlockColors() {
        BlockColors blockColors = Minecraft.getInstance().getBlockColors();
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        BlockColor oakLeafColor = (x, world, pos, u) -> world != null && pos != null ? BiomeColors.getAverageFoliageColor(world, pos) : FoliageColor.getDefaultColor();
        blockColors.register(oakLeafColor, FruitfulBlocks.APPLE_OAK_LEAVES.get(), FruitfulBlocks.FLOWERING_OAK_LEAVES.get());

        ItemColor oakLeafItemColor = (color, items) -> FoliageColor.getDefaultColor();
        itemColors.register(oakLeafItemColor, FruitfulBlocks.APPLE_OAK_LEAVES.get(), FruitfulBlocks.FLOWERING_OAK_LEAVES.get());
    }
}
