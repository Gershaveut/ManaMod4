package com.gershaveut.mana_mod.world.item;

import com.gershaveut.mana_mod.ManaMod;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.Item;
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

    public static final RegistryObject<Item> MANA = ITEMS.register("mana", () -> new TooltipItem(new Item.Properties()));
    public static final RegistryObject<Item> FLINT_AND_MANA = ITEMS.register("flint_and_mana", () -> new FlintAndSteelItem(new Item.Properties()));
}
