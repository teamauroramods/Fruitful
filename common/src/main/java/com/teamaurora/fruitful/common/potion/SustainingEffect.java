package com.teamaurora.fruitful.common.potion;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

/**
 * @author Exoplanetary, Steven
 */
public class SustainingEffect extends MobEffect {
    public SustainingEffect() {
        super(MobEffectCategory.BENEFICIAL, 16774917);
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int i) {
    }

    @Override
    public void applyInstantenousEffect(@Nullable Entity entity, @Nullable Entity entity2, LivingEntity livingEntity, int i, double d) {
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}