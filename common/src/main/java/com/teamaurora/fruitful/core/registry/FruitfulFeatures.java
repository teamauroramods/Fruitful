package com.teamaurora.fruitful.core.registry;

import com.google.common.collect.ImmutableList;
import com.teamaurora.fruitful.core.Fruitful;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class FruitfulFeatures {

    public static final class Configured {
        public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_OAK = Feature.TREE.configured(Configs.FLOWERING_OAK);
        public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_FANCY_OAK = Feature.TREE.configured(Configs.FLOWERING_FANCY_OAK);
        public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_OAK_BEES_005 = Feature.TREE.configured(Configs.FLOWERING_OAK.func_236685_a_(ImmutableList.of(Features.Placements.BEES_005_PLACEMENT)));
        public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_FANCY_OAK_BEES_005 = Feature.TREE.configured(Configs.FLOWERING_FANCY_OAK.func_236685_a_(ImmutableList.of(Features.Placements.BEES_005_PLACEMENT)));
        public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_OAK_BEES_002 = Feature.TREE.configured(Configs.FLOWERING_OAK.func_236685_a_(ImmutableList.of(Features.Placements.BEES_002_PLACEMENT)));
        public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_FANCY_OAK_BEES_002 = Feature.TREE.configured(Configs.FLOWERING_FANCY_OAK.func_236685_a_(ImmutableList.of(Features.Placements.BEES_002_PLACEMENT)));

        public static final ConfiguredFeature<?, ?> FLOWERING_OAK_INFREQUENT = FLOWERING_OAK.withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.12F, 3)));
        public static final ConfiguredFeature<?, ?> FOREST_FLOWER_TREES = Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(Features.BIRCH_BEES_002.withChance(0.2F), FLOWERING_FANCY_OAK_BEES_002.withChance(0.05F), Features.FANCY_OAK_BEES_002.withChance(0.053F), Features.OAK_BEES_002.withChance(0.5F)), FLOWERING_OAK_BEES_002)).withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(6, 0.1F, 1)));

        private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Fruitful.MOD_ID, name), configuredFeature);
        }

        public static void registerConfiguredFeatures() {
            register("flowering_oak", FLOWERING_OAK);
            register("flowering_fancy_oak", FLOWERING_FANCY_OAK);
            register("flowering_oak_bees_005", FLOWERING_OAK_BEES_005);
            register("flowering_fancy_oak_bees_005", FLOWERING_FANCY_OAK_BEES_005);
            register("flowering_oak_bees_002", FLOWERING_OAK_BEES_002);
            register("flowering_fancy_oak_bees_002", FLOWERING_FANCY_OAK_BEES_002);

            register("flowering_oak_infrequent", FLOWERING_OAK_INFREQUENT);
            register("forest_flower_trees", FOREST_FLOWER_TREES);
        }
    }
}