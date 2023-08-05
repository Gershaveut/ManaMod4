package com.gershaveut.mana_mod.world.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static com.gershaveut.mana_mod.ManaMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> GENERAL = CREATIVE_MODE_TABS.register("general", () -> CreativeModeTab.builder()
            .withTabsBefore(net.minecraft.world.item.CreativeModeTabs.COMBAT)
            .title(Component.translatable("item_group.mana_mod.general"))
            .icon(() -> Items.MANA.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(Items.MANA.get());
                output.accept(Items.FLINT_AND_MANA.get());
                output.accept(Items.MAGIC_LIGHTER.get());
                output.accept(Items.MANA_BAG.get());
                output.accept(Items.MANA_FRIED.get());
                output.accept(Items.MANA_FUEL.get());
                output.accept(Items.MANA_HEAT.get());
                output.accept(Items.MANA_INGOT.get());
                output.accept(Items.MANA_PIECE.get());
                output.accept(Items.MANA_POTION.get());
                output.accept(Items.MANA_SHARDS.get());
                output.accept(Items.MANA_STAFF.get());
                output.accept(Items.MANA_STICK.get());
                output.accept(Items.MANA_STONE.get());
                output.accept(Items.MANA_TALISMAN.get());
                output.accept(Items.SUPER_STONE.get());
                output.accept(Items.UNSTABLE_MANA_PIECE.get());
                output.accept(Items.UNSTABLE_MANA_STICK.get());
                output.accept(Items.UNSTABLE_MANA_TALISMAN.get());
            }).build());
}
