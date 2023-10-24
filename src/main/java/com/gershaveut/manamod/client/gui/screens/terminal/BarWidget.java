package com.gershaveut.manamod.client.gui.screens.terminal;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class BarWidget extends AbstractWidget {
    public int maxValue;
    public int value;
    public int tempValue;
    
    private float timer;
    
    public BarWidget(int x, int y, int width, int height, Component message, int maxValue) {
        super(x, y, width, height, message);
        
        this.maxValue = maxValue;
        this.value = maxValue;
        this.tempValue = maxValue;
    }
    
    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, 8421504);
        graphics.fill(this.getX(), this.getY(), this.getX() + this.width * (tempValue / maxValue), this.getY() + this.height, 16753920);
        graphics.fill(this.getX(), this.getY(), this.getX() + this.width * (value / maxValue), this.getY() + this.height, -1);
        
    }
    
    public void tick() {
        if (timer > 0)
            timer -= 0.1F;
        
        if (timer <= 0) {
            if (tempValue < value) {
                tempValue -= 1;
            } else {
                timer = 100;
            }
        }
    }
    
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }
}
