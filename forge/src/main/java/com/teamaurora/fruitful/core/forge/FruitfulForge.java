package com.teamaurora.fruitful.core.forge;

import com.teamaurora.fruitful.core.Fruitful;
import net.minecraftforge.fml.common.Mod;

@Mod(Fruitful.MOD_ID)
public class FruitfulForge {
    public FruitfulForge() {
        Fruitful.PLATFORM.setup();
    }
}
