package com.gershaveut.mana_mod.data.worldgen.features;

import com.gershaveut.mana_mod.ManaMod;
import com.gershaveut.mana_mod.world.level.block.MMBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class MMOreFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORE_MANA = ResourceKey.create(Registries.CONFIGURED_FEATURE, ManaMod.prefix("ore_mana"));
    
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest baseStoneOverworld = new TagMatchTest(BlockTags.BASE_STONE_OVERWORLD);
        
        List<OreConfiguration.TargetBlockState> manaOres = List.of(OreConfiguration.target(baseStoneOverworld, MMBlocks.MANA_ORE.get().defaultBlockState()));
        
        context.register(ORE_MANA, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(manaOres, 10)));
    }
}
