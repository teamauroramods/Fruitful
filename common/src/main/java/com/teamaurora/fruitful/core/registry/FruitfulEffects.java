package com.teamaurora.fruitful.core.registry;

import com.teamaurora.fruitful.common.potion.SustainingEffect;
import com.teamaurora.fruitful.core.Fruitful;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;

import java.util.function.Supplier;

public class FruitfulEffects {
    public static final PollinatedRegistry<MobEffect> EFFECTS = PollinatedRegistry.create(Registry.MOB_EFFECT, Fruitful.MOD_ID);

    public static Supplier<MobEffect> SUSTAINING = EFFECTS.register("sustaining", SustainingEffect::new);
}