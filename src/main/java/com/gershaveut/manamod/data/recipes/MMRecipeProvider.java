package com.gershaveut.manamod.data.recipes;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.item.MMItems;
import com.gershaveut.manamod.world.level.block.MMBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Consumer;

public class MMRecipeProvider extends RecipeProvider {
    public MMRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }
    
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> finishedRecipe) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.CHISELED_MANA_BRICKS.get())
                .pattern("S")
                .pattern("S")
                .define('S', MMBlocks.MANA_BRICK_SLAB.get())
                .unlockedBy("has_item", has(MMBlocks.CHISELED_MANA_BRICKS.get()))
                .save(finishedRecipe);
        StonecutterRecipeBuilder.stonecutting(MMBlocks.MANA_BRICKS.get(), MMBlocks.CHISELED_MANA_BRICKS.get()).save(finishedRecipe);
        StonecutterRecipeBuilder.stonecutting(MMBlocks.MANA_BLOCK.get(), MMBlocks.CHISELED_MANA_BRICKS.get()).save(finishedRecipe);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TOOLS, MMItems.FLINT_AND_MANA.get())
                .requires(MMItems.MANA_INGOT.get())
                .requires(Items.FLINT)
                .unlockedBy("has_item", has(MMItems.FLINT_AND_MANA.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.MAGIC_LIGHTER.get())
                .pattern("  F")
                .pattern(" S ")
                .pattern("S  ")
                .define('S', MMItems.MANA_STICK.get())
                .define('F', MMItems.MANA_FUEL.get())
                .unlockedBy("has_item", has(MMItems.MAGIC_LIGHTER.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, MMItems.MANA_AMULET.get())
                .pattern("GSG")
                .pattern("GHG")
                .pattern("GGG")
                .define('G', Items.GOLD_INGOT)
                .define('S', Items.STRING)
                .define('H', MMItems.MANA_HEAT.get())
                .unlockedBy("has_item", has(MMItems.MANA_AMULET.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.MANA_AXE.get())
                .pattern("II ")
                .pattern("IS ")
                .pattern(" S ")
                .define('S', MMItems.MANA_STICK.get())
                .define('I', MMItems.MANA_INGOT.get())
                .unlockedBy("has_item", has(MMItems.MANA_AXE.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.MANA_BAG.get())
                .pattern("M M")
                .pattern("MPM")
                .pattern("MMM")
                .define('M', MMItems.MANA.get())
                .define('P', MMItems.MANA_PIECE.get())
                .unlockedBy("has_item", has(MMItems.MANA_BAG.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.MANA_BRICK_SLAB.get(), 6)
                .pattern("BBB")
                .define('B', MMBlocks.MANA_BRICKS.get())
                .unlockedBy("has_item", has(MMBlocks.MANA_BRICK_SLAB.get()))
                .save(finishedRecipe);
        StonecutterRecipeBuilder.stonecutting(MMBlocks.MANA_BRICKS.get(), MMBlocks.MANA_BRICK_SLAB.get(), 2).save(finishedRecipe);
        StonecutterRecipeBuilder.stonecutting(MMBlocks.MANA_BLOCK.get(), MMBlocks.MANA_BRICK_SLAB.get(), 2).save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMItems.MANA_BRICK_STAIRS.get(), 4)
                .pattern("B  ")
                .pattern("BB ")
                .pattern("BBB")
                .define('B', MMBlocks.MANA_BRICKS.get())
                .unlockedBy("has_item", has(MMBlocks.MANA_BRICK_STAIRS.get()))
                .save(finishedRecipe);
        StonecutterRecipeBuilder.stonecutting(MMBlocks.MANA_BRICKS.get(), MMBlocks.MANA_BRICK_STAIRS.get()).save(finishedRecipe);
        StonecutterRecipeBuilder.stonecutting(MMBlocks.MANA_BLOCK.get(), MMBlocks.MANA_BRICK_STAIRS.get()).save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.MANA_BRICK_WALL.get(), 6)
                .pattern("BBB")
                .pattern("BBB")
                .define('B', MMBlocks.MANA_BRICKS.get())
                .unlockedBy("has_item", has(MMBlocks.MANA_BRICK_WALL.get()))
                .save(finishedRecipe);
        StonecutterRecipeBuilder.stonecutting(MMBlocks.MANA_BRICKS.get(), MMBlocks.MANA_BRICK_WALL.get()).save(finishedRecipe);
        StonecutterRecipeBuilder.stonecutting(MMBlocks.MANA_BLOCK.get(), MMBlocks.MANA_BRICK_WALL.get()).save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, MMBlocks.MANA_BRICKS.get(), 4)
                .pattern("BB")
                .pattern("BB")
                .define('B', MMBlocks.MANA_BLOCK.get())
                .unlockedBy("has_item", has(MMBlocks.MANA_BRICKS.get()))
                .save(finishedRecipe);
        StonecutterRecipeBuilder.stonecutting(MMBlocks.MANA_BLOCK.get(), MMBlocks.MANA_BRICKS.get()).save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, MMBlocks.MANA_CAKE.get())
                .pattern("MMM")
                .pattern("SES")
                .pattern("CCC")
                .define('M', Items.MILK_BUCKET)
                .define('S', Items.SUGAR)
                .define('C', MMItems.MANA.get())
                .define('E', Items.EGG)
                .unlockedBy("has_item", has(MMBlocks.MANA_CAKE.get()))
                .save(finishedRecipe);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(MMItems.MANA.get()), RecipeCategory.FOOD, MMItems.MANA_FRIED.get(), 0.5f, 225)
                .unlockedBy("has_item", has(MMItems.MANA_FRIED.get()))
                .save(finishedRecipe);
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(MMItems.MANA.get()), RecipeCategory.FOOD, MMItems.MANA_FRIED.get(), 0.5f, 675)
                .unlockedBy("has_item", has(MMItems.MANA_FRIED.get()))
                .save(finishedRecipe, ManaMod.prefix(MMItems.MANA_FRIED.get() + "_from_campfire_cooking"));
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(MMItems.MANA.get()), RecipeCategory.FOOD, MMItems.MANA_FRIED.get(), 0.5f, 113)
                .unlockedBy("has_item", has(MMItems.MANA_FRIED.get()))
                .save(finishedRecipe, ManaMod.prefix(MMItems.MANA_FRIED.get() + "_from_smoking"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BREWING, MMItems.MANA_FUEL.get())
                .pattern("M")
                .pattern("C")
                .pattern("M")
                .define('M', MMItems.MANA.get())
                .define('C', Blocks.COAL_BLOCK)
                .unlockedBy("has_item", has(MMItems.MANA_FUEL.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.MANA_HOE.get())
                .pattern("II ")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', MMItems.MANA_STICK.get())
                .define('I', MMItems.MANA_INGOT.get())
                .unlockedBy("has_item", has(MMItems.MANA_HOE.get()))
                .save(finishedRecipe);
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(MMItems.MANA_ORE.get()), RecipeCategory.BREWING, MMItems.MANA_INGOT.get(), 1, 125)
                .unlockedBy("has_item", has(MMItems.MANA_INGOT.get()))
                .save(finishedRecipe, ManaMod.prefix(MMItems.MANA_INGOT.get() + "_from_blasting_deepslate"));
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(MMItems.MANA_ORE.get()), RecipeCategory.BREWING, MMItems.MANA_INGOT.get(), 1.0f, 250)
                .unlockedBy("has_item", has(MMItems.MANA_INGOT.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.MANA_PICKAXE.get())
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', MMItems.MANA_STICK.get())
                .define('I', MMItems.MANA_INGOT.get())
                .unlockedBy("has_item", has(MMItems.MANA_PICKAXE.get()))
                .save(finishedRecipe);
        StonecutterRecipeBuilder.stonecutting(MMItems.MANA_ORE.get(), MMItems.MANA_SHARDS.get(), 1).save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.MANA_SHOVEL.get())
                .pattern("I")
                .pattern("S")
                .pattern("S")
                .define('S', MMItems.MANA_STICK.get())
                .define('I', MMItems.MANA_INGOT.get())
                .unlockedBy("has_item", has(MMItems.MANA_SHOVEL.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, MMItems.MANA_STICK.get())
                .pattern("  M")
                .pattern(" M ")
                .pattern("M  ")
                .define('M', MMItems.MANA.get())
                .unlockedBy("has_item", has(MMItems.MANA_STICK.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.MANA_STONE.get())
                .pattern(" P ")
                .pattern("PIP")
                .pattern(" P ")
                .define('P', MMItems.MANA_PIECE.get())
                .define('I', MMItems.MANA_INGOT.get())
                .unlockedBy("has_item", has(MMItems.MANA_STONE.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.MANA_SWORD.get())
                .pattern("I")
                .pattern("I")
                .pattern("S")
                .define('S', MMItems.MANA_STICK.get())
                .define('I', MMItems.MANA_INGOT.get())
                .unlockedBy("has_item", has(MMItems.MANA_SWORD.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, MMItems.MANA_SYNTHESIZER.get())
                .pattern("III")
                .pattern("IAI")
                .pattern("III")
                .define('A', Items.ANVIL)
                .define('I', MMItems.MANA_INGOT.get())
                .unlockedBy("has_item", has(MMItems.MANA_SYNTHESIZER.get()))
                .save(finishedRecipe);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, MMItems.MANA_TALISMAN.get(), 1)
                .pattern("  P")
                .pattern(" S ")
                .pattern("P  ")
                .define('P', MMItems.MANA_PIECE.get())
                .define('S', MMItems.MANA_STICK.get())
                .unlockedBy("has_item", has(MMItems.MANA_TALISMAN.get()))
                .save(finishedRecipe);
    }
}
