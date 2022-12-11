package com.teamaurora.fruitful.core.registry;

import com.teamaurora.fruitful.core.Fruitful;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

public final class FruitfulFeatures {
    public static final PollinatedRegistry<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = PollinatedRegistry.create(BuiltinRegistries.CONFIGURED_FEATURE, Fruitful.MOD_ID);
    public static final PollinatedRegistry<PlacedFeature> PLACEMENTS = PollinatedRegistry.create(BuiltinRegistries.PLACED_FEATURE, Fruitful.MOD_ID);
    private static final Logger LOGGER = LogManager.getLogger();

    public static void load(Platform platform) {
        LOGGER.debug("Registered to platform");
        CONFIGURED_FEATURES.register(platform);
        PLACEMENTS.register(platform);
    }

    private static final class Configs {
        public static final TreeConfiguration FLOWERING_OAK = floweringOak().build();
        public static final TreeConfiguration FLOWERING_OAK_BEES = floweringOak().decorators(List.of(new BeehiveDecorator(0.05F))).build();

        public static final TreeConfiguration FANCY_FLOWERING_OAK = fancyFloweringOak().build();
        public static final TreeConfiguration FANCY_FLOWERING_OAK_BEES = fancyFloweringOak().decorators(List.of(new BeehiveDecorator(0.05F))).build();

        private static TreeConfiguration.TreeConfigurationBuilder floweringOak() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new StraightTrunkPlacer(4, 2, 0),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(FruitfulBlocks.BUDDING_OAK_LEAVES.get().defaultBlockState(), 2).add(FruitfulBlocks.BUDDING_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                    new TwoLayersFeatureSize(1, 0, 1));
        }

        private static TreeConfiguration.TreeConfigurationBuilder fancyFloweringOak() {
            return new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(Blocks.OAK_LOG),
                    new FancyTrunkPlacer(3, 11, 0),
                    new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(FruitfulBlocks.BUDDING_OAK_LEAVES.get().defaultBlockState(), 2).add(FruitfulBlocks.FLOWERING_OAK_LEAVES.get().defaultBlockState(), 1).build()),
                    new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))
                    .ignoreVines();
        }

    }

    public static final class Configured {

        // Checked //

        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_OAK_CHECKED = CONFIGURED_FEATURES.register("flowering_oak_checked", () -> new ConfiguredFeature<>(Feature.TREE, Configs.FLOWERING_OAK));
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_OAK_BEES_CHECKED = CONFIGURED_FEATURES.register("flowering_oak_bees_checked", () -> new ConfiguredFeature<>(Feature.TREE, Configs.FLOWERING_OAK_BEES));
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_FANCY_OAK_CHECKED = CONFIGURED_FEATURES.register("flowering_fancy_oak_checked", () -> new ConfiguredFeature<>(Feature.TREE, Configs.FANCY_FLOWERING_OAK));
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_FANCY_OAK_BEES_CHECKED = CONFIGURED_FEATURES.register("flowering_fancy_oak_bees_checked", () -> new ConfiguredFeature<>(Feature.TREE, Configs.FANCY_FLOWERING_OAK_BEES));

        // Generation //

        public static final Supplier<ConfiguredFeature<RandomFeatureConfiguration, ?>> FLOWERING_OAK = CONFIGURED_FEATURES.register("flowering_oak", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(Holder.direct(Placements.FLOWERING_OAK_CHECKED.get()), 0.33333334F)), Holder.direct(Placements.FLOWERING_OAK_BEES_CHECKED.get()))));
        public static final Supplier<ConfiguredFeature<RandomFeatureConfiguration, ?>> FLOWERING_FANCY_OAK = CONFIGURED_FEATURES.register("flowering_fancy_oak", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(Holder.direct(Placements.FLOWERING_FANCY_OAK_CHECKED.get()), 0.33333334F)), Holder.direct(Placements.FLOWERING_FANCY_OAK_BEES_CHECKED.get()))));
        public static final Supplier<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_FLOWER_FOREST = CONFIGURED_FEATURES.register("trees_flower_forest", () -> new ConfiguredFeature<>(Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.BIRCH_BEES_002, 0.2F), new WeightedPlacedFeature(Holder.direct(Placements.FLOWERING_FANCY_OAK_BEES_CHECKED.get()), 0.1F)), Holder.direct(Placements.FLOWERING_OAK_BEES_CHECKED.get()))));

    }

    public static final class Placements {

        // Checked //

        public static final Supplier<PlacedFeature> FLOWERING_OAK_CHECKED = PLACEMENTS.register("flowering_oak_checked", () -> new PlacedFeature(Holder.direct(Configured.FLOWERING_OAK_CHECKED.get()), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING))));
        public static final Supplier<PlacedFeature> FLOWERING_OAK_BEES_CHECKED = PLACEMENTS.register("flowering_oak_bees_checked", () -> new PlacedFeature(Holder.direct(Configured.FLOWERING_OAK_BEES_CHECKED.get()), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING))));
        public static final Supplier<PlacedFeature> FLOWERING_FANCY_OAK_CHECKED = PLACEMENTS.register("flowering_fancy_oak_checked", () -> new PlacedFeature(Holder.direct(Configured.FLOWERING_FANCY_OAK_CHECKED.get()), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING))));
        public static final Supplier<PlacedFeature> FLOWERING_FANCY_OAK_BEES_CHECKED = PLACEMENTS.register("flowering_fancy_oak_bees_checked", () -> new PlacedFeature(Holder.direct(Configured.FLOWERING_FANCY_OAK_BEES_CHECKED.get()), List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING))));

        // Generation //

        public static final Supplier<PlacedFeature> TREES_FLOWER_FOREST = PLACEMENTS.register("trees_flower_forest", () -> new PlacedFeature(Holder.direct(Configured.TREES_FLOWER_FOREST.get()), VegetationPlacements.treePlacement(PlacementUtils.countExtra(6, 0.1F, 1))));
        public static final Supplier<PlacedFeature> FLOWERING_OAK_INFREQUENT = PLACEMENTS.register("flowering_oak_infrequent", () -> new PlacedFeature(Holder.direct(Configured.FLOWERING_OAK.get()), VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.1F, 0))));

    }

    // resource keys used by fabric representing the placement features
    public static final class Keys {
        public static final ResourceKey<PlacedFeature> FLOWER_FOREST_TREES_KEY = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, Fruitful.location("trees_flower_forest"));
        public static final ResourceKey<PlacedFeature> FLOWERING_OAK_INFREQUENT_KEY = ResourceKey.create(Registry.PLACED_FEATURE_REGISTRY, Fruitful.location("flowering_oak_infrequent"));

    }
}