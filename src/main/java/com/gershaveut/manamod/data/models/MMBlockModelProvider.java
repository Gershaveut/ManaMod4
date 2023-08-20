package com.gershaveut.manamod.data.models;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.level.block.MMBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockModelProvider;
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
        stairsBlock(MMBlocks.MANA_BRICK_STAIRS.get(), MMBlocks.MANA_BRICKS.get());
        wallBlock(MMBlocks.MANA_BRICK_WALL.get(), MMBlocks.MANA_BRICKS.get());
        slabBlock(MMBlocks.MANA_BRICK_SLAB.get(), MMBlocks.MANA_BRICKS.get());
        cakeBlock(MMBlocks.MANA_CAKE.get());
        basicBlock(MMBlocks.MANA_ORE.get());
        basicBlock(MMBlocks.MANA_SHIELD.get());
        basicBlock(MMBlocks.MANA_SYNTHESIZER.get());
        basicBlock(MMBlocks.UNSTABLE_MANA_BLOCK.get());
    }

    private void basicBlock(Block block) {
        cubeAll(blockPrefix(block).getPath(), ManaMod.prefix("block/" + blockPrefix(block).getPath()));
    }

    private void slabBlock(Block block, Block foreign) {
        ResourceLocation foreignPrefix = ManaMod.prefix("block/" + blockPrefix(foreign).getPath());
        String blockPath = blockPrefix(block).getPath();
        slab(blockPath, foreignPrefix, foreignPrefix, foreignPrefix);
        slabTop(blockPath + "_top", foreignPrefix, foreignPrefix, foreignPrefix);
    }

    private void stairsBlock(Block block, Block foreign) {
        ResourceLocation foreignPrefix = ManaMod.prefix("block/" + blockPrefix(foreign).getPath());
        String blockPath = blockPrefix(block).getPath();
        stairs(blockPath, foreignPrefix, foreignPrefix, foreignPrefix);
        stairsInner(blockPath + "_inner", foreignPrefix, foreignPrefix, foreignPrefix);
        stairsOuter(blockPath + "_outer", foreignPrefix, foreignPrefix, foreignPrefix);
    }

    private void wallBlock(Block block, Block foreign) {
        ResourceLocation foreignPrefix = ManaMod.prefix("block/" + blockPrefix(foreign).getPath());
        String blockPath = blockPrefix(block).getPath();
        wallInventory(blockPath + "_inventory", foreignPrefix);
        wallPost(blockPath + "_post", foreignPrefix);
        wallSide(blockPath + "_side", foreignPrefix);
        wallSideTall(blockPath + "_side_tall", foreignPrefix);
    }

    private void cakeBlock(Block block) {
        ResourceLocation sidePrefix = ManaMod.prefix("block/" + blockPrefix(block).getPath() + "_side");
        ResourceLocation bottomPrefix = ManaMod.prefix("block/" + blockPrefix(block).getPath() + "_bottom");
        ResourceLocation topPrefix = ManaMod.prefix("block/" + blockPrefix(block).getPath() + "_top");
        ResourceLocation innerPrefix = ManaMod.prefix("block/" + blockPrefix(block).getPath() + "_inner");
        String blockPath = blockPrefix(block).getPath();

        getBuilder(blockPath).texture("particle", sidePrefix).texture("bottom", bottomPrefix).texture("top", topPrefix).texture("side", sidePrefix).element().from(1, 0, 1).to(15, 8, 15).face(Direction.DOWN).texture("#bottom").cullface(Direction.DOWN).end().face(Direction.UP).texture("#top").end().face(Direction.NORTH).texture("#side").end().face(Direction.SOUTH).texture("#side").end().face(Direction.WEST).texture("#side").end().face(Direction.EAST).texture("#side").end();

        int number = 1;

        cakeSliceBuild(number++, 3, blockPath, sidePrefix, bottomPrefix, topPrefix, innerPrefix);
        cakeSliceBuild(number++, 5, blockPath, sidePrefix, bottomPrefix, topPrefix, innerPrefix);
        cakeSliceBuild(number++, 7, blockPath, sidePrefix, bottomPrefix, topPrefix, innerPrefix);
        cakeSliceBuild(number++, 9, blockPath, sidePrefix, bottomPrefix, topPrefix, innerPrefix);
        cakeSliceBuild(number++, 11, blockPath, sidePrefix, bottomPrefix, topPrefix, innerPrefix);
        cakeSliceBuild(number, 13, blockPath, sidePrefix, bottomPrefix, topPrefix, innerPrefix);
    }

    private void cakeSliceBuild(int number ,int fromX, String blockPath, ResourceLocation sidePrefix, ResourceLocation bottomPrefix, ResourceLocation topPrefix, ResourceLocation innerPrefix) {
        getBuilder(blockPath + number).texture("particle", sidePrefix).texture("bottom", bottomPrefix).texture("top", topPrefix).texture("side", sidePrefix).texture("inside", innerPrefix).element().from(fromX, 0, 1).to(15, 8, 15).face(Direction.DOWN).texture("#bottom").cullface(Direction.DOWN).end().face(Direction.UP).texture("#top").end().face(Direction.NORTH).texture("#side").end().face(Direction.SOUTH).texture("#side").end().face(Direction.WEST).texture("#inside").end().face(Direction.EAST).texture("#side").end();
    }

    private ResourceLocation blockPrefix(Block block) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block));
    }
}
