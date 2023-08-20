package com.gershaveut.manamod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = ManaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    
    private static final ForgeConfigSpec.BooleanValue ITEM_COOLDOWN = BUILDER.define("itemCooldown", true);
    
    public static final ForgeConfigSpec SPEC = BUILDER.build();
    
    public static boolean itemCooldown;
    
    @SubscribeEvent
    public static void onLoad(final ModConfigEvent event) {
        itemCooldown = ITEM_COOLDOWN.get();
    }
}
