package com.gershaveut.manamod.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Rarity;

public class MMRarity {
    public static final Rarity MANA = Rarity.create("mana", style -> style.withFont(new ResourceLocation("minecraft", "mana")).withColor(ChatFormatting.AQUA));
}
