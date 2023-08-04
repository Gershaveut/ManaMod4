package com.gershaveut.mana_mod.world.item;

import com.gershaveut.mana_mod.ManaMod;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

import static com.gershaveut.mana_mod.ManaMod.MODID;
import static com.gershaveut.mana_mod.world.item.TooltipItem.keyTooltip;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TooltipItem extends Item {
    public static final KeyMapping keyTooltip = new KeyMapping("key.mana_mod.key_tooltip", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_SHIFT, "key.categories.mana_mod");

    public TooltipItem(Properties properties) {
        super(properties);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
    }
}
/*
class KeyPressedManaMod extends ScreenEvent.KeyPressed {
    public KeyPressedManaMod(Screen screen, int keyCode, int scanCode, int modifiers) {
        super(screen, keyCode, scanCode, modifiers);
    }

    @Override
    public boolean keyPressed(int key, int scancode, int mods) {
        if (EXAMPLE_MAPPING.get().isActiveAndMatches(InputConstants.getKey(key, scancode))) {
            // Execute logic to perform on key press here
            return true;
        }
        return super.keyPressed(x, y, button);
    }
}
*/
@Mod.EventBusSubscriber(modid = ManaMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
class MyStaticClientOnlyEventHandler {
    @SubscribeEvent
    public static void drawLast(ScreenEvent.KeyPressed event) {
        ManaMod.Log(org.slf4j.event.Level.INFO, "PRESS");
        if (keyTooltip.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), event.getScanCode()))) {
            ManaMod.Log(org.slf4j.event.Level.INFO, "TOOLTIP");
        }
    }
}
