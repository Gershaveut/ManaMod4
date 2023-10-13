package com.gershaveut.manamod.world.item;

import com.gershaveut.manamod.world.level.block.MMBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.gershaveut.manamod.ManaMod.MODID;

public class MMItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

    public static final RegistryObject<Item> MANA = ITEMS.register("mana", () -> registerItem(new Item(new Item.Properties())));
    public static final RegistryObject<Item> FLINT_AND_MANA = ITEMS.register("flint_and_mana", () -> registerItem(new FlintAndSteelItem(new Item.Properties().durability(128))));
    public static final RegistryObject<Item> MAGIC_LIGHTER = ITEMS.register("magic_lighter", () -> registerItem(new Item(new Item.Properties().stacksTo(1)), new TooltipProperties().descriptionItem().UsageItem().WIP()));
    public static final RegistryObject<Item> MANA_BAG = ITEMS.register("mana_bag", () -> registerItem(new ManaBag(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)), new TooltipProperties().descriptionItem().WIP()));
    public static final RegistryObject<Item> MANA_FRIED = ITEMS.register("mana_fried", () -> registerItem(new ManaFried(new Item.Properties().food(new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(1.2F).build())), new TooltipProperties().descriptionItem()));
    public static final RegistryObject<Item> MANA_FUEL = ITEMS.register("mana_fuel", () -> registerItem(new FuelItem(new Item.Properties(), 32000), new TooltipProperties().descriptionItem()));
    public static final RegistryObject<Item> MANA_HEART = ITEMS.register("mana_heart", () -> registerItem(new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)), new TooltipProperties().WIP()));
    public static final RegistryObject<Item> MANA_INGOT = ITEMS.register("mana_ingot", () -> registerItem(new Item(new Item.Properties())));
    public static final RegistryObject<Item> MANA_PIECE = ITEMS.register("mana_piece", () -> registerItem(new Item(new Item.Properties())));
    public static final RegistryObject<Item> MANA_POTION = ITEMS.register("mana_potion", () -> registerItem(new ManaPotion(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).durability(3).food(new FoodProperties.Builder().alwaysEat().build())), new TooltipProperties().descriptionItem()));
    public static final RegistryObject<Item> MANA_SHARDS = ITEMS.register("mana_shards", () -> registerItem(new Item(new Item.Properties())));
    public static final RegistryObject<Item> MANA_STAFF = ITEMS.register("mana_staff", () -> registerItem(new Item(new Item.Properties().stacksTo(1)), new TooltipProperties().WIP()));
    public static final RegistryObject<Item> MANA_STICK = ITEMS.register("mana_stick", () -> registerItem(new Item(new Item.Properties())));
    public static final RegistryObject<Item> MANA_STONE = ITEMS.register("mana_stone", () -> registerItem(new ManaStone(new Item.Properties().stacksTo(1)), new TooltipProperties().descriptionItem().UsageItem()));
    public static final RegistryObject<Item> MANA_TALISMAN = ITEMS.register("mana_talisman", () -> registerItem(new ManaTalisman(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)), new TooltipProperties().descriptionItem().WIP()));
    public static final RegistryObject<Item> SUPER_STONE = ITEMS.register("super_stone", () -> registerItem(new Item(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)), new TooltipProperties().descriptionItem().UsageItem().WIP()));
    public static final RegistryObject<Item> UNSTABLE_MANA_PIECE = ITEMS.register("unstable_mana_piece", () -> registerItem(new Item(new Item.Properties())));
    public static final RegistryObject<Item> UNSTABLE_MANA_STICK = ITEMS.register("unstable_mana_stick", () -> registerItem(new Item(new Item.Properties())));
    public static final RegistryObject<Item> UNSTABLE_MANA_TALISMAN = ITEMS.register("unstable_mana_talisman", () -> registerItem(new UnstableManaTalisman(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)), new TooltipProperties().descriptionItem().UsageItem()));
    public static final RegistryObject<Item> MANA_BLOCK = ITEMS.register("mana_block", () -> registerItem(new BlockItem(MMBlocks.MANA_BLOCK.get(), new Item.Properties())));
    public static final RegistryObject<Item> MANA_BRICKS = ITEMS.register("mana_bricks", () -> registerItem(new BlockItem(MMBlocks.MANA_BRICKS.get(), new Item.Properties())));
    public static final RegistryObject<Item> CHISELED_MANA_BRICKS = ITEMS.register("chiseled_mana_bricks", () -> registerItem(new BlockItem(MMBlocks.CHISELED_MANA_BRICKS.get(), new Item.Properties())));
    public static final RegistryObject<Item> MANA_BRICK_STAIRS = ITEMS.register("mana_brick_stairs", () -> registerItem(new BlockItem(MMBlocks.MANA_BRICK_STAIRS.get(), new Item.Properties())));
    public static final RegistryObject<Item> MANA_BRICK_WALL = ITEMS.register("mana_brick_wall", () -> registerItem(new BlockItem(MMBlocks.MANA_BRICK_WALL.get(), new Item.Properties())));
    public static final RegistryObject<Item> MANA_BRICK_SLAB = ITEMS.register("mana_brick_slab", () -> registerItem(new BlockItem(MMBlocks.MANA_BRICK_SLAB.get(), new Item.Properties())));
    public static final RegistryObject<Item> MANA_CAKE = ITEMS.register("mana_cake", () -> registerItem(new BlockItem(MMBlocks.MANA_CAKE.get(), new Item.Properties().stacksTo(1)), new TooltipProperties().descriptionItem().WIP()));
    public static final RegistryObject<Item> MANA_ORE = ITEMS.register("mana_ore", () -> registerItem(new BlockItem(MMBlocks.MANA_ORE.get(), new Item.Properties()), new TooltipProperties().WIP()));
    public static final RegistryObject<Item> MANA_SHIELD = ITEMS.register("mana_shield", () -> registerItem(new BlockItem(MMBlocks.MANA_SHIELD.get(), new Item.Properties())));
    public static final RegistryObject<Item> MANA_SYNTHESIZER = ITEMS.register("mana_synthesizer", () -> registerItem(new BlockItem(MMBlocks.MANA_SYNTHESIZER.get(), new Item.Properties()), new TooltipProperties().WIP()));
    public static final RegistryObject<Item> UNSTABLE_MANA_BLOCK = ITEMS.register("unstable_mana_block", () -> registerItem(new BlockItem(MMBlocks.UNSTABLE_MANA_BLOCK.get(), new Item.Properties())));
    public static final RegistryObject<Item> MANA_SHOVEL = ITEMS.register("mana_shovel", () -> registerItem(new ShovelItem(MMTiers.MANA, 0.5F, 0F, new Item.Properties()), new TooltipProperties().WIP()));
    public static final RegistryObject<Item> MANA_SWORD = ITEMS.register("mana_sword", () -> registerItem(new SwordItem(MMTiers.MANA, 3, -1.9F, new Item.Properties()), new TooltipProperties().WIP()));
    public static final RegistryObject<Item> MANA_PICKAXE = ITEMS.register("mana_pickaxe", () -> registerItem(new PickaxeItem(MMTiers.MANA, 1, -2.3F, new Item.Properties()), new TooltipProperties().WIP()));
    public static final RegistryObject<Item> MANA_AXE = ITEMS.register("mana_axe", () -> registerItem(new AxeItem(MMTiers.MANA, 6F, -2.6F, new Item.Properties()), new TooltipProperties().WIP()));
    public static final RegistryObject<Item> MANA_HOE = ITEMS.register("mana_hoe", () -> registerItem(new HoeItem(MMTiers.MANA, -2, 0.5F, new Item.Properties()), new TooltipProperties().WIP()));
    public static final RegistryObject<Item> MANA_AMULET = ITEMS.register("mana_amulet", () -> registerItem(new ArmorItem(MMArmorMaterials.MANA_AMULET, ArmorItem.Type.CHESTPLATE, new Item.Properties().rarity(Rarity.RARE)), new TooltipProperties().WIP()));
    public static final RegistryObject<Item> MANA_DICE = ITEMS.register("mana_dice", () -> registerItem(new ManaDice(new Item.Properties().stacksTo(1).durability(1).rarity(MMRarity.MANA)), new TooltipProperties().descriptionItem()));
    public static final RegistryObject<Item> TERMINAL = ITEMS.register("terminal", () -> registerItem(new BlockItem(MMBlocks.TERMINAL.get(), new Item.Properties()), new TooltipProperties().WIP()));

    public static Item registerItem(Item item, TooltipProperties tooltipProperties) {
        if (FMLEnvironment.dist.isClient())
            return (Item) ((Tooltip) item).manaMod$setTooltipProperties(tooltipProperties);
        else
            return item;
    }
    
    public static Item registerItem(Item item) {
        return item;
    }
}
