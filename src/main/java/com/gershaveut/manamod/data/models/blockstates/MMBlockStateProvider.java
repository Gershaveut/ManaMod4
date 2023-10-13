package com.gershaveut.manamod.data.models.blockstates;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.level.block.MMBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class MMBlockStateProvider extends BlockStateProvider {
    public MMBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ManaMod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(MMBlocks.MANA_BLOCK.get());
        simpleBlock(MMBlocks.MANA_BRICKS.get());
        simpleBlock(MMBlocks.CHISELED_MANA_BRICKS.get());
        stairsBlock((StairBlock) MMBlocks.MANA_BRICK_STAIRS.get(), blockTexture(MMBlocks.MANA_BRICKS.get()));
        wallBlock((WallBlock) MMBlocks.MANA_BRICK_WALL.get(), blockTexture(MMBlocks.MANA_BRICKS.get()));
        slabBlock((SlabBlock) MMBlocks.MANA_BRICK_SLAB.get(), blockTexture(MMBlocks.MANA_BRICKS.get()), blockTexture(MMBlocks.MANA_BRICKS.get()));
        cakeBlock(MMBlocks.MANA_CAKE.get());
        simpleBlock(MMBlocks.MANA_ORE.get());
        simpleBlock(MMBlocks.MANA_SHIELD.get());
        simpleBlock(MMBlocks.MANA_SYNTHESIZER.get());
        simpleBlock(MMBlocks.UNSTABLE_MANA_BLOCK.get());
        simpleBlock(MMBlocks.TERMINAL.get());
    }

    public void cakeBlock(Block block) {
        String nameBlock = blockPrefix(block).getPath();
        getVariantBuilder(block).forAllStates(blockState -> {
            ModelFile modelFile = switch (blockState.getValue(CakeBlock.BITES)) {
                case 0 -> new ModelFile.UncheckedModelFile(ManaMod.prefix("block/" + nameBlock));
                case 1 -> new ModelFile.UncheckedModelFile(ManaMod.prefix("block/" + nameBlock + 1));
                case 2 -> new ModelFile.UncheckedModelFile(ManaMod.prefix("block/" + nameBlock + 2));
                case 3 -> new ModelFile.UncheckedModelFile(ManaMod.prefix("block/" + nameBlock + 3));
                case 4 -> new ModelFile.UncheckedModelFile(ManaMod.prefix("block/" + nameBlock + 4));
                case 5 -> new ModelFile.UncheckedModelFile(ManaMod.prefix("block/" + nameBlock + 5));
                case 6 -> new ModelFile.UncheckedModelFile(ManaMod.prefix("block/" + nameBlock + 6));
                default -> throw new IllegalStateException("Unexpected value: " + blockState.getValue(CakeBlock.BITES));
            };

            return ConfiguredModel.builder().modelFile(modelFile).build();
        });
    }
    
    private ResourceLocation blockPrefix(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
    }
}
