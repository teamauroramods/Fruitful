package com.teamaurora.fruitful.core.registry;

import com.google.common.collect.ImmutableList;
import com.teamaurora.fruitful.core.Fruitful;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FancyFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

public class FruitfulFeatures {
    public static final PollinatedRegistry<Feature<?>> FEATURES = PollinatedRegistry.create(Registry.FEATURE, Fruitful.MOD_ID);
    public static final PollinatedRegistry<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = PollinatedRegistry.create(Registry.TREE_DECORATOR_TYPES, Fruitful.MOD_ID);
    public static final PollinatedRegistry<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES = PollinatedRegistry.create(BuiltinRegistries.CONFIGURED_FEATURE, Fruitful.MOD_ID);
    public static final PollinatedRegistry<PlacedFeature> PLACED_FEATURES = PollinatedRegistry.create(BuiltinRegistries.PLACED_FEATURE, Fruitful.MOD_ID);

    /* Beehives */
    public static final BeehiveDecorator FRUITFUL_BEEHIVE_0002 = new BeehiveDecorator(0.002F);
    public static final BeehiveDecorator FRUITFUL_BEEHIVE_005 = new BeehiveDecorator(0.05F);

    public static final class BlockStates {
        public static final BlockState FLOWERING_OAK_LEAVES = FruitfulBlocks.FLOWERING_OAK_LEAVES.get().defaultBlockState();
        public static final BlockState BUDDING_OAK_LEAVES = FruitfulBlocks.BUDDING_OAK_LEAVES.get().defaultBlockState();
    }

    public static final class Configs {
        private static TreeConfiguration.TreeConfigurationBuilder createFancyFloweringOak() {
            return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new FancyTrunkPlacer(3, 11, 0), new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BlockStates.BUDDING_OAK_LEAVES, 2).add(BlockStates.FLOWERING_OAK_LEAVES, 1).build()), new FancyFoliagePlacer(ConstantInt.of(2), ConstantInt.of(4), 4), new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
        }
        private static TreeConfiguration.TreeConfigurationBuilder createFloweringOak() {
            return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new StraightTrunkPlacer(4, 2, 0), new WeightedStateProvider(new SimpleWeightedRandomList.Builder<BlockState>().add(BlockStates.BUDDING_OAK_LEAVES, 2).add(BlockStates.FLOWERING_OAK_LEAVES, 1).build()), new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
        }
    }

    public static final class Configured {
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_OAK = () -> Feature.TREE.configured(Configs.createFloweringOak().build());
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_OAK_BEES_005 = () -> Feature.TREE.configured(Configs.createFloweringOak().decorators(List.of(FRUITFUL_BEEHIVE_005)).build());
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_OAK_BEES_002 = () -> Feature.TREE.configured(Configs.createFloweringOak().decorators(List.of(FRUITFUL_BEEHIVE_0002)).build());
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_FANCY_OAK = () -> Feature.TREE.configured(Configs.createFancyFloweringOak().build());
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_FANCY_OAK_BEES_005 = () -> Feature.TREE.configured(Configs.createFancyFloweringOak().decorators(List.of(FRUITFUL_BEEHIVE_005)).build());
        public static final Supplier<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_FANCY_OAK_BEES_002 = () -> Feature.TREE.configured(Configs.createFancyFloweringOak().decorators(List.of(FRUITFUL_BEEHIVE_0002)).build());

        public static final Supplier<PlacedFeature> FLOWERING_OAK_INFREQUENT = () -> FLOWERING_OAK.get().placed(VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.12F, 3)));
        public static final Supplier<ConfiguredFeature<?, ?>> TREES_FLOWER_FOREST = () -> Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.BIRCH_BEES_002, 0.2F), new WeightedPlacedFeature(FLOWERING_FANCY_OAK_BEES_002.get().filteredByBlockSurvival(FruitfulBlocks.FLOWERING_OAK_SAPLING.get()), 0.05F), new WeightedPlacedFeature(TreePlacements.BIRCH_BEES_002, 0.053F), new WeightedPlacedFeature(TreePlacements.BIRCH_BEES_002, 0.5F)), FLOWERING_OAK_BEES_002.get().filteredByBlockSurvival(FruitfulBlocks.FLOWERING_OAK_SAPLING.get())));
        public static final Supplier<PlacedFeature> TREES_FLOWER_FOREST_PLACED = () -> TREES_FLOWER_FOREST.get().placed(VegetationPlacements.treePlacement(PlacementUtils.countExtra(6, 0.1F, 1)));


        public static void registerConfiguredFeatures() {
            CONFIGURED_FEATURES.register("flowering_oak", FLOWERING_OAK);
            CONFIGURED_FEATURES.register("flowering_fancy_oak", FLOWERING_FANCY_OAK);
            CONFIGURED_FEATURES.register("flowering_oak_bees_005", FLOWERING_OAK_BEES_005);
            CONFIGURED_FEATURES.register("flowering_fancy_oak_bees_005", FLOWERING_FANCY_OAK_BEES_005);
            CONFIGURED_FEATURES.register("flowering_oak_bees_002", FLOWERING_OAK_BEES_002);
            CONFIGURED_FEATURES.register("flowering_fancy_oak_bees_002", FLOWERING_FANCY_OAK_BEES_002);

//            PLACED_FEATURES.register("flowering_oak_infrequent", FLOWERING_OAK_INFREQUENT);
            CONFIGURED_FEATURES.register("trees_flower_forest", TREES_FLOWER_FOREST);
            PLACED_FEATURES.register("trees_flower_forest", TREES_FLOWER_FOREST_PLACED);
        }
    }
}