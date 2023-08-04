package com.gershaveut.mana_mod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = ManaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue ITEM_COOLDOWN = BUILDER
            .define("itemCooldown", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean itemCooldown;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        itemCooldown = ITEM_COOLDOWN.get();
    }
}
