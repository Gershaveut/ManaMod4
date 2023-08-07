package com.gershaveut.mana_mod.world.item;

import com.gershaveut.mana_mod.ManaMod;
import com.gershaveut.mana_mod.world.level.block.Blocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.gershaveut.mana_mod.ManaMod.MODID;

@Mod.EventBusSubscriber(modid = ManaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> MANA = ITEMS.register("mana", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLINT_AND_MANA = ITEMS.register("flint_and_mana", () -> new FlintAndSteelItem(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> MAGIC_LIGHTER = ITEMS.register("magic_lighter", () -> (Item) ((Tooltip) new Item(new Item.Properties().stacksTo(1))).manaMod$setTooltipProperties(new TooltipProperties(true, true, true)));
    public static final RegistryObject<Item> MANA_BAG = ITEMS.register("mana_bag", () -> (Item) ((Tooltip) new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON))).manaMod$setTooltipProperties(new TooltipProperties(true, false, true)));
    public static final RegistryObject<Item> MANA_FRIED = ITEMS.register("mana_fried", () -> (Item) ((Tooltip) new Item(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(1.2F).build()))).manaMod$setTooltipProperties(new TooltipProperties(false, false, true)));
    public static final RegistryObject<Item> MANA_FUEL = ITEMS.register("mana_fuel", () -> (Item) ((Tooltip) new Item(new Item.Properties())).manaMod$setTooltipProperties(new TooltipProperties(true, false, true)));
    public static final RegistryObject<Item> MANA_HEAT = ITEMS.register("mana_heat", () -> (Item) ((Tooltip) new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE))).manaMod$setTooltipProperties(new TooltipProperties(false, false, true)));
    public static final RegistryObject<Item> MANA_INGOT = ITEMS.register("mana_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANA_PIECE = ITEMS.register("mana_piece", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANA_POTION = ITEMS.register("mana_potion", () -> (Item) ((Tooltip) new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).durability(3).food(new FoodProperties.Builder().alwaysEat().build()))).manaMod$setTooltipProperties(new TooltipProperties(true, false, true)));
    public static final RegistryObject<Item> MANA_SHARDS = ITEMS.register("mana_shards", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANA_STAFF = ITEMS.register("mana_staff", () -> (Item) ((Tooltip) new Item(new Item.Properties().stacksTo(1))).manaMod$setTooltipProperties(new TooltipProperties(false, false, true)));
    public static final RegistryObject<Item> MANA_STICK = ITEMS.register("mana_stick", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANA_STONE = ITEMS.register("mana_stone", () -> (Item) ((Tooltip) new Item(new Item.Properties().stacksTo(1))).manaMod$setTooltipProperties(new TooltipProperties(true, true, true)));
    public static final RegistryObject<Item> MANA_TALISMAN = ITEMS.register("mana_talisman", () -> (Item) ((Tooltip) new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON))).manaMod$setTooltipProperties(new TooltipProperties(true, true, true)));
    public static final RegistryObject<Item> SUPER_STONE = ITEMS.register("super_stone", () -> (Item) ((Tooltip) new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE))).manaMod$setTooltipProperties(new TooltipProperties(true, true, true)));
    public static final RegistryObject<Item> UNSTABLE_MANA_PIECE = ITEMS.register("unstable_mana_piece", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNSTABLE_MANA_STICK = ITEMS.register("unstable_mana_stick", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNSTABLE_MANA_TALISMAN = ITEMS.register("unstable_mana_talisman", () -> (Item) ((Tooltip) new Item(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON))).manaMod$setTooltipProperties(new TooltipProperties(true, true, true)));
    public static final RegistryObject<Item> MANA_BLOCK = ITEMS.register("mana_block", () -> new BlockItem(Blocks.MANA_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANA_BRICKS = ITEMS.register("mana_bricks", () -> new BlockItem(Blocks.MANA_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> CHISELED_MANA_BRICKS = ITEMS.register("chiseled_mana_bricks", () -> new BlockItem(Blocks.CHISELED_MANA_BRICKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANA_BRICK_WALL = ITEMS.register("mana_brick_wall", () -> new BlockItem(Blocks.MANA_BRICK_WALL.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANA_BRICK_SLAB = ITEMS.register("mana_brick_slab", () -> new BlockItem(Blocks.MANA_BRICK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANA_CAKE = ITEMS.register("mana_cake", () -> (BlockItem) ((Tooltip) new BlockItem(Blocks.MANA_CAKE.get(), new Item.Properties().stacksTo(1))).manaMod$setTooltipProperties(new TooltipProperties(true, false, true)));
    public static final RegistryObject<Item> MANA_ORE = ITEMS.register("mana_ore", () -> (BlockItem) ((Tooltip) new BlockItem(Blocks.MANA_ORE.get(), new Item.Properties())).manaMod$setTooltipProperties(new TooltipProperties(false, false, true)));
    public static final RegistryObject<Item> MANA_SHIELD = ITEMS.register("mana_shield", () -> new BlockItem(Blocks.MANA_BRICK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> MANA_SYNTHESIZER = ITEMS.register("mana_synthesizer", () -> (BlockItem) ((Tooltip) new BlockItem(Blocks.MANA_SYNTHESIZER.get(), new Item.Properties())).manaMod$setTooltipProperties(new TooltipProperties(false, false, true)));
    public static final RegistryObject<Item> UNSTABLE_MANA_BLOCK = ITEMS.register("unstable_mana_block", () -> new BlockItem(Blocks.UNSTABLE_MANA_BLOCK.get(), new Item.Properties()));
}
