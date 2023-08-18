package com.gershaveut.manamod.world.item;

import net.minecraft.network.chat.Component;

public interface Tooltip {
    Tooltip manaMod$setTooltipProperties(TooltipProperties tooltipProperties);

    TooltipProperties manaMod$getTooltipProperties();

    void manaMod$setFeedback(Component component);

    Component manaMod$getFeedback();
}
