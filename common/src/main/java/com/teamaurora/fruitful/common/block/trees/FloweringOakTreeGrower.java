package com.teamaurora.fruitful.common.block.trees;

import com.teamaurora.fruitful.core.registry.FruitfulFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.Random;

/**
 * @author Exoplanetary, Steven
 */
public class FloweringOakTreeGrower extends AbstractTreeGrower {
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random random, boolean largeHive) {
        if (random.nextInt(10) == 0) {
            return largeHive ? Holder.direct(FruitfulFeatures.Configured.FLOWERING_FANCY_OAK_BEES_CHECKED.get()) : Holder.direct(FruitfulFeatures.Configured.FLOWERING_FANCY_OAK_CHECKED.get());
        } else {
            return largeHive ? Holder.direct(FruitfulFeatures.Configured.FLOWERING_OAK_BEES_CHECKED.get()) : Holder.direct(FruitfulFeatures.Configured.FLOWERING_OAK_CHECKED.get());
        }
    }
}