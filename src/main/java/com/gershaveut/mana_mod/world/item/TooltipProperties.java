package com.gershaveut.mana_mod.world.item;

public class TooltipProperties {
    public boolean descriptionItem;
    public boolean UsageItem;
    public boolean WIP;

    public TooltipProperties descriptionItem() {
        descriptionItem = true;
        return this;
    }
    public TooltipProperties UsageItem() {
        UsageItem = true;
        return this;
    }

    public TooltipProperties WIP() {
        WIP = true;
        return this;
    }

}
