package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.inventory.TerminalMenu;
import com.gershaveut.manamod.world.item.MMItems;
import net.minecraft.ChatFormatting;
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
    private final FocusWidget focusWidget = new FocusWidget(10, 10);
    private final HashSet<FileWidget> FILE_WIDGETS = new HashSet<>();
    private double scrollX = -MAX_X / 2.5D;
    private double scrollY = -MAX_Y / 2.5D;
    private int inspectorX;
    private int inspectorY;
    
    public TerminalScreen(TerminalMenu terminalMenu, Inventory inventory, Component component) {
        super(terminalMenu, inventory, component);
        
        this.inventoryLabelX = -1000;
    }
    
    @Override
    protected void init() {
        FileWidget mana = registerTerminalWidget(0, 0, new FileWidget.Properties().display(MMItems.MANA.get().getDefaultInstance()));
        FileWidget mana_bag = registerTerminalWidget(50, 50, new FileWidget.Properties().parent(mana).display(MMItems.MANA_BAG.get().getDefaultInstance()));
        FileWidget mana_dice = registerTerminalWidget(100, 75, new FileWidget.Properties().parent(mana_bag).display(MMItems.MANA_DICE.get().getDefaultInstance()));
        FileWidget mana_heart = registerTerminalWidget(150, 50, new FileWidget.Properties().parent(mana_dice).display(MMItems.MANA_HEART.get().getDefaultInstance()));
        
        for (FileWidget fileWidget : FILE_WIDGETS) {
            this.addRenderableWidget(fileWidget);
        }
        
        this.addRenderableWidget(focusWidget);
        
        //this.addRenderableWidget();
        
        super.init();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 1) {
            if (focusWidget.getFollowFocus() != null)
                focusWidget.setFollowFocus(null);
        }

        return super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int mouseButton, double dragX, double dragY) {
        if (mouseButton == 0 || mouseButton == 2) {
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
        graphics.pose().translate(inspectorX, inspectorY, 200);

        if (focusWidget.getFollowFocus() != null || focusWidget.lastFollowFocus != null) {
            FileWidget selectedFile = focusWidget.getFollowFocus() != null ? focusWidget.getFollowFocus() : focusWidget.lastFollowFocus;

            graphics.fill(this.width, 0, Mth.floor(this.width / 1.35D), this.height, -16777216);
            graphics.vLine(Mth.floor(this.width / 1.35D), this.height, -1, -1);

            graphics.renderFakeItem(selectedFile.getItem(), Mth.floor(this.width / 1.3D), 10);
            graphics.drawString(this.font, selectedFile.getMessage(), Mth.floor(this.width / 1.2D), 15, 0);

            graphics.pose().popPose();

            for (int i = 0; i < 3; i++) {
                inspectorX += focusWidget.getFollowFocus() != null ? Mth.floor(Math.signum(-inspectorX)) : Mth.floor(Math.signum(this.width - this.width / 1.35D - inspectorX + 1));
            }
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

    public FileWidget registerTerminalWidget(int offsetX, int offsetY, int width, int height, FileWidget.Properties properties) {
        FileWidget fileWidget = new FileWidget(this.focusWidget, offsetX, offsetY, width, height, properties);
        FILE_WIDGETS.add(fileWidget);
        return fileWidget;
    }

    public FileWidget registerTerminalWidget(int offsetX, int offsetY, FileWidget.Properties properties) {
        return registerTerminalWidget(offsetX, offsetY, 10, 10, properties);
    }
}
