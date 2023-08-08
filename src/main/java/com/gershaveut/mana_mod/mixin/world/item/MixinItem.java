package com.gershaveut.mana_mod.mixin.world.item;

import com.gershaveut.mana_mod.ManaMod;
import com.gershaveut.mana_mod.world.item.Tooltip;
import com.gershaveut.mana_mod.world.item.TooltipProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Mixin(Item.class)
public abstract class MixinItem implements Tooltip {
    @Unique
    protected Component manaMod$feedback;
    @Unique
    private TooltipProperties manaMod$tooltipProperties = new TooltipProperties();
    @Unique
    private boolean manaMod$isFeedbackNullRunning;

    public Tooltip manaMod$setTooltipProperties(TooltipProperties tooltipProperties) {
        this.manaMod$tooltipProperties = tooltipProperties;
        return this;
    }

    public TooltipProperties manaMod$getTooltipProperties() {
        return manaMod$tooltipProperties;
    }

    public void manaMod$setFeedback(Component component) {
        manaMod$feedback = component;
    }

    public Component manaMod$getFeedback() {
        return manaMod$feedback;
    }

    @Shadow public abstract InteractionResultHolder<ItemStack> use(Level p_41432_, Player p_41433_, InteractionHand p_41434_);

    @Shadow protected abstract String getOrCreateDescriptionId();

    @OnlyIn(Dist.CLIENT)
    @Inject(method = "appendHoverText", at = @At("HEAD"))
    public void onAppendHoverText(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag, CallbackInfo callbackInfo) {
        Component description = Component.translatable(getOrCreateDescriptionId() + ".description");
        Component descriptionView = Component.literal(Component.translatable("item.mana_mod.description_view").getString().replace("{key}", ManaMod.ClientModEvents.KEY_DESCRIPTION_ITEM.getKey().getDisplayName().getString()));
        Component usageView = Component.literal(Component.translatable("item.mana_mod.usage_view").getString().replace("{key}", ManaMod.ClientModEvents.KEY_USAGE_ITEM.getKey().getDisplayName().getString()));

        long window = Minecraft.getInstance().getWindow().getWindow();
        boolean keyTooltipPressed = GLFW.GLFW_PRESS == GLFW.glfwGetKey(window, ManaMod.ClientModEvents.KEY_DESCRIPTION_ITEM.getKey().getValue());
        boolean keyUsageItemPressed = GLFW.GLFW_PRESS == GLFW.glfwGetKey(window, ManaMod.ClientModEvents.KEY_USAGE_ITEM.getKey().getValue());
        LocalPlayer player = Minecraft.getInstance().player;
        assert player != null;
        boolean canUseItem = manaMod$tooltipProperties.UsageItem && !player.getCooldowns().isOnCooldown(itemStack.getItem()) && GLFW.GLFW_PRESS != GLFW.glfwGetKey(window, ManaMod.ClientModEvents.KEY_DESCRIPTION_ITEM.getKey().getValue());

        if (keyTooltipPressed && manaMod$feedback == null && manaMod$tooltipProperties.descriptionItem) {
            tooltip.add(description);
        } else {
            if (manaMod$feedback == null) {
                if (manaMod$tooltipProperties.descriptionItem)
                    tooltip.add(descriptionView);
                if (manaMod$tooltipProperties.UsageItem)
                    tooltip.add(usageView);
            } else {
                tooltip.add(manaMod$feedback);

                if (!manaMod$isFeedbackNullRunning) {
                    manaMod$isFeedbackNullRunning = true;
                    Executors.newSingleThreadScheduledExecutor().schedule(() -> {
                        manaMod$feedback = null;
                        manaMod$isFeedbackNullRunning = false;
                    }, 2, TimeUnit.SECONDS);
                }
            }
        }

        if (keyUsageItemPressed && canUseItem) {
            if (player.isCreative() || player.getInventory().contains(itemStack)) {
                use(level, Minecraft.getInstance().player, Minecraft.getInstance().player.getUsedItemHand());
            } else {
                manaMod$feedback = Component.translatable("item.mana_mod.feedback.error");
            }
        }

        if (manaMod$tooltipProperties.WIP) {
            tooltip.add(Component.literal("§cWIP"));
        }
    }
}
