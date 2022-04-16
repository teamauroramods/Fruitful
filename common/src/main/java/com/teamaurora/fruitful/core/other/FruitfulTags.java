package com.teamaurora.fruitful.core.other;

import com.teamaurora.fruitful.core.Fruitful;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class FruitfulTags {
    public static class Items {
        public static final ITag.INamedTag<Item> GIVES_SUSTAINING = ItemTags.createOptional(new ResourceLocation(Fruitful.MODID, "gives_sustaining"));
        public static final ITag.INamedTag<Item> GIVES_SUSTAINING_II = ItemTags.createOptional(new ResourceLocation(Fruitful.MODID, "gives_sustaining_ii"));
        public static final ITag.INamedTag<Item> GIVES_SUSTAINING_LONG = ItemTags.createOptional(new ResourceLocation(Fruitful.MODID, "gives_sustaining_long"));
    }
}
