package com.teamaurora.fruitful.core.fabric;

import com.teamaurora.fruitful.core.Fruitful;
import com.teamaurora.fruitful.core.registry.FruitfulFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;

public class FruitfulFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Fruitful.PLATFORM.setup();
        BiomeModifications.create(Fruitful.location("replace_flower_forest_trees")).add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(Biomes.FLOWER_FOREST), modifier -> {
            if (modifier.getGenerationSettings().removeFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.TREES_FLOWER_FOREST.unwrapKey().orElseThrow())) {
                modifier.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FruitfulFeatures.Configured.FLOWER_FOREST_TREES_KEY);
            }
        });
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION, FruitfulFeatures.Configured.FLOWERING_OAK_KEY);
    }
}