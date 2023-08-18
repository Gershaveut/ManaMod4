package com.gershaveut.manamod.data.worldgen.placement;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.data.worldgen.features.MMOreFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class MMOrePlacements {
    public static final ResourceKey<PlacedFeature> ORE_MANA = ResourceKey.create(Registries.PLACED_FEATURE, ManaMod.prefix("ore_mana"));
    
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        
        context.register(ORE_MANA, new PlacedFeature(holderGetter.getOrThrow(MMOreFeatures.ORE_MANA), List.copyOf(rareOrePlacement(32, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(40))))));
    }
    
    private static List<PlacementModifier> orePlacement(PlacementModifier placementModifier1, PlacementModifier placementModifier2) {
        return List.of(placementModifier1, InSquarePlacement.spread(), placementModifier2, BiomeFilter.biome());
    }
    
    private static List<PlacementModifier> commonOrePlacement(int i, PlacementModifier placementModifier) {
        return orePlacement(CountPlacement.of(i), placementModifier);
    }
    
    private static List<PlacementModifier> rareOrePlacement(int i, PlacementModifier placementModifier) {
        return orePlacement(RarityFilter.onAverageOnceEvery(i), placementModifier);
    }
}
