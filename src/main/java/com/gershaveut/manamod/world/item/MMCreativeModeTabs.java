package com.gershaveut.manamod.world.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.gershaveut.manamod.ManaMod.MODID;

public class MMCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> GENERAL = CREATIVE_MODE_TABS.register("general", () -> CreativeModeTab.builder()
            .withTabsBefore(net.minecraft.world.item.CreativeModeTabs.COMBAT)
            .title(Component.translatable("item_group.mana_mod.general"))
            .icon(() -> MMItems.MANA.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(MMItems.MANA.get());
                output.accept(MMItems.FLINT_AND_MANA.get());
                output.accept(MMItems.MAGIC_LIGHTER.get());
                output.accept(MMItems.MANA_BAG.get());
                output.accept(MMItems.MANA_FRIED.get());
                output.accept(MMItems.MANA_FUEL.get());
                output.accept(MMItems.MANA_HEAT.get());
                output.accept(MMItems.MANA_INGOT.get());
                output.accept(MMItems.MANA_PIECE.get());
                output.accept(MMItems.MANA_POTION.get());
                output.accept(MMItems.MANA_SHARDS.get());
                output.accept(MMItems.MANA_STAFF.get());
                output.accept(MMItems.MANA_STICK.get());
                output.accept(MMItems.MANA_STONE.get());
                output.accept(MMItems.MANA_TALISMAN.get());
                output.accept(MMItems.SUPER_STONE.get());
                output.accept(MMItems.UNSTABLE_MANA_PIECE.get());
                output.accept(MMItems.UNSTABLE_MANA_STICK.get());
                output.accept(MMItems.UNSTABLE_MANA_TALISMAN.get());
                output.accept(MMItems.MANA_BLOCK.get());
                output.accept(MMItems.MANA_BRICKS.get());
                output.accept(MMItems.CHISELED_MANA_BRICKS.get());
                output.accept(MMItems.MANA_BRICK_STAIRS.get());
                output.accept(MMItems.MANA_BRICK_WALL.get());
                output.accept(MMItems.MANA_BRICK_SLAB.get());
                output.accept(MMItems.MANA_CAKE.get());
                output.accept(MMItems.MANA_ORE.get());
                output.accept(MMItems.MANA_SYNTHESIZER.get());
                output.accept(MMItems.UNSTABLE_MANA_BLOCK.get());
                output.accept(MMItems.MANA_SHOVEL.get());
                output.accept(MMItems.MANA_SWORD.get());
                output.accept(MMItems.MANA_PICKAXE.get());
                output.accept(MMItems.MANA_AXE.get());
                output.accept(MMItems.MANA_HOE.get());
                output.accept(MMItems.MANA_AMULET.get());
            }).build());
}
