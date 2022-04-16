package com.teamaurora.fruitful.core.mixin;

import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;
import java.util.function.Predicate;

@Mixin(Bee.BeePollinateGoal.class)
public abstract class BeeEntityPollinateGoal {
    @Shadow
    protected abstract Optional<BlockPos> findNearestBlock(Predicate<BlockState> predicate, double distance);

    public BeeEntityPollinateGoal() {
    }

    @Inject(method = "findNearbyFlower", at = @At("HEAD"), cancellable = true)
    private void onFindNearbyFlower(CallbackInfoReturnable<Optional<BlockPos>> cir) {
        Optional<BlockPos> pos = findNearestBlock((blockState) -> blockState.getBlock() == FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get(),5.0d);

        if (pos.isPresent())
            cir.setReturnValue(pos);
    }
}