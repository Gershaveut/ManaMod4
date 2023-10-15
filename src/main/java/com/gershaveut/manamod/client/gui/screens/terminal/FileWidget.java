package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.item.MMItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class FileWidget extends AbstractButton {
    private static final ResourceLocation TERMINAL_WIDGET = ManaMod.prefixGui("terminal/terminal_widget");

    private final ItemStack item;
    private final Texture texture;
    private final FileWidget parent;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private boolean focusing;

    public FileWidget(int offsetX, int offsetY, int width, int height, FileWidget.Properties properties) {
        super(0, 0, (int) (width * 2.5), (int) (height * 2.5), properties.message);

        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.item = properties.item;
        this.texture = properties.texture;
        this.parent = properties.parent;
    }

    public FileWidget(int offsetX, int offsetY, FileWidget.Properties properties) {
        this(offsetX, offsetY, 10, 10, properties);
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (parent != null)
            this.renderConnectivity(graphics, mouseX, mouseY, partialTick);

        graphics.blitInscribed(TERMINAL_WIDGET, this.getX(), this.getY(), 24, 24, this.width, this.height);

        if (item != null) {
            graphics.renderFakeItem(item, getCenter(this.getX(), this.width), getCenter(this.getY(), this.height));
        } else if (texture != null) {
            graphics.blitInscribed(this.texture.texture, getCenter(this.getX(), this.width), getCenter(this.getY(), this.height), this.texture.boundsWidth, this.texture.boundsHeight, this.width / 2, this.height / 2);
        } else {
            graphics.renderItem(MMItems.MANA.get().getDefaultInstance(), getCenter(this.getX(), this.width), getCenter(this.getY(), this.height), this.width / 2, this.height / 2);
        }
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
        FocusWidget.followFocus = this;
        focusing = true;
    }

    @Override
    public boolean isHovered() {
        onPress();
        return super.isHovered();
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
        Component message = Component.empty();
        ItemStack item;
        Texture texture;
        FileWidget parent;

        public Properties message(Component message) {
            this.message = message;
            return this;
        }

        public Properties parent(FileWidget parent) {
            this.parent = parent;
            return this;
        }

        public Properties display(ItemStack itemTexture) {
            this.item = itemTexture;
            this.message = itemTexture.getDisplayName();
            return this;
        }

        public Properties display(Texture texture) {
            this.texture = texture;
            return this;
        }
    }
}
