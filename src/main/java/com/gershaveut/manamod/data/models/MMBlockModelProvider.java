package com.gershaveut.manamod.data.models;

import com.gershaveut.manamod.ManaMod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MMBlockModelProvider extends BlockModelProvider {
    public MMBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ManaMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }
}
