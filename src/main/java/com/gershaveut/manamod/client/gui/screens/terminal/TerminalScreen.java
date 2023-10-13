package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.inventory.TerminalMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;
import org.slf4j.event.Level;

public class TerminalScreen extends AbstractContainerScreen<TerminalMenu> {
    private static final ResourceLocation BACKGROUND = ManaMod.prefixGui("background");
    
    private double scrollX = -133;
    private double scrollY = -193;
    
    public TerminalScreen(TerminalMenu terminalMenu, Inventory inventory, Component component) {
        super(terminalMenu, inventory, component);
    }
    
    @Override
    protected void init() {
        super.init();
    }
    
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int mouseButton, double dragX, double dragY) {
        if (mouseButton == 0) {
            int maxX = 500;
            int maxY = 500;
            
            scrollX = Mth.clamp(scrollX + dragX, -(maxX - 234), 0.0D);
            scrollY = Mth.clamp(scrollY + dragY, -(maxY - 113), 0.0D);
            
            ManaMod.Log(Level.INFO, "X: " + scrollX + " Y: " + scrollY + " MB: " + mouseButton);
            
            return true;
        }
        return false;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        
        
        super.render(graphics, mouseX, mouseY, partialTick);
    }
    
    @Override
    protected void renderBg(GuiGraphics graphics, float mouseX, int mouseY, int partialTick) {
        this.renderBackground(graphics);
    }
    
    @Override
    public void renderBackground(GuiGraphics graphics) {
        graphics.blit(BACKGROUND, Mth.floor(scrollX), Mth.floor(scrollY), 512, 512, 1000, 1000);
    }
}
