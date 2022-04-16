package com.teamaurora.fruitful.core.mixin;

import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Predicate;

@Mixin(BeeEntity.PollinateGoal.class)
public abstract class BeeEntityPollinateGoal {


    @Shadow
    protected abstract Optional<BlockPos> findFlower(Predicate<BlockState> p_226500_1_, double distance);

    public BeeEntityPollinateGoal() {
    }

    @Inject(method = "getFlower", at = @At("HEAD"), cancellable = true)
    private void onGetFlower(CallbackInfoReturnable<Optional<BlockPos>> cir) {
        Optional<BlockPos> pos = findFlower((blockState) -> blockState.getBlock() == FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get(),5.0d);

        if (pos.isPresent())
            cir.setReturnValue(pos);
    }
}