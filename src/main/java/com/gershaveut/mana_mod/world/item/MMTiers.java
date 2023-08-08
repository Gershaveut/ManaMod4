package com.gershaveut.mana_mod.world.item;

import com.gershaveut.mana_mod.ManaMod;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class MMTiers {
    public static final Tier MANA = TierSortingRegistry.registerTier(new ForgeTier(2, 300, 7F, 2.5F, 35, BlockTags.create(ManaMod.prefix("needs_mana_tool")), () -> Ingredient.of(MMItems.MANA.get())), ManaMod.prefix("mana"), List.of(Tiers.IRON), List.of(Tiers.DIAMOND));
}
