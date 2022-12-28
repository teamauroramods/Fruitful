package com.teamaurora.fruitful.core.forge;

import com.teamaurora.fruitful.core.Fruitful;
import com.teamaurora.fruitful.core.registry.FruitfulFeatures;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Fruitful.MOD_ID)
public class FruitfulForge {
    public FruitfulForge() {
        Fruitful.PLATFORM.setup();
        MinecraftForge.EVENT_BUS.addListener(this::onBiomeLoad);
    }

    // World Gen //

    private void onBiomeLoad(BiomeLoadingEvent event) {
        ResourceLocation biome = event.getName();
        BiomeGenerationSettingsBuilder gen = event.getGeneration();

        if (biome != null)
            gen.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Holder.direct(FruitfulFeatures.Placements.TREES_FLOWER_FOREST.get()));
    }

}
