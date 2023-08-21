package com.gershaveut.manamod.data.tags;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.level.block.MMBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class MMBlockTagsProvider extends BlockTagsProvider {
    public MMBlockTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, ManaMod.MODID, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(MMBlocks.MANA_ORE.get(), MMBlocks.UNSTABLE_MANA_BLOCK.get(), MMBlocks.MANA_BRICKS.get(), MMBlocks.CHISELED_MANA_BRICKS.get(), MMBlocks.MANA_BRICK_STAIRS.get(), MMBlocks.MANA_BRICK_WALL.get(), MMBlocks.MANA_BRICK_SLAB.get());
        tag(BlockTags.NEEDS_IRON_TOOL).add(MMBlocks.MANA_ORE.get());
        tag(BlockTags.SLABS).add(MMBlocks.MANA_BRICK_SLAB.get());
        tag(BlockTags.STAIRS).add(MMBlocks.MANA_BRICK_STAIRS.get());
        tag(BlockTags.WALLS).add(MMBlocks.MANA_BRICK_WALL.get());
    }
}
