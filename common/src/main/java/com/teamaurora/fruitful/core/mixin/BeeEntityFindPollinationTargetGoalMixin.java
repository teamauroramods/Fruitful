package com.teamaurora.fruitful.core.mixin;

import com.teamaurora.fruitful.common.block.OakBlossomBlock;
import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Exoplanetary, Sarinsa, Steven
 */
@Mixin(Bee.BeeGrowCropGoal.class)
public abstract class BeeEntityFindPollinationTargetGoalMixin {
    private Bee bee;

    private BeeEntityFindPollinationTargetGoalMixin() {
    }

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void onConstructed(Bee outer, CallbackInfo ci) {
        bee = outer;
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void onTick(CallbackInfo ci) {
        if (bee.getRandom().nextInt(15) == 0) {
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (Math.abs(x) != 1 || Math.abs(y) != 1 || Math.abs(z) != 1) {
                            mutableBlockPos.set(bee.blockPosition().offset(x, y, z));
                            BlockState blockstate = bee.level.getBlockState(mutableBlockPos);
                            Block block = blockstate.getBlock();

                            if (block == FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get() && !blockstate.getValue(LeavesBlock.PERSISTENT) && !blockstate.getValue(OakBlossomBlock.POLLINATED)) {
                                bee.level.levelEvent(2005, mutableBlockPos, 0);
                                bee.level.setBlockAndUpdate(mutableBlockPos, blockstate.setValue(OakBlossomBlock.POLLINATED, true));
                                bee.incrementNumCropsGrownSincePollination();
                            }
                        }
                    }
                }
            }
        }
    }
}