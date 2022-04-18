package com.teamaurora.fruitful.core.other;

import com.teamaurora.fruitful.core.Fruitful;
import gg.moonflower.pollen.api.registry.resource.TagRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

/**
 * @author Exoplanetary, Steven
 */
@SuppressWarnings("unused")
public class FruitfulTags {
    public static class Items {
        public static final Tag.Named<Item> GIVES_SUSTAINING = TagRegistry.bindItem(new ResourceLocation(Fruitful.MOD_ID, "gives_sustaining"));
        public static final Tag.Named<Item> GIVES_SUSTAINING_II = TagRegistry.bindItem(new ResourceLocation(Fruitful.MOD_ID, "gives_sustaining_ii"));
        public static final Tag.Named<Item> GIVES_SUSTAINING_LONG = TagRegistry.bindItem(new ResourceLocation(Fruitful.MOD_ID, "gives_sustaining_long"));
    }
}