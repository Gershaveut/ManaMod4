package com.gershaveut.manamod.data;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.client.KeyMappings;
import com.gershaveut.manamod.world.effect.MMMobEffects;
import com.gershaveut.manamod.world.item.MMCreativeModeTabs;
import com.gershaveut.manamod.world.item.MMItems;
import com.gershaveut.manamod.world.level.block.MMBlocks;
import net.minecraft.client.KeyMapping;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;

public class MMLanguageProvider extends LanguageProvider {
    public MMLanguageProvider(PackOutput output) {
        super(output, ManaMod.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add(MMCreativeModeTabs.GENERAL.get(), "Mana Mod");

        add("item." + ManaMod.MODID + ".description_view", "§7Hold§f {key} §7for a detailed description");
        add("item." + ManaMod.MODID + ".usage_view", "§7Hold§f {key} §7for a usage");
        add("item." + ManaMod.MODID + ".feedback.error", "You cannot use the item in this situation");

        add(MMItems.MANA.get(), "Mana");
        add(MMItems.FLINT_AND_MANA.get(), "Flint And Mana");
        add(MMItems.MAGIC_LIGHTER.get(), "Magic Lighter", "Allows you to teleport to a new dimension");
        add(MMItems.MANA_BAG.get(), "Mana Bag");
        add(MMItems.MANA_FRIED.get(), "Mana Fried");
        add(MMItems.MANA_FUEL.get(), "Mana Fuel", "Fuel time: §f32000");
        add(MMItems.MANA_HEAT.get(), "Mana Heat");
        add(MMItems.MANA_INGOT.get(), "Mana Ingot");
        add(MMItems.MANA_PIECE.get(), "Mana Piece");
        add(MMItems.MANA_POTION.get(), "Mana Potion", "Allows you to fly");
        add(MMItems.MANA_SHARDS.get(), "Mana Shards");
        add(MMItems.MANA_STAFF.get(), "Mana Staff");
        add(MMItems.MANA_STICK.get(), "Mana Stick");
        add(MMItems.MANA_STONE.get(), "Mana Stone", "Teleports to the world spawn");
        add(MMItems.MANA_TALISMAN.get(), "Mana Talisman", "Allows you to change the weather", new Feedback("rain", "Rain"), new Feedback("clear", "Clear"), new Feedback("thunder", "Thunder"));
        add(MMItems.SUPER_STONE.get(), "Super Stone", "Allows you to create a list of places to teleport to them");
        add(MMItems.UNSTABLE_MANA_PIECE.get(), "Unstable Mana Piece");
        add(MMItems.UNSTABLE_MANA_STICK.get(), "Unstable Mana Stick");
        add(MMItems.UNSTABLE_MANA_TALISMAN.get(), "Unstable Mana Talisman", "Allows you to change the time of day", new Feedback("day", "Day"), new Feedback("night", "Night"));
        add(MMBlocks.MANA_BLOCK.get(), "Mana Block");
        add(MMBlocks.MANA_BRICKS.get(), "Mana Bricks");
        add(MMBlocks.CHISELED_MANA_BRICKS.get(), "Chiseled Mana Bricks");
        add(MMBlocks.MANA_BRICK_STAIRS.get(), "Mana Brick Stairs");
        add(MMBlocks.MANA_BRICK_WALL.get(), "Mana Brick Wall");
        add(MMBlocks.MANA_BRICK_SLAB.get(), "Mana Brick Slab");
        add(MMBlocks.MANA_CAKE.get(), "Mana Cake", "The cake is restored if you click on it with a mana");
        add(MMBlocks.MANA_ORE.get(), "Mana Ore");
        add(MMBlocks.MANA_SHIELD.get(), "Mana Shield");
        add(MMBlocks.MANA_SYNTHESIZER.get(), "Mana Synthesizer");
        add(MMBlocks.UNSTABLE_MANA_BLOCK.get(), "Unstable Mana Block");
        add(MMItems.MANA_SHOVEL.get(), "Mana Shovel");
        add(MMItems.MANA_SWORD.get(), "Mana sword");
        add(MMItems.MANA_PICKAXE.get(), "Mana Pickaxe");
        add(MMItems.MANA_AXE.get(), "Mana Axe");
        add(MMItems.MANA_HOE.get(), "Mana Hoe");
        add(MMItems.MANA_AMULET.get(), "Mana Amulet");

        add(MMMobEffects.MANA_FLIGHT.get(), "Mana Flight");
        add(MMMobEffects.MANAIFICATION.get(), "Manaification");

        add("key.categories." + ManaMod.MODID, "Mana Mod");

        add(KeyMappings.KEY_DESCRIPTION_ITEM, "Item description");
        add(KeyMappings.KEY_USAGE_ITEM, "Using the item");
    }

    private void add(CreativeModeTab creativeModeTab, String name) {
        add(creativeModeTab.getDisplayName().getString(), name);
    }

    private void add(KeyMapping keyMapping, String name) {
        add(keyMapping.getName(), name);
    }

    private void add(String key, String name, String description, Feedback... feedbacks) {
        add(key, name, description);
        for (Feedback feedback : feedbacks) {
            add(key + ".feedback." + feedback.name, feedback.message);
        }
    }

    private void add(String key, String name, String description) {
        super.add(key, name);
        add(key + ".description", description);
    }

    private void add(Item item, String name, String description) {
        add(item.getDescriptionId(), name, description);
    }

    private void add(Item item, String name, String description, Feedback... feedbacks) {
        add(item.getDescriptionId(), name, description, feedbacks);
    }

    private void add(Block block, String name, String description) {
        add(block.getDescriptionId(), name, description);
    }

    private void add(Block block, String name, String description, Feedback... feedbacks) {
        add(block.getDescriptionId(), name, description, feedbacks);
    }

    private record Feedback(String name, String message) {
    }
}
