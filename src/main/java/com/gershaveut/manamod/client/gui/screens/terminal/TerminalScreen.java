package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.MMConfig;
import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.inventory.TerminalMenu;
import com.gershaveut.manamod.world.item.MMItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class TerminalScreen extends AbstractContainerScreen<TerminalMenu> {
    public static final FocusWidget FOCUS_WIDGET = new FocusWidget(10, 10);
    public static final List<Float> COLOR = MMConfig.CLIENT.TERMINAL_COLOR.get().stream().map(Number::floatValue).collect(Collectors.toList());
    private static final ResourceLocation BACKGROUND = ManaMod.prefixGui("terminal/background");
    private static final int MAX_X = 1000;
    private static final int MAX_Y = 1000;
    
    private final HashSet<FileWidget> fileWidgets = new HashSet<>();
    private final HashSet<AbstractWidget> inspectorWidgets = new HashSet<>();
    private double scrollX = (double) -MAX_X + this.getXSize();
    private double scrollY = (double) -MAX_Y + this.getYSize();
    private int inspectorX;
    private int inspectorY;
    
    public TerminalScreen(TerminalMenu terminalMenu, Inventory inventory, Component component) {
        super(terminalMenu, inventory, component);
        
        this.inventoryLabelX = -1000;
        this.titleLabelX = -1000;
    }
    
    @Override
    protected void init() {
        FileWidget mana = registerTerminalWidget(new FileWidget(MMItems.MANA.get().getDefaultInstance(), 0, 0, new FileWidget.Properties()));
        FileWidget mana_bag = registerTerminalWidget(new FileWidget(MMItems.MANA_BAG.get().getDefaultInstance(), 50, 50, new FileWidget.Properties().parent(mana)));
        FileWidget mana_dice = registerTerminalWidget(new FileWidget(MMItems.MANA_DICE.get().getDefaultInstance(), 100, 75, new FileWidget.Properties().parent(mana_bag).fileWidgetType(FileWidgetType.UNCOMMON)));
        FileWidget mana_heart = registerTerminalWidget(new FileWidget(MMItems.MANA_HEART.get().getDefaultInstance(), 150, 50, new FileWidget.Properties().parent(mana_dice).fileWidgetType(FileWidgetType.RARE)));
        FileWidget texture = registerTerminalWidget(new FileWidget(new FileWidget.Texture(ManaMod.prefix("textures/item/mana_cake.png"), 16, 16), Component.literal("Test"), -50, -50, new FileWidget.Properties().parent(mana)));
        
        for (FileWidget fileWidget : fileWidgets) {
            this.addRenderableWidget(fileWidget);
        }
        
        this.addRenderableWidget(FOCUS_WIDGET);
        
        inspectorWidgets.add(this.addRenderableWidget(new InspectorWidget(Button.builder(Component.literal("test"), (pressed) -> {}).build(), 0, 0).widget));
        
        this.inspectorX = Mth.floor(this.width - this.width / 1.35D) - 1;
        
        super.init();
    }
    
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        if (mouseButton == 1) {
            if (FOCUS_WIDGET.getFollowFocus() != null)
                FOCUS_WIDGET.setFollowFocus(null);
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
        setTerminalColor(graphics);
        
        this.renderBackground(graphics);
        
        for (FileWidget fileWidget : fileWidgets) {
            fileWidget.setPosition(Mth.floor(scrollX + MAX_X + fileWidget.offsetX), Mth.floor(scrollY + MAX_Y + fileWidget.offsetY));
        }
        
        if (FOCUS_WIDGET.getFollowFocus() != null || FOCUS_WIDGET.lastFollowFocus != null) {
            graphics.pose().pushPose();
            graphics.pose().translate(inspectorX, inspectorY, 200);
            
            for (AbstractWidget abstractWidget : inspectorWidgets) {
                abstractWidget.setPosition(inspectorX, inspectorY);
            }
            
            FileWidget selectedFile = FOCUS_WIDGET.getFollowFocus() != null ? FOCUS_WIDGET.getFollowFocus() : FOCUS_WIDGET.lastFollowFocus;
            
            graphics.fill(this.width, 0, Mth.floor(this.width / 1.35D), this.height, -16777216);
            graphics.vLine(Mth.floor(this.width / 1.35D), this.height, -1, -1);
            
            graphics.setColor(1F, 1F, 1F, 1F);
            assert selectedFile != null;
            if (selectedFile.item != null)
                graphics.renderFakeItem(selectedFile.item, Mth.floor(this.width / 1.3D), 10);
            else {
                assert selectedFile.texture != null;
                graphics.blitInscribed(selectedFile.texture.resourceLocation(), Mth.floor(this.width / 1.3D), 10, selectedFile.texture.boundsWidth(), selectedFile.texture.boundsHeight(), selectedFile.getWidth() / 2, selectedFile.getHeight() / 2);
            }
            graphics.drawString(this.font, !selectedFile.getMessage().getStyle().isEmpty() ? selectedFile.getMessage() : selectedFile.getMessage().copy().withStyle(ChatFormatting.WHITE), Mth.floor(this.width / 1.2D), 15, 0);
            setTerminalColor(graphics);
            
            graphics.pose().popPose();
        }
        
        ResourceLocation WIDGETS_LOCATION = AbstractWidget.WIDGETS_LOCATION;
        
        AbstractWidget.WIDGETS_LOCATION = ManaMod.prefixGui("terminal/widgets");
        super.render(graphics, mouseX, mouseY, partialTick);
        AbstractWidget.WIDGETS_LOCATION = WIDGETS_LOCATION;
    }
    
    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(BACKGROUND, Mth.floor(scrollX), Mth.floor(scrollY), 512, 512, MAX_X * this.getGuiLeft(), MAX_Y * this.getGuiTop());
    }
    
    @Override
    public void renderBackground(GuiGraphics graphics) {
    }
    
    @Override
    protected void containerTick() {
        super.containerTick();
        
        if (FOCUS_WIDGET.getFollowFocus() != null || FOCUS_WIDGET.lastFollowFocus != null) {
            for (int i = 0; i < 12; i++) {
                inspectorX += FOCUS_WIDGET.getFollowFocus() != null ? Mth.floor(Math.signum(-inspectorX)) : Mth.floor(Math.signum(this.width - this.width / 1.35D - inspectorX + 1));
            }
        }
        
        FOCUS_WIDGET.tick();
        for (FileWidget fileWidget : fileWidgets) {
            fileWidget.tick();
        }
    }
    
    @Override
    public void onClose() {
        FOCUS_WIDGET.setFollowFocus(null);
        
        super.onClose();
    }
    
    public static void setTerminalColor(GuiGraphics graphics) {
        graphics.setColor(COLOR.get(0), COLOR.get(1), COLOR.get(2), COLOR.get(3));
    }
    
    public FileWidget registerTerminalWidget(FileWidget fileWidget) {
        fileWidgets.add(fileWidget);
        return fileWidget;
    }
}
