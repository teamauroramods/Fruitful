package com.teamaurora.fruitful.core.registry;

import com.teamaurora.fruitful.core.Fruitful;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class FruitfulItems {
    public static final PollinatedRegistry<Item> ITEMS = PollinatedRegistry.create(Registry.ITEM, Fruitful.MOD_ID);

    public static final Supplier<Item> BAKED_APPLE = ITEMS.register("baked_apple", ()->new Item(new Item.Properties().food(Foods.BAKED_APPLE).tab(CreativeModeTab.TAB_FOOD)));

    public static class Foods {
        public static final FoodProperties BAKED_APPLE = new FoodProperties.Builder().nutrition(6).saturationMod(0.4F).build();
    }
}