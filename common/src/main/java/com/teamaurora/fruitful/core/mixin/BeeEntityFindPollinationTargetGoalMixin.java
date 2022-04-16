package com.teamaurora.fruitful.core.mixin;

import com.teamaurora.fruitful.common.block.OakBlossomBlock;
import com.teamaurora.fruitful.core.registry.FruitfulBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Bee.BeeGrowCropGoal.class)
public abstract class BeeEntityFindPollinationTargetGoalMixin {
    @Dynamic(value = "This is from a Fruitful mixin :0")
    private Bee b;

    private BeeEntityFindPollinationTargetGoalMixin() {
    }

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void onConstructed(Bee outer, CallbackInfo ci) {
        b = outer;
    }

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void onTick(CallbackInfo ci) {
        if (b.getRandom().nextInt(15) == 0) {
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (Math.abs(x) != 1 || Math.abs(y) != 1 || Math.abs(z) != 1) {
                            blockpos$mutableblockpos.set(b.getPosition().add(x, y, z));
                            BlockState blockstate = b.level.getBlockState(blockpos$mutableblockpos);
                            Block block = blockstate.getBlock();

                            if (block == FruitfulBlocks.BLOSSOMING_OAK_LEAVES.get() && !blockstate.getValue(LeavesBlock.PERSISTENT) && !blockstate.getValue(OakBlossomBlock.POLLINATED)) {
                                b.level.levelEvent(2005, blockpos$mutableblockpos, 0);
                                b.level.setBlockAndUpdate(blockpos$mutableblockpos, blockstate.setValue(OakBlossomBlock.POLLINATED, true));
                                b.incrementNumCropsGrownSincePollination();
                            }
                        }
                    }
                }
            }
        }
    }
}