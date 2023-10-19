package com.gershaveut.manamod.data.models;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.item.MMItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class MMItemModelProvider extends ItemModelProvider {
    public MMItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ManaMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(MMItems.MANA.get());
        basicItem(MMItems.FLINT_AND_MANA.get());
        handheldItem(MMItems.MAGIC_LIGHTER.get());
        basicItem(MMItems.MANA_BAG.get());
        basicItem(MMItems.MANA_FRIED.get());
        basicItem(MMItems.MANA_FUEL.get());
        basicItem(MMItems.MANA_HEART.get());
        basicItem(MMItems.MANA_INGOT.get());
        basicItem(MMItems.MANA_PIECE.get());
        manaPotion();
        basicItem(MMItems.MANA_SHARDS.get());
        basicItem(MMItems.MANA_STAFF.get());
        basicItem(MMItems.MANA_STICK.get());
        basicItem(MMItems.MANA_STONE.get());
        basicItem(MMItems.MANA_TALISMAN.get());
        basicItem(MMItems.SUPER_STONE.get());
        basicItem(MMItems.UNSTABLE_MANA_PIECE.get());
        basicItem(MMItems.UNSTABLE_MANA_STICK.get());
        basicItem(MMItems.UNSTABLE_MANA_TALISMAN.get());
        blockItem(MMItems.MANA_BLOCK.get());
        blockItem(MMItems.MANA_BRICKS.get());
        blockItem(MMItems.CHISELED_MANA_BRICKS.get());
        blockItem(MMItems.MANA_BRICK_STAIRS.get());
        wallItem(MMItems.MANA_BRICK_WALL.get());
        blockItem(MMItems.MANA_BRICK_SLAB.get());
        basicItem(MMItems.MANA_CAKE.get());
        blockItem(MMItems.MANA_ORE.get());
        blockItem(MMItems.MANA_SHIELD.get());
        blockItem(MMItems.MANA_SYNTHESIZER.get());
        blockItem(MMItems.UNSTABLE_MANA_BLOCK.get());
        handheldItem(MMItems.MANA_SHOVEL.get());
        handheldItem(MMItems.MANA_SWORD.get());
        handheldItem(MMItems.MANA_PICKAXE.get());
        handheldItem(MMItems.MANA_AXE.get());
        handheldItem(MMItems.MANA_HOE.get());
        basicItem(MMItems.MANA_AMULET.get());
        basicItem(MMItems.MANA_DICE.get());
        blockItem(MMItems.TERMINAL.get());
    }

    private void blockItem(Item item) {
        ResourceLocation resourceLocation = itemPrefix(item);
        getBuilder(resourceLocation.toString()).parent(new ModelFile.UncheckedModelFile(ManaMod.MODID + ":block/" + resourceLocation.getPath()));
    }

    private void handheldItem(Item item) {
        getBuilder(itemPrefix(item).toString()).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture("layer0", texturePrefix(item));
    }
    
    private void wallItem(Item item) {
        getBuilder(item.toString()).parent(new ModelFile.UncheckedModelFile(ManaMod.MODID + ":block/" + item + "_inventory"));
    }
    
    private void manaPotion() {
        getBuilder(itemPrefix(MMItems.MANA_POTION.get()).toString()).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", texturePrefix(MMItems.MANA_POTION.get(), "_0")).override().predicate(new ResourceLocation("damage"), 0.3F).model(withExistingParent(MMItems.MANA_POTION.get() + "_1", ManaMod.prefix(MMItems.MANA_POTION.get().toString())).texture("layer0", texturePrefix(MMItems.MANA_POTION.get(), "_1"))).end().override().predicate(new ResourceLocation("damage"), 0.6F).model(withExistingParent(MMItems.MANA_POTION.get() + "_2", ManaMod.prefix(MMItems.MANA_POTION.get().toString())).texture("layer0", texturePrefix(MMItems.MANA_POTION.get(), "_2"))).end();
    }

    private ResourceLocation itemPrefix(Item item) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item));
    }

    private ResourceLocation texturePrefix(Item item) {
        return new ResourceLocation(itemPrefix(item).getNamespace(), "item/" + itemPrefix(item).getPath());
    }

    private ResourceLocation texturePrefix(Item item, String addition) {
        return new ResourceLocation(itemPrefix(item).getNamespace(), "item/" + itemPrefix(item).getPath() + addition);
    }
}
