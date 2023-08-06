package com.gershaveut.mana_mod.world.item;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Tooltip {
    public static final KeyMapping KEY_DESCRIPTION_ITEM = new KeyMapping("key.mana_mod.key_description_item", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "key.categories.mana_mod");
    public static final KeyMapping KEY_USAGE_ITEM = new KeyMapping("key.mana_mod.key_usage_item", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_CONTROL, "key.categories.mana_mod");
    protected Component feedback;
    protected boolean descriptionItem;
    protected boolean UsageItem;
    protected String name;
    protected boolean WIP;
    private boolean isFeedbackNullRunning;

    public Tooltip(String name) {
        this(name, true, false, false);
    }

    public Tooltip(String name, boolean descriptionItem) {
        this(name, descriptionItem, false, false);
    }

    public Tooltip(String name, boolean descriptionItem, boolean UsageItem) {
        this(name, descriptionItem, UsageItem, false);
    }

    public Tooltip(String name, boolean descriptionItem, boolean UsageItem, boolean WIP) {
        this.name = name;
        this.descriptionItem = descriptionItem;
        this.UsageItem = UsageItem;
        this.WIP = WIP;
    }

    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag, Runnable use) {
        Component description = Component.translatable("item.mana_mod." + name + ".description");
        Component descriptionView = Component.literal(Component.translatable("item.mana_mod.description_view").getString().replace("{key}", KEY_DESCRIPTION_ITEM.getKey().getDisplayName().getString()));
        Component usageView = Component.literal(Component.translatable("item.mana_mod.usage_view").getString().replace("{key}", KEY_USAGE_ITEM.getKey().getDisplayName().getString()));

        long window = Minecraft.getInstance().getWindow().getWindow();
        boolean keyTooltipPressed = GLFW.GLFW_PRESS == GLFW.glfwGetKey(window, KEY_DESCRIPTION_ITEM.getKey().getValue());
        boolean keyUsageItemPressed = GLFW.GLFW_PRESS == GLFW.glfwGetKey(window, KEY_USAGE_ITEM.getKey().getValue());
        LocalPlayer player = Minecraft.getInstance().player;
        assert player != null;
        boolean canUseItem = UsageItem && !player.getCooldowns().isOnCooldown(itemStack.getItem()) && GLFW.GLFW_PRESS != GLFW.glfwGetKey(window, KEY_DESCRIPTION_ITEM.getKey().getValue());

        if (keyTooltipPressed && feedback == null && descriptionItem) {
            tooltip.add(description);
        } else {
            if (feedback == null) {
                if (descriptionItem)
                    tooltip.add(descriptionView);
                if (UsageItem)
                    tooltip.add(usageView);
            } else {
                tooltip.add(feedback);

                if (!isFeedbackNullRunning) {
                    isFeedbackNullRunning = true;
                    Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                        feedback = null;
                        isFeedbackNullRunning = false;
                    }, 2, TimeUnit.SECONDS);
                }
            }
        }

        if (keyUsageItemPressed && canUseItem) {
            if (player.isCreative() || player.getInventory().contains(itemStack)) {
                assert level != null;
                use.run();
            } else {
                feedback = Component.translatable("item.mana_mod.feedback.error");
            }
        }

        if (WIP) {
            tooltip.add(Component.literal("§cWIP"));
        }
    }
}
