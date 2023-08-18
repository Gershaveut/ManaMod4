package com.gershaveut.manamod.data.recipes.packs;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class MMRecipeProvider extends RecipeProvider {
    public MMRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }
    
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> finishedRecipe) {
    }
}
