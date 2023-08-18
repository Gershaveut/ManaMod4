package com.gershaveut.manamod.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static com.gershaveut.manamod.ManaMod.MODID;

public class MMClientEvents {
    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class MMClientForgeEvents {
        @SubscribeEvent
        public static void onKeyPressed(ScreenEvent.KeyPressed event) {
            if (MMClientModBusEvents.KEY_DESCRIPTION_ITEM.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), event.getScanCode())))
                MMClientModBusEvents.KEY_DESCRIPTION_ITEM.setDown(true);
            
            if (MMClientModBusEvents.KEY_USAGE_ITEM.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), event.getScanCode())))
                MMClientModBusEvents.KEY_USAGE_ITEM.setDown(true);
        }
        
        @SubscribeEvent
        public static void onKeyReleased(ScreenEvent.KeyReleased event) {
            if (MMClientModBusEvents.KEY_DESCRIPTION_ITEM.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), event.getScanCode())))
                MMClientModBusEvents.KEY_DESCRIPTION_ITEM.setDown(false);
            
            if (MMClientModBusEvents.KEY_USAGE_ITEM.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), event.getScanCode())))
                MMClientModBusEvents.KEY_USAGE_ITEM.setDown(true);
        }
    }
    
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class MMClientModBusEvents {
        public static final KeyMapping KEY_DESCRIPTION_ITEM = new KeyMapping("key.mana_mod.key_description_item", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, "key.categories.mana_mod");
        public static final KeyMapping KEY_USAGE_ITEM = new KeyMapping("key.mana_mod.key_usage_item", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_U, "key.categories.mana_mod");
        
        @SubscribeEvent
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            event.register(KEY_DESCRIPTION_ITEM);
            event.register(KEY_USAGE_ITEM);
        }
    }
}
