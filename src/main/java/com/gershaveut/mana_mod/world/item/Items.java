package com.gershaveut.mana_mod.world.item;

import com.gershaveut.mana_mod.ManaMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.gershaveut.mana_mod.ManaMod.MODID;

@Mod.EventBusSubscriber(modid = ManaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Items {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> MANA = ITEMS.register("mana", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLINT_AND_MANA = ITEMS.register("flint_and_mana", () -> new FlintAndSteelItem(new Item.Properties().durability(128)));
    public static final RegistryObject<Item> MAGIC_LIGHTER = registerTooltipItem("magic_lighter", new Item.Properties().stacksTo(1), true, true, true);
    public static final RegistryObject<Item> MANA_BAG = registerTooltipItem("mana_bag", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), true, false, true);
    public static final RegistryObject<Item> MANA_FRIED = registerTooltipItem("mana_fried", new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(1.2F).build()), false, false, true);
    public static final RegistryObject<Item> MANA_FUEL = registerTooltipItem("mana_fuel", new Item.Properties(), true, false, true);
    public static final RegistryObject<Item> MANA_HEAT = registerTooltipItem("mana_heat", new Item.Properties().stacksTo(1).rarity(Rarity.RARE), false);
    public static final RegistryObject<Item> MANA_INGOT = ITEMS.register("mana_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANA_PIECE = ITEMS.register("mana_piece", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANA_POTION = registerTooltipItem("mana_potion", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).durability(3).food(new FoodProperties.Builder().alwaysEat().build()), true, false, true);
    public static final RegistryObject<Item> MANA_SHARDS = ITEMS.register("mana_shards", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANA_STAFF = registerTooltipItem("mana_staff", new Item.Properties().stacksTo(1), false, false, true);
    public static final RegistryObject<Item> MANA_STICK = ITEMS.register("mana_stick", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANA_STONE = registerTooltipItem("mana_stone", new Item.Properties().stacksTo(1), true, true, true);
    public static final RegistryObject<Item> MANA_TALISMAN = registerTooltipItem("mana_talisman", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), true, true, true);
    public static final RegistryObject<Item> SUPER_STONE = registerTooltipItem("super_stone", new Item.Properties().stacksTo(1).rarity(Rarity.RARE), true, true, true);
    public static final RegistryObject<Item> UNSTABLE_MANA_PIECE = ITEMS.register("unstable_mana_piece", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNSTABLE_MANA_STICK = ITEMS.register("unstable_mana_stick", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> UNSTABLE_MANA_TALISMAN = registerTooltipItem("unstable_mana_talisman", new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON), true, true, true);

    public static RegistryObject<Item> registerTooltipItem(String name, Item.Properties properties) {
        return ITEMS.register(name, () -> new TooltipItem(properties, name));
    }
    public static RegistryObject<Item> registerTooltipItem(String name, Item.Properties properties, boolean descriptionItem) {
        return ITEMS.register(name, () -> new TooltipItem(properties, name, descriptionItem));
    }

    public static RegistryObject<Item> registerTooltipItem(String name, Item.Properties properties, boolean descriptionItem, boolean UsageItem) {
        return ITEMS.register(name, () -> new TooltipItem(properties, name, descriptionItem, UsageItem));
    }

    public static RegistryObject<Item> registerTooltipItem(String name, Item.Properties properties, boolean descriptionItem, boolean UsageItem, boolean WIP) {
        return ITEMS.register(name, () -> new TooltipItem(properties, name, descriptionItem, UsageItem, WIP));
    }
}
