package com.gershaveut.manamod.data.recipes;

import com.gershaveut.manamod.ManaMod;
import com.google.gson.JsonObject;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Consumer;

public class StonecutterRecipeBuilder {
    public static Result stonecutting(ItemLike input, ItemLike output) {
        return stonecutting(input, output, 1);
    }
    
    public static Result stonecutting(ItemLike input, ItemLike output, int count) {
        return new Result(getIdFor(input.asItem(), output.asItem()), Ingredient.of(input), output.asItem(), count);
    }
    
    public static ResourceLocation getIdFor(Item input, Item output) {
        return ManaMod.prefix(String.format("stonecutting/%s/%s", Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(input)).getPath(), Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(output)).getPath()));
    }
    
    public static class Result extends SingleItemRecipeBuilder.Result {
        public Result(ResourceLocation id, Ingredient input, Item output, int count) {
            super(id, RecipeSerializer.STONECUTTER, "", input, output, count, null, null);
        }
        
        public void save(Consumer<FinishedRecipe> finishedRecipe) {
            finishedRecipe.accept(this);
        }
        
        @Nullable
        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }
        
        @Nullable
        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
