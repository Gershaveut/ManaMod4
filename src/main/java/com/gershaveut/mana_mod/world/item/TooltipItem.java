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
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.gershaveut.mana_mod.ManaMod.MODID;
import static com.gershaveut.mana_mod.world.item.TooltipItem.keyTooltip;

public class TooltipItem extends Item {
    public static final KeyMapping keyTooltip = new KeyMapping("key.mana_mod.key_tooltip", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "key.categories.mana_mod");
    public static final KeyMapping keyUsageItem = new KeyMapping("key.mana_mod.key_usage_item", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_CONTROL, "key.categories.mana_mod");
    protected final Component tooltip = Component.translatable("item.mana_mod." + "getName(getDefaultInstance())" + ".tooltip");
    //protected final Component tip = Component.translatable("item.mana_mod.tooltip_1" + keyTooltip.getName() + "item.mana_mod.tooltip_2");
    protected final Component tip = Component.literal(Component.translatable("item.mana_mod.tooltip").getString().replace("f", "f " + keyTooltip.getName()));
    protected final Component usageTip = Component.literal(Component.translatable("item.mana_mod.usagetip").getString().replace("f", "f " + keyUsageItem.getName()));
    protected Component reportTip;

    public TooltipItem(Properties properties) {
        super(properties);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> text, TooltipFlag tooltipFlag) {
        long window = Minecraft.getInstance().getWindow().getWindow();
        boolean keyTooltipPressed = GLFW.GLFW_PRESS == GLFW.glfwGetKey(window, keyTooltip.getKey().getValue());
        boolean keyUsageItemPressed = GLFW.GLFW_PRESS == GLFW.glfwGetKey(window, keyUsageItem.getKey().getValue());
        assert Minecraft.getInstance().player != null;
        boolean canUseItem = !Minecraft.getInstance().player.getCooldowns().isOnCooldown(itemStack.getItem()) && GLFW.GLFW_PRESS != GLFW.glfwGetKey(window, keyTooltip.getKey().getValue());

        if (keyTooltipPressed && reportTip == null) {
            text.add(1, tooltip);
        } else {
            if (reportTip == null) {
                text.add(1, tip);
                if (text.size() == 2)
                    text.add(2, usageTip);
            } else {
                text.add(1, reportTip);
                boolean isTaskRunning = false;

                if (!isTaskRunning) {
                    isTaskRunning = true;
                    Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                        reportTip = null;
                        isTaskRunning = false;
                    }, 5, TimeUnit.SECONDS);
                }
            }
        }

        if (keyUsageItemPressed && canUseItem) {
            if (Minecraft.getInstance().player.isCreative() || Minecraft.getInstance().player.getInventory().contains(itemStack)) {
                assert level != null;
                use(level, Minecraft.getInstance().player, Minecraft.getInstance().player.getUsedItemHand());
            } else {
                reportTip = Component.translatable("item.mana_mod.usagetip.error");
            }
        }
    }
}
