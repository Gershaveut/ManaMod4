package com.gershaveut.mana_mod.world.item;

public class TooltipProperties {
    public boolean descriptionItem;
    public boolean UsageItem;
    public boolean WIP;

    public TooltipProperties() {
        this(false, false, false);
    }

    public TooltipProperties(boolean descriptionItem) {
        this(descriptionItem, false, false);
    }

    public TooltipProperties(boolean descriptionItem, boolean UsageItem) {
        this(descriptionItem, UsageItem, false);
    }

    public TooltipProperties(boolean descriptionItem, boolean UsageItem, boolean WIP) {
        this.descriptionItem = descriptionItem;
        this.UsageItem = UsageItem;
        this.WIP = WIP;
    }
}
