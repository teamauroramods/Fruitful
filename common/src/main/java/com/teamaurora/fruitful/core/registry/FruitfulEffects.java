package com.teamaurora.fruitful.core.registry;

import com.teamaurora.fruitful.common.potion.SustainingEffect;
import com.teamaurora.fruitful.core.Fruitful;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public class FruitfulEffects {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final PollinatedRegistry<MobEffect> EFFECTS = PollinatedRegistry.create(Registry.MOB_EFFECT, Fruitful.MOD_ID);
    public static Supplier<MobEffect> SUSTAINING = EFFECTS.register("sustaining", SustainingEffect::new);

    public static void load(Platform platform) {
        LOGGER.debug("Registered to platform");
        EFFECTS.register(platform);
    }
}