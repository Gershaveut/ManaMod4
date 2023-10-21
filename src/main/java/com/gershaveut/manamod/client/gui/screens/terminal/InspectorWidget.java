package com.gershaveut.manamod.client.gui.screens.terminal;

import net.minecraft.client.gui.components.AbstractWidget;

public class InspectorWidget {
    public AbstractWidget widget;
    public int offsetX;
    public int offsetY;
    
    public InspectorWidget(AbstractWidget widget, int offsetX, int offsetY) {
        this.widget = widget;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    
    public void setPosition(int x, int y) {
        widget.setPosition(x + offsetX, y + offsetY);
    }
}
