package com.gershaveut.manamod.world.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;

public class TerminalMenu extends AbstractContainerMenu {
    
    public TerminalMenu(int containerId, Inventory inventory) {
        super(MMMenuType.TERMINAL_MENU.get(), containerId);
    }
    
    @Override
    public ItemStack quickMoveStack(Player player, int containerId) {
        return null;
    }
    
    @Override
    public boolean stillValid(Player player) {
        return true;
    }
}
