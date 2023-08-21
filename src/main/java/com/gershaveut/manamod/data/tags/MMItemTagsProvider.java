package com.gershaveut.manamod.data.tags;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.item.MMItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class MMItemTagsProvider extends ItemTagsProvider {
    public MMItemTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> lookupBlock, ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, lookupBlock, ManaMod.MODID, existingFileHelper);
    }
    
    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        tag(ItemTags.CLUSTER_MAX_HARVESTABLES).add(MMItems.MANA_PICKAXE.get());
        tag(ItemTags.AXES).add(MMItems.MANA_AXE.get());
    }
}
