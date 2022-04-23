package com.teamaurora.fruitful.core.fabric;

import com.teamaurora.fruitful.core.Fruitful;
import net.fabricmc.api.ModInitializer;

public class FruitfulFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Fruitful.PLATFORM.setup();
    }
}