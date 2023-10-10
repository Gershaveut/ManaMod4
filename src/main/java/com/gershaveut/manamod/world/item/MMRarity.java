package com.gershaveut.manamod.world.item;

import com.gershaveut.manamod.ManaMod;
import net.minecraft.world.item.Rarity;

public class MMRarity {
    public static final Rarity MANA = Rarity.create("mana", style -> style.withFont(ManaMod.prefix("default")));
}
