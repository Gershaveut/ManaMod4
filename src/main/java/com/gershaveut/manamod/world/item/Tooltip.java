package com.gershaveut.manamod.world.item;

import net.minecraft.network.chat.Component;

public interface Tooltip {
    Tooltip manaMod$setTooltipProperties(TooltipProperties tooltipProperties);
    
    TooltipProperties manaMod$getTooltipProperties();
    
    void manaMod$setFeedback(Component component);
    
    Component manaMod$getFeedback();
    
    Component manaMod$getDescription();
    
    String manaMod$getFeedbackId();
    
    Component manaMod$getFeedbackMessage(String name);
}
