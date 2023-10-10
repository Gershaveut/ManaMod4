package com.gershaveut.manamod.world.inventory;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ManaBagMenu extends AbstractContainerMenu {
    public ManaBagMenu(int containerId, Inventory inventory) {
        this(containerId, inventory, new ItemStackHandler(5), DataSlot.standalone());
        //super(containerId, inventory);
    }
    
    public ManaBagMenu(int containerId, Inventory inventory, IItemHandler iItemHandler, DataSlot dataSlot) {
        super(MMMenuType.MANA_BAG_MENU.get(), containerId);
        
        //this.addSlot(new SlotItemHandler(iItemHandler, 0, 0, 0));
        
        this.addSlot(new Slot(inventory, 1, 10, 10));
        
        //this.addDataSlot(dataSlot);
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
