package com.teamaurora.fruitful.core.fabric;

import com.teamaurora.fruitful.core.Fruitful;
import com.teamaurora.fruitful.core.registry.FruitfulFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class FruitfulFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Fruitful.PLATFORM.setup();

//        var flowerBiomes = Fruitful.CONFIG.flowerBiomes.get().toString().replace("[", "").replace("]", "").replace(" ", "").split(",", Fruitful.CONFIG.flowerBiomes.get().size());
//        for (int flowerBiome = 0; flowerBiome < flowerBiomes.length; flowerBiome++) {
//            var biomeSelector = BiomeSelectors.includeByKey(ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(flowerBiomes[flowerBiome])));
//            BiomeModifications.addFeature(biomeSelector, GenerationStep.Decoration.VEGETAL_DECORATION, registryKey(FruitfulFeatures.Configured.FLOWERING_OAK_INFREQUENT.get()));
//        }

//        BiomeModifications.create(new ResourceLocation(Fruitful.MOD_ID, "remove_flower_forest_trees"))
//                .add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(Biomes.FLOWER_FOREST), (c) -> {
//                    if (c.getGenerationSettings().removeBuiltInFeature(VegetationPlacements.TREES_FLOWER_FOREST)) {
//                        c.getGenerationSettings().addBuiltInFeature(GenerationStep.Decoration.VEGETAL_DECORATION, FruitfulFeatures.Configured.TREES_FLOWER_FOREST_PLACED.get());
//                    }
//                });
    }

    public static ResourceKey<PlacedFeature> registryKey(PlacedFeature carver)
    {
        return BuiltinRegistries.PLACED_FEATURE.getResourceKey(carver).get();
    }
}