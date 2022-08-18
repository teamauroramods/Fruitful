package com.teamaurora.fruitful.core.mixin;

import com.teamaurora.fruitful.core.other.FruitfulTags;
import com.teamaurora.fruitful.core.registry.FruitfulEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

/**
 * @author Steven
 */
@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(at = @At("HEAD"), method = "eat")
    public void fruitfulEat(Level level, ItemStack itemStack, CallbackInfoReturnable<ItemStack> cir) {
        Player player = (Player) (Object) this;

        /*  Sustaining  */
        if (player.hasEffect(FruitfulEffects.SUSTAINING.get())) {
            var amplifier = Objects.requireNonNull(player.getEffect(FruitfulEffects.SUSTAINING.get())).getAmplifier();
            var foodToAdd = 2 * (amplifier + 1);
            player.getFoodData().eat(foodToAdd, 0);
        }

        /* Apples Give Sustaining */
        if (itemStack.is(FruitfulTags.Items.GIVES_SUSTAINING)) {
            if (player.hasEffect(FruitfulEffects.SUSTAINING.get())) {
                var ticksRemaining = Objects.requireNonNull(player.getEffect(FruitfulEffects.SUSTAINING.get())).getDuration();
                player.addEffect(new MobEffectInstance(FruitfulEffects.SUSTAINING.get(), Math.max(200, ticksRemaining), 0, false, false, true));
            } else {
                player.addEffect(new MobEffectInstance(FruitfulEffects.SUSTAINING.get(), 200, 0, false, false, true));
            }
        }
        if (itemStack.is(FruitfulTags.Items.GIVES_SUSTAINING_II)) {
            if (player.hasEffect(FruitfulEffects.SUSTAINING.get())) {
                var ticksRemaining = Objects.requireNonNull(player.getEffect(FruitfulEffects.SUSTAINING.get())).getDuration();
                player.addEffect(new MobEffectInstance(FruitfulEffects.SUSTAINING.get(), Math.max(200, ticksRemaining), 1, false, false, true));
            } else {
                player.addEffect(new MobEffectInstance(FruitfulEffects.SUSTAINING.get(), 200, 1, false, false, true));
            }
        }
        if (itemStack.is(FruitfulTags.Items.GIVES_SUSTAINING_LONG)) {
            if (player.hasEffect(FruitfulEffects.SUSTAINING.get())) {
                var ticksRemaining = Objects.requireNonNull(player.getEffect(FruitfulEffects.SUSTAINING.get())).getDuration();
                player.addEffect(new MobEffectInstance(FruitfulEffects.SUSTAINING.get(), Math.max(400, ticksRemaining), 0, false, false, true));
            } else {
                player.addEffect(new MobEffectInstance(FruitfulEffects.SUSTAINING.get(), 400, 0, false, false, true));
            }
        }
    }
}