package com.teamaurora.fruitful.core.registry;

import com.teamaurora.fruitful.core.Fruitful;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

public class FruitfulItems {
    public static final PollinatedRegistry<Item> ITEMS = PollinatedRegistry.create(Registry.ITEM, Fruitful.MOD_ID);
}
