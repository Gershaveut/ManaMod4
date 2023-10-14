package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.inventory.TerminalMenu;
import com.gershaveut.manamod.world.item.MMItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.HashSet;

public class TerminalScreen extends AbstractContainerScreen<TerminalMenu> {
    private static final ResourceLocation BACKGROUND = ManaMod.prefixGui("terminal/background");
    private static final ResourceLocation FOCUS = ManaMod.prefixGui("terminal/focus");
    
    private final int MAX_X = 550;
    private final int MAX_Y = 750;
    private final int TERMINAL_WIDGET_WIDTH = 10;
    private final int TERMINAL_WIDGET_HEIGHT = 10;
    private final HashSet<TerminalWidget> terminalWidgets = new HashSet<>();
    private double scrollX = -MAX_X / 2D;
    private double scrollY = -MAX_Y / 2D;
    private TerminalWidget followFocus;
    
    public TerminalScreen(TerminalMenu terminalMenu, Inventory inventory, Component component) {
        super(terminalMenu, inventory, component);
    }
    
    @Override
    protected void init() {
        terminalWidgets.add(new TerminalWidget(0, 0, TERMINAL_WIDGET_WIDTH, TERMINAL_WIDGET_HEIGHT, MMItems.MANA.get().getDefaultInstance()));
        terminalWidgets.add(new TerminalWidget(50, 0, TERMINAL_WIDGET_WIDTH, TERMINAL_WIDGET_HEIGHT, MMItems.MANA_BAG.get().getDefaultInstance()));
        
        super.init();
    }
    
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int mouseButton, double dragX, double dragY) {
        if (mouseButton == 0) {
            scrollX = Mth.clamp(scrollX + dragX, -MAX_X, 0.0D);
            scrollY = Mth.clamp(scrollY + dragY, -MAX_Y, 0.0D);
            
            return true;
        }
        return false;
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 0) {
            for (TerminalWidget terminalWidget : terminalWidgets) {
                if (mouseX >= (double)terminalWidget.getX() && mouseY >= (double)terminalWidget.getY() && mouseX < (terminalWidget.getX() + terminalWidget.getWidth() * 2.5D) && mouseY < (terminalWidget.getY() + terminalWidget.getHeight() * 2.5D)) {
                    terminalWidget.onClick(mouseX, mouseY);
                    followFocus = terminalWidget;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);
        
        if (followFocus != null)
            graphics.blitInscribed(FOCUS, followFocus.getX() - followFocus.getWidth() / 4, followFocus.getY() - followFocus.getHeight() / 4, 28, 28, TERMINAL_WIDGET_WIDTH, TERMINAL_WIDGET_HEIGHT);
        
        for (TerminalWidget terminalWidget : terminalWidgets ) {
            terminalWidget.draw(graphics, Mth.floor(scrollX) + MAX_X / 2, Mth.floor(scrollY) + MAX_Y / 2, partialTick);
        }
        
        super.render(graphics, mouseX, mouseY, partialTick);
    }
    
    @Override
    protected void renderBg(GuiGraphics graphics, float mouseX, int mouseY, int partialTick) {
        this.renderBackground(graphics);
    }
    
    @Override
    public void renderBackground(GuiGraphics graphics) {
        //graphics.blit(BACKGROUND, Mth.floor(scrollX), Mth.floor(scrollY), 512, 512, 1000, 1000);
    }
}
