package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class TerminalWidget {
    private static final ResourceLocation TERMINAL_WIDGET = ManaMod.prefixGui("terminal/terminal_widget");
    
    private final int offsetX;
    private final int offsetY;
    private int x;
    private int y;
    private final int width;
    private final int height;
    private final ItemStack itemDisplay;
    
    public TerminalWidget(int offsetX, int offsetY, int width, int height, ItemStack itemDisplay) {
        this.itemDisplay = itemDisplay;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
    }
    
    protected void draw(GuiGraphics graphics, int x, int y, float partialTick) {
        this.x = x + offsetX;
        this.y = y + offsetY;
        
        graphics.blitInscribed(TERMINAL_WIDGET, this.x, this.y, 24, 24, width, height);
        graphics.renderItem(itemDisplay, (int) (this.x + width / 2 - width / 2 * 0.1), (int) (this.y + height / 2 - width / 2 * 0.1), width / 2, height / 2);
    }
    
    public void onClick(double x, double y) {
        System.out.println("X:" + x + " Y: " + y);
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public int getHeight() {
        return this.height;
    }
}
