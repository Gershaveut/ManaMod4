package com.gershaveut.manamod.client.gui.screens.terminal;

public enum FileWidgetType {
    ROOT(0, 0),
    COMMON(24, 0),
    UNCOMMON(0, 24),
    RARE(24, 24);

    public final int x;
    public final int y;

    FileWidgetType(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
