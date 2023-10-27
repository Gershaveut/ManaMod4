package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.Util;
import com.gershaveut.manamod.client.gui.screens.TextureInscribed;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class BarWidget extends AbstractWidget implements Tickable {
    private static final List<Float> BLINK = new ArrayList<>(TerminalScreen.COLOR);
    
    public TextureInscribed icon;
    public int maxValue;
    public Font font;
    
    private int value;
    private int needValue;
    private int tempValue;
    private float timer;
    
    public BarWidget(int x, int y, int width, int height, Component message, int maxValue, TextureInscribed icon, Font font) {
        super(x, y, width, height, message);
        
        this.maxValue = maxValue;
        this.value = maxValue;
        this.tempValue = maxValue;
        this.icon = icon;
        this.font = font;
    }
    
    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.blitInscribed(icon.resourceLocation(), this.getX(), this.getY(), icon.boundsWidth(), icon.boundsHeight(), this.width, this.height);
        
        int barsX = this.getX() + icon.boundsWidth() + 5;
        int barsWidth = barsX + this.width;
        int barsHeight = this.getY() + this.height;
        
        graphics.fill(barsX, this.getY(), barsWidth, barsHeight, 8421504);
        graphics.fill(barsX, this.getY(), barsWidth * (tempValue / maxValue), barsHeight, 16753920);
        graphics.fill(barsX, this.getY(), barsWidth * (value / maxValue), barsHeight, -1);
        
        graphics.setColor(BLINK.get(0), BLINK.get(1), BLINK.get(2), TerminalScreen.COLOR.get(3));
        graphics.fill(barsWidth * (value / maxValue) - needValue / this.width, this.getY(), barsWidth * (value / maxValue), barsHeight, -1);
        TerminalScreen.setTerminalColor(graphics);
        
        graphics.drawString(this.font, Component.literal(String.valueOf(value)), barsWidth + 5, barsHeight / 2, 0);
        
        if (tempValue > value)
            graphics.drawString(this.font, Component.literal(String.valueOf(value - tempValue)), barsWidth + 10, barsHeight / 2, 8421504);
    }
    
    @Override
    public void tick() {
        if (timer > 0)
            timer -= 0.1F;
        
        if (timer <= 0) {
            if (tempValue < value)
                tempValue -= 1;
            else
                timer = 100;
        }
        
        Util.blinkingColor(BLINK, TerminalScreen.COLOR, 0.5F, 0.25F);
    }
    
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }
    
    public int getValue() {
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = Math.min(value, maxValue);
    }
    
    public int getNeedValue() {
        return this.value;
    }
    
    public void setNeedValue(int needValue) {
        this.needValue = Math.min(needValue, value);
    }
}
