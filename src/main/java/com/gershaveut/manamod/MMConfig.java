package com.gershaveut.manamod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = ManaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMConfig {
    public static List<? extends Float> terminalColor;
    public static boolean itemCooldown;
    
    public static class MMConfigClient {
        private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        
        private static final ForgeConfigSpec.ConfigValue<List<? extends Float>> TERMINAL_COLOR = BUILDER.defineList("terminalColor", Arrays.asList(1.0F, 1.0F, 1.0F, 1.0F), s -> true);
        
        public static final ForgeConfigSpec SPEC = BUILDER.build();
    }
    
    public static class MMConfigServer {
        private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        
        private static final ForgeConfigSpec.BooleanValue ITEM_COOLDOWN = BUILDER.define("itemCooldown", true);
        
        public static final ForgeConfigSpec SPEC = BUILDER.build();
    }
    
    @SubscribeEvent
    public static void onLoad(final ModConfigEvent event) {
        if (event.getConfig().getType() == ModConfig.Type.CLIENT) {
            if (MMConfigClient.TERMINAL_COLOR.get().get(0) instanceof Float) ;
            
            else throw new RuntimeException("No float");
            
        }
        
        if (event.getConfig().getType() == ModConfig.Type.SERVER) {
            MMConfig.itemCooldown = MMConfigServer.ITEM_COOLDOWN.get();
        }
    }
}
