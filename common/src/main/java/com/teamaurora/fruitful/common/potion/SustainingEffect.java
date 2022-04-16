package com.teamaurora.fruitful.common.potion;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SustainingEffect extends Effect {
    public SustainingEffect() {
        super(EffectType.BENEFICIAL, 16774917);
    }

    @Override
    public void performEffect(LivingEntity livingEntity, int amplifier) {
    }

    @Override
    public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health) {

    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        return true;
    }
}