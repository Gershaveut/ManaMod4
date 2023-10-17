package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.item.MMItems;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class FileWidget extends AbstractButton {
    private static final ResourceLocation TERMINAL_WIDGET = ManaMod.prefixGui("terminal/terminal_widget");

    private final ItemStack item;
    private final Texture texture;
    private final FileWidget parent;
    private final FocusWidget focusWidget;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private boolean focusing;

    private FileWidget(ItemStack item, Texture texture, Component component, int offsetX, int offsetY, FileWidget.Properties properties) {
        super(0, 0, (int) (properties.width * 2.5), (int) (properties.height * 2.5), component);

        this.width = properties.width;
        this.height = properties.height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.item = item;
        this.texture = texture;
        this.parent = properties.parent;
        this.focusWidget = TerminalScreen.focusWidget;
    }

    public FileWidget(ItemStack item, int offsetX, int offsetY, FileWidget.Properties properties) {
        this(item, null, Component.translatable(item.getDescriptionId()).withStyle(ChatFormatting.WHITE), offsetX, offsetY, properties);
    }

    public FileWidget(ItemStack item, Component component, int offsetX, int offsetY, FileWidget.Properties properties) {
        this(item, null, component, offsetX, offsetY, properties);
    }

    public FileWidget(Texture texture, Component component, int offsetX, int offsetY, FileWidget.Properties properties) {
        this(null, texture, component, offsetX, offsetY, properties);
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (parent != null)
            this.renderConnectivity(graphics, mouseX, mouseY, partialTick);

        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 1);


        graphics.blitInscribed(TERMINAL_WIDGET, this.getX(), this.getY(), 24, 24, this.width, this.height);

        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        if (item != null)
            graphics.renderFakeItem(item, getCenter(this.getX(), this.width), getCenter(this.getY(), this.height));
        else
            graphics.blitInscribed(this.texture.texture, getCenter(this.getX(), this.width), getCenter(this.getY(), this.height), this.texture.boundsWidth, this.texture.boundsHeight, this.width / 2, this.height / 2);

        graphics.pose().popPose();
    }

    public void renderConnectivity(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        int i = this.parent.getX() + 13;
        int j = this.parent.getX() + 26 + 4;
        int k = this.parent.getY() + 13;
        int l = this.getX() + 13;
        int i1 = this.getY() + 13;
        int j1 = -1;

        graphics.hLine(j, i, k, j1);
        graphics.hLine(l, j, i1, j1);
        graphics.vLine(j, i1, k, j1);
    }

    @Override
    public void onPress() {
        this.focusWidget.setFollowFocus(this);
        focusing = true;
    }

    @Override
    public boolean isFocused() {
        //this.onPress();
        return super.isFocused();
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }

    public int getCenter(int coordinate, int direction) {
        return Mth.floor(coordinate + direction / 2D - direction / 2D * 0.1);
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    public boolean getFocusing() {
        return this.focusing;
    }

    public void setFocusing(boolean focusing) {
        this.focusing = focusing;
    }
    
    public ItemStack getItem() {
        return this.item;
    }
    
    public Texture getTexture() {
        return this.texture;
    }
    
    public FileWidget getParent() {
        return this.parent;
    }

    public record Texture(ResourceLocation texture, int boundsWidth, int boundsHeight) {
    }

    public static class Properties {
        FileWidget parent;
        int width = 10;
        int height = 10;

        public Properties parent(FileWidget parent) {
            this.parent = parent;
            return this;
        }

        public Properties width(int width) {
            this.width = width;
            return this;
        }

        public Properties height(int height) {
            this.height = height;
            return this;
        }
    }
}
