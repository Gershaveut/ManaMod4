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
            }).build());
}
