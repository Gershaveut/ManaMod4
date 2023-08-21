package com.gershaveut.manamod.data.loot;

import com.gershaveut.manamod.data.loot.packs.MMLoot;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class MMLootTableProvider extends LootTableProvider {
    public MMLootTableProvider(PackOutput output) {
        super(output, MMLoot.allBuiltin(), List.of(new LootTableProvider.SubProviderEntry(MMBlockLootSubProvider::new, LootContextParamSets.BLOCK)));
    }
}
