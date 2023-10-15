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
    
    private final int MAX_X = 550;
    private final int MAX_Y = 750;
    private static final HashSet<FileWidget> FILE_WIDGETS = new HashSet<>();
    private double scrollX = -MAX_X / 2.5D;
    private double scrollY = -MAX_Y / 2.5D;
    
    public TerminalScreen(TerminalMenu terminalMenu, Inventory inventory, Component component) {
        super(terminalMenu, inventory, component);
        
        this.inventoryLabelX = -1000;
    }
    
    @Override
    protected void init() {
        FileWidget mana = registerTerminalWidget(new FileWidget(0, 0, new FileWidget.Properties().display(MMItems.MANA.get().getDefaultInstance())));
        FileWidget mana_bag = registerTerminalWidget(new FileWidget(50, 50, new FileWidget.Properties().parent(mana).display(MMItems.MANA_BAG.get().getDefaultInstance())));
        FileWidget mana_dice = registerTerminalWidget(new FileWidget(100, 75, new FileWidget.Properties().parent(mana_bag).display(MMItems.MANA_DICE.get().getDefaultInstance())));
        FileWidget mana_heart = registerTerminalWidget(new FileWidget(150, 50, new FileWidget.Properties().parent(mana_dice).display(MMItems.MANA_HEART.get().getDefaultInstance())));
        
        for (FileWidget fileWidget : FILE_WIDGETS) {
            this.addRenderableWidget(fileWidget);
        }
        
        this.addRenderableWidget(new FocusWidget(10, 10));
        
        //this.addRenderableWidget();
        
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
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        for (FileWidget fileWidget : FILE_WIDGETS) {
            fileWidget.setPosition(Mth.floor(scrollX + MAX_X / 2D + fileWidget.getOffsetX()), Mth.floor(scrollY + MAX_Y / 2D + fileWidget.getOffsetY()));
        }
        
        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 200);
        
        if (FocusWidget.followFocus != null) {
            FileWidget selectedFile = FocusWidget.followFocus;
            
            //Inspector
            graphics.fill(width, 0, Mth.floor(this.width / 1.35D), this.height, -16777216);
            graphics.vLine(Mth.floor(this.width / 1.35D), Mth.floor(this.width / 1.35D), -1, -1);
            
            graphics.renderFakeItem(selectedFile.getItem(), Mth.floor(this.width / 1.3D), 10);
            graphics.drawString(this.font, selectedFile.getMessage(), Mth.floor(this.width / 1.2D), 15, 0);
            
            graphics.pose().popPose();
        }
        
        super.render(graphics, mouseX, mouseY, partialTick);
    }
    
    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(BACKGROUND, Mth.floor(scrollX), Mth.floor(scrollY), 512, 512, Mth.floor(MAX_X * 5), Mth.floor(MAX_Y * 2.5));
    }
    
    @Override
    public void renderBackground(GuiGraphics graphics) {
    }

    public static FileWidget registerTerminalWidget(FileWidget fileWidget) {
        FILE_WIDGETS.add(fileWidget);
        return fileWidget;
    }
}
