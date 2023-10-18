package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class FileWidget extends AbstractButton {
    private static final ResourceLocation TERMINAL_WIDGET = ManaMod.prefixGui("terminal/terminal_widget");
    private static final float[] FLASH = new float[4];
    private static final int FLASH_SPEED = 20;
    
    public final ItemStack item;
    public final Texture texture;
    public final FileWidget parent;
    public final FocusWidget focusWidget;
    public int offsetX;
    public int offsetY;
    public boolean focusing;
    public boolean flashing;
    private final int width;
    private final int height;
    private boolean shading;
    
    private FileWidget(ItemStack item, Texture texture, Component component, int offsetX, int offsetY, FileWidget.Properties properties) {
        super(0, 0, (int) (properties.width * 2.5), (int) (properties.height * 2.5), component);

        this.width = properties.width;
        this.height = properties.height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.item = item;
        this.texture = texture;
        this.parent = properties.parent;
        this.focusWidget = TerminalScreen.FOCUS_WIDGET;
        this.flashing = properties.parent != null;
        this.shading = this.flashing;
        
        for (int i = 0; i < FLASH.length; i++) {
            FLASH[i] = TerminalScreen.COLOR[i] * FLASH_SPEED;
        }
        
        update();
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
        graphics.setColor(TerminalScreen.COLOR[0], TerminalScreen.COLOR[1], TerminalScreen.COLOR[2], TerminalScreen.COLOR[3]);
        
        if (parent != null)
            this.renderConnectivity(graphics, mouseX, mouseY, partialTick);

        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 1);
        
        if (flashing)
            graphics.setColor(FLASH[0] / FLASH_SPEED, FLASH[1] / FLASH_SPEED, FLASH[2] / FLASH_SPEED, FLASH[3]);
        graphics.blitInscribed(TERMINAL_WIDGET, this.getX(), this.getY(), 24, 24, this.width, this.height);
        graphics.setColor(1F, 1F, 1F, 1F);
        
        if (item != null)
            graphics.renderFakeItem(item, getCenter(this.getX(), this.width), getCenter(this.getY(), this.height));
        else
            graphics.blitInscribed(this.texture.resourceLocation, getCenter(this.getX(), this.width), getCenter(this.getY(), this.height), this.texture.boundsWidth, this.texture.boundsHeight, this.width / 2, this.height / 2);
        
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
    
    public void tick() {
        update();
        
        if (TerminalScreen.COLOR[0] * FLASH_SPEED <= FLASH[0])
            shading = true;
        else if (TerminalScreen.COLOR[0] / 2 * FLASH_SPEED >= FLASH[0])
            shading = false;
            
        for (int i = 0; i < 3; i++) {
            FLASH[i] += Math.signum((shading ? TerminalScreen.COLOR[i] / 2 * FLASH_SPEED : TerminalScreen.COLOR[i] * FLASH_SPEED) - FLASH[i]);
        }
    }
    
    private void update() {
        if (parent != null) {
            if (parent.visible) {
                this.visible = !parent.flashing;
            } else {
                this.visible = false;
            }
        }
    }
    
    @Override
    public void onPress() {
        this.focusWidget.setFollowFocus(this);
        focusing = true;
        
        this.flashing = false;
    }
    
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }

    public int getCenter(int coordinate, int direction) {
        return Mth.floor(coordinate + direction / 2D - direction / 2D * 0.1);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }
    
    public record Texture(ResourceLocation resourceLocation, int boundsWidth, int boundsHeight) {
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
