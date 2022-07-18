package com.teamaurora.fruitful.core.registry;

import com.google.common.collect.ImmutableList;
import com.teamaurora.fruitful.core.Fruitful;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
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
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
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
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.Supplier;

public class FruitfulFeatures {
    public static final PollinatedRegistry<Feature<?>> FEATURES = PollinatedRegistry.create(Registry.FEATURE, Fruitful.MOD_ID);
    public static final PollinatedRegistry<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = PollinatedRegistry.create(Registry.TREE_DECORATOR_TYPES, Fruitful.MOD_ID);
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

        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_OAK = register("flowering_oak", Feature.TREE, Configs.createFloweringOak().build());
        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_OAK_BEES_005 = register("flowering_oak_bees_005", Feature.TREE, Configs.createFloweringOak().decorators(List.of(FRUITFUL_BEEHIVE_005)).build());
        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_OAK_BEES_002 = register("flowering_oak_bees_002", Feature.TREE, Configs.createFloweringOak().decorators(List.of(FRUITFUL_BEEHIVE_0002)).build());
        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_FANCY_OAK = register("flowering_fancy_oak", Feature.TREE, Configs.createFancyFloweringOak().build());
        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_FANCY_OAK_BEES_005 = register("flowering_fancy_oak_bees_005", Feature.TREE, Configs.createFancyFloweringOak().decorators(List.of(FRUITFUL_BEEHIVE_005)).build());
        public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> FLOWERING_FANCY_OAK_BEES_002 = register("flowering_fancy_oak_bees_002", Feature.TREE, Configs.createFancyFloweringOak().decorators(List.of(FRUITFUL_BEEHIVE_0002)).build());

        public static final Holder<PlacedFeature> FLOWERING_OAK_CHECKED = registerPlaced("flowering_oak_checked", FLOWERING_OAK, List.of(PlacementUtils.filteredByBlockSurvival(FruitfulBlocks.FLOWERING_OAK_SAPLING.get())));
        public static final Holder<PlacedFeature> FLOWERING_OAK_BEES_005_PLACED = registerPlaced("flowering_oak_bees_005", FLOWERING_OAK_BEES_005, List.of(PlacementUtils.filteredByBlockSurvival(FruitfulBlocks.FLOWERING_OAK_SAPLING.get())));
        public static final Holder<PlacedFeature> FLOWERING_OAK_BEES_002_PLACED = registerPlaced("flowering_oak_bees_002", FLOWERING_OAK_BEES_002, List.of(PlacementUtils.filteredByBlockSurvival(FruitfulBlocks.FLOWERING_OAK_SAPLING.get())));
        public static final Holder<PlacedFeature> FLOWERING_FANCY_OAK_CHECKED = registerPlaced("flowering_fancy_oak_checked", FLOWERING_FANCY_OAK, List.of(PlacementUtils.filteredByBlockSurvival(FruitfulBlocks.FLOWERING_OAK_SAPLING.get())));
        public static final Holder<PlacedFeature> FLOWERING_FANCY_OAK_BEES_005_PLACED = registerPlaced("flowering_fancy_oak_bees_005", FLOWERING_FANCY_OAK_BEES_005, List.of(PlacementUtils.filteredByBlockSurvival(FruitfulBlocks.FLOWERING_OAK_SAPLING.get())));
        public static final Holder<PlacedFeature> FLOWERING_FANCY_OAK_BEES_002_PLACED = registerPlaced("flowering_fancy_oak_bees_002", FLOWERING_FANCY_OAK_BEES_002, List.of(PlacementUtils.filteredByBlockSurvival(FruitfulBlocks.FLOWERING_OAK_SAPLING.get())));

        public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>>  TREES_FLOWER_FOREST = register("trees_flower_forest", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.BIRCH_BEES_002, 0.2F), new WeightedPlacedFeature(FLOWERING_FANCY_OAK_BEES_002_PLACED, 0.1F)), FLOWERING_OAK_BEES_002_PLACED));
        public static final Holder<PlacedFeature> TREES_FLOWER_FOREST_PLACED = registerPlaced("trees_flower_forest", TREES_FLOWER_FOREST, PlacementUtils.countExtra(6, 0.1F, 1));

        public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, F feature, FC featureConfiguration) {
            return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, Fruitful.MOD_ID  + ":" + id, new ConfiguredFeature<>(feature, featureConfiguration));
        }

        public static Holder<PlacedFeature> registerPlaced(String string, Holder<? extends ConfiguredFeature<?, ?>> holder, PlacementModifier... list) {
            return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, Fruitful.MOD_ID + ":" + string, new PlacedFeature(Holder.hackyErase(holder), List.of(list)));
        }

        public static Holder<PlacedFeature> registerPlaced(String string, Holder<? extends ConfiguredFeature<?, ?>> holder, List<PlacementModifier> list) {
            return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, Fruitful.MOD_ID + ":" + string, new PlacedFeature(Holder.hackyErase(holder), List.copyOf(list)));
        }
    }
}