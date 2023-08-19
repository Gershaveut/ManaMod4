package com.gershaveut.manamod.data.models;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.level.block.MMBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class MMBlockModelProvider extends BlockModelProvider {
    public MMBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ManaMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicBlock(MMBlocks.MANA_BLOCK.get());
        basicBlock(MMBlocks.MANA_BRICKS.get());
        basicBlock(MMBlocks.CHISELED_MANA_BRICKS.get());
        basicBlock(MMBlocks.MANA_BRICK_STAIRS.get(), MMBlocks.MANA_BRICKS.get());
        basicBlock(MMBlocks.MANA_BRICK_WALL.get(), MMBlocks.MANA_BRICKS.get());
        basicBlock(MMBlocks.MANA_BRICK_SLAB.get(), MMBlocks.MANA_BRICKS.get());
        //basicBlock(MMBlocks.MANA_CAKE.get());
        basicBlock(MMBlocks.MANA_ORE.get());
        basicBlock(MMBlocks.MANA_SHIELD.get());
        basicBlock(MMBlocks.MANA_SYNTHESIZER.get());
        basicBlock(MMBlocks.UNSTABLE_MANA_BLOCK.get());
    }

    private BlockModelBuilder basicBlock(Block block) {
        return cubeAll(blockPrefix(block).getPath(), ManaMod.prefix("block/" + blockPrefix(block).getPath()));
    }

    private BlockModelBuilder basicBlock(Block block, Block foreign) {
        return cubeAll(blockPrefix(block).getPath(), ManaMod.prefix("block/" + blockPrefix(foreign).getPath()));
    }

    private ResourceLocation blockPrefix(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
    }
}
