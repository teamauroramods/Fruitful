package com.teamaurora.fruitful.common.block.trees;

import com.teamaurora.fruitful.core.registry.FruitfulFeatures;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

/**
 * @author Exoplanetary, Steven
 */
public class FloweringOakTree extends AbstractTreeGrower {
    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random random, boolean largeHive) {
        if (random.nextInt(10) == 0) {
            return largeHive ? FruitfulFeatures.Configured.FLOWERING_FANCY_OAK_BEES_005.get() : FruitfulFeatures.Configured.FLOWERING_FANCY_OAK.get();
        } else {
            return largeHive ? FruitfulFeatures.Configured.FLOWERING_OAK_BEES_005.get() : FruitfulFeatures.Configured.FLOWERING_OAK.get();
        }
    }
}