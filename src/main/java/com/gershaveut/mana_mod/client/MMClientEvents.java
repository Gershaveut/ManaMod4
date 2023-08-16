package com.gershaveut.mana_mod.client;

import com.gershaveut.mana_mod.ManaMod;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegisterEvent;
import org.lwjgl.glfw.GLFW;
import org.slf4j.event.Level;

import static com.gershaveut.mana_mod.ManaMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class MMClientEvents {
    @SubscribeEvent
    public void keyInput(InputEvent.Key event) {
        ManaMod.Log(Level.DEBUG, String.valueOf(event.getKey()));
    }
    
    /*
    public static void registerEvents(RegisterEvent event) {
        event.register(MMClientEvents.class);
        MinecraftForge.EVENT_BUS.register(this);
    }
     */
    
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class MMStaticClientEvents {
        public static final KeyMapping KEY_DESCRIPTION_ITEM = new KeyMapping("key.mana_mod.key_description_item", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "key.categories.mana_mod");
        public static final KeyMapping KEY_USAGE_ITEM = new KeyMapping("key.mana_mod.key_usage_item", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_CONTROL, "key.categories.mana_mod");
        
        @SubscribeEvent
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            event.register(KEY_DESCRIPTION_ITEM);
            event.register(KEY_USAGE_ITEM);
        }
    }
}
