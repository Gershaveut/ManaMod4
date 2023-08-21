package com.gershaveut.manamod.data.loot;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.level.block.MMBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MMBlockLootSubProvider extends BlockLootSubProvider {
    public MMBlockLootSubProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }
    
    @Override
    protected void generate() {
        dropSelf(MMBlocks.MANA_BLOCK.get());
        dropSelf(MMBlocks.MANA_BRICKS.get());
        dropSelf(MMBlocks.CHISELED_MANA_BRICKS.get());
        dropSelf(MMBlocks.MANA_BRICK_STAIRS.get());
        dropSelf(MMBlocks.MANA_BRICK_WALL.get());
        dropSelf(MMBlocks.MANA_BRICK_SLAB.get());
        dropSelf(MMBlocks.MANA_CAKE.get());
        dropSelf(MMBlocks.MANA_ORE.get());
        dropSelf(MMBlocks.MANA_SHIELD.get());
        dropSelf(MMBlocks.MANA_SYNTHESIZER.get());
        dropSelf(MMBlocks.UNSTABLE_MANA_BLOCK.get());
    }
    
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ForgeRegistries.BLOCKS.getValues().stream().filter(block -> Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).getNamespace().equals(ManaMod.MODID)).collect(Collectors.toList());
    }
}
