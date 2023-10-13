package com.gershaveut.manamod.world.inventory;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.gershaveut.manamod.ManaMod.MODID;

public class MMMenuType {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MODID);
    
    public static final RegistryObject<MenuType<ManaBagMenu>> MANA_BAG_MENU = MENUS.register("mana_bag_menu", () -> new MenuType<>(ManaBagMenu::new, FeatureFlags.DEFAULT_FLAGS));
    public static final RegistryObject<MenuType<TerminalMenu>> TERMINAL_MENU = MENUS.register("terminal_menu", () -> new MenuType<>(TerminalMenu::new, FeatureFlags.DEFAULT_FLAGS));
}
