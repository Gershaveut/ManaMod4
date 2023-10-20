package com.gershaveut.manamod;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = ManaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMConfig {
    public static final Client CLIENT = new Client();
    public static final Server SERVER = new Server();
    
    public static class Client {
        private final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        
        public final ForgeConfigSpec.ConfigValue<List<? extends Double>> TERMINAL_COLOR = BUILDER.defineList("terminalColor", Arrays.asList(1.0D, 1.0D, 1.0D, 1.0D), d -> d instanceof Double);
        
        public final ForgeConfigSpec SPEC = BUILDER.build();
    }
    
    public static class Server {
        private final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        
        public final ForgeConfigSpec.BooleanValue ITEM_COOLDOWN = BUILDER.define("itemCooldown", true);
        
        public final ForgeConfigSpec SPEC = BUILDER.build();
    }
}
