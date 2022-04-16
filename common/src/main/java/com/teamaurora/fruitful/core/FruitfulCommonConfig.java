package com.teamaurora.fruitful.core;

import gg.moonflower.pollen.api.config.PollinatedConfigBuilder;

import java.util.ArrayList;

public class FruitfulCommonConfig {
    public final PollinatedConfigBuilder.ConfigValue<Boolean> whitelist;
    public final PollinatedConfigBuilder.ConfigValue<ArrayList<String>> flowerBiomes;

    protected FruitfulCommonConfig(PollinatedConfigBuilder builder) {
        builder.comment("Common configurations for Fruitful").push("common");

        ArrayList<String> defaultFlowerBiomes = new ArrayList<>();
        defaultFlowerBiomes.add("minecraft:forest");
        defaultFlowerBiomes.add("minecraft:wooded_hills");

        whitelist = builder.define("Whether the lists below are a blacklist or whitelist", true);
        flowerBiomes = builder.define("Biomes flowering oak trees can spawn in", defaultFlowerBiomes);

        builder.pop();
    }
}