package com.gershaveut.mana_mod.data.worldgen;

import com.gershaveut.mana_mod.ManaMod;
import com.gershaveut.mana_mod.data.worldgen.features.MMOreFeatures;
import com.gershaveut.mana_mod.data.worldgen.placement.MMOrePlacements;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MMWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder().add(Registries.CONFIGURED_FEATURE, MMOreFeatures::bootstrap).add(Registries.PLACED_FEATURE, MMOrePlacements::bootstrap);
    
    public MMWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(ManaMod.MODID));
    }
}
