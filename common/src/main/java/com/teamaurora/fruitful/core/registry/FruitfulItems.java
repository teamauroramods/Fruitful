package com.teamaurora.fruitful.core.registry;

import com.teamaurora.fruitful.core.Fruitful;
import gg.moonflower.pollen.api.platform.Platform;
import gg.moonflower.pollen.api.registry.PollinatedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class FruitfulItems {

    private static final Logger LOGGER = LogManager.getLogger();
    public static final PollinatedRegistry<Item> ITEMS = PollinatedRegistry.create(Registry.ITEM, Fruitful.MOD_ID);
    public static final Supplier<Item> BAKED_APPLE = ITEMS.register("baked_apple", ()->new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.4F).build()).tab(CreativeModeTab.TAB_FOOD)));

    public static void load(Platform platform) {
        LOGGER.debug("Registered to platform");
        ITEMS.register(platform);
    }
}