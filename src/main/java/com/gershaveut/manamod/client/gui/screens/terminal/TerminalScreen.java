package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.MMConfig;
import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.Util;
import com.gershaveut.manamod.util.TextureInscribed;
import com.gershaveut.manamod.world.inventory.TerminalMenu;
import com.gershaveut.manamod.world.item.MMItems;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TerminalScreen extends AbstractContainerScreen<TerminalMenu> {
    public static final FocusWidget FOCUS_WIDGET = new FocusWidget(10, 10, Component.literal("Focus"));
    public static final List<Float> COLOR = MMConfig.CLIENT.TERMINAL_COLOR.get().stream().map(Number::floatValue).collect(Collectors.toList());
    private static final ResourceLocation BACKGROUND = ManaMod.prefixGui("terminal/background");
    private static final int INSPECTOR_SPEED = 10;
    private static final int MAX_X = 1000;
    private static final int MAX_Y = 1000;
    
    private final HashSet<Tickable> tickableWidgets = new HashSet<>();
    private final HashSet<FileWidget> fileWidgets = new HashSet<>();
    private final HashSet<InspectorWidget> inspectorWidgets = new HashSet<>();
    private double scrollX = (double) -MAX_X + this.getXSize();
    private double scrollY = (double) -MAX_Y + this.getYSize();
    private int inspectorX;
    private int inspectorY;
    private BarWidget score;
    private final Button unlock = Button.builder(Component.literal("Unlock"), (pressed) -> {
        Objects.requireNonNull(FOCUS_WIDGET.getFocus()).unlock();
        score.setValue(score.getValue() - FOCUS_WIDGET.getFocus().needScore);
    }).width(Mth.floor(90)).build();
    
    public TerminalScreen(TerminalMenu terminalMenu, Inventory inventory, Component component) {
        super(terminalMenu, inventory, component);
        
        this.inventoryLabelX = -1000;
        this.titleLabelX = -1000;
    }
    
    @Override
    protected void init() {
        FileWidget mana = registerTerminalWidget(new FileWidget(MMItems.MANA.get().getDefaultInstance(), 0, 0, new FileWidget.Properties()));
        FileWidget mana_bag = registerTerminalWidget(new FileWidget(MMItems.MANA_BAG.get().getDefaultInstance(), 50, 50, new FileWidget.Properties().parent(mana).needScore(25)));
        FileWidget mana_dice = registerTerminalWidget(new FileWidget(MMItems.MANA_DICE.get().getDefaultInstance(), 100, 75, new FileWidget.Properties().parent(mana_bag).fileWidgetType(FileWidgetType.UNCOMMON)));
        FileWidget mana_heart = registerTerminalWidget(new FileWidget(MMItems.MANA_HEART.get().getDefaultInstance(), 150, 50, new FileWidget.Properties().parent(mana_dice).fileWidgetType(FileWidgetType.RARE)));
        FileWidget texture = registerTerminalWidget(new FileWidget(new TextureInscribed(ManaMod.prefix("textures/item/mana_cake.png"), 16, 16), Component.literal("Test"), -50, -50, new FileWidget.Properties().parent(mana)));
        
        for (FileWidget fileWidget : fileWidgets) {
            this.addRenderableWidget(fileWidget);
        }
        
        this.score = new BarWidget(5, 5, 100, 10, Component.literal("Score"), 100, new TextureInscribed(ManaMod.prefixGui("terminal/score"), 8, 8), this.font);
        
        this.addRenderableWidget(FOCUS_WIDGET);
        this.addRenderableWidget(score);
        
        this.addInspectorWidget(new InspectorWidget(Mth.floor(this.width / 1.3D), 35, unlock));
        
        this.tickableWidgets.addAll(fileWidgets);
        this.tickableWidgets.add(FOCUS_WIDGET);
        this.tickableWidgets.add(score);
        
        this.inspectorX = Mth.floor(this.width - this.width / 1.35D) - 1;
        
        this.renderables.remove(this.unlock);
        
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
        ResourceLocation WIDGETS_LOCATION = AbstractWidget.WIDGETS_LOCATION;
        
        setTerminalColor(graphics);
        this.renderBackground(graphics);
        
        for (FileWidget fileWidget : fileWidgets) {
            fileWidget.setPosition(Mth.floor(scrollX + MAX_X + fileWidget.offsetX), Mth.floor(scrollY + MAX_Y + fileWidget.offsetY));
        }
        
        for (InspectorWidget inspectorWidget : inspectorWidgets) {
            inspectorWidget.widget.setPosition(inspectorX + inspectorWidget.offsetX, inspectorY + inspectorWidget.offsetY);
            AbstractWidget.WIDGETS_LOCATION = ManaMod.prefixGui("terminal/widgets");
            graphics.pose().pushPose();
            graphics.pose().translate(0, 0, 250);
            inspectorWidget.widget.render(graphics, mouseX, mouseY, partialTick);
            graphics.pose().popPose();
        }
        
        if (FOCUS_WIDGET.getFocus() != null) {
            graphics.pose().pushPose();
            graphics.pose().translate(inspectorX, inspectorY, 200);
            
            FileWidget selectedFile = FOCUS_WIDGET.getFocus();
            
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
            
            this.unlock.active = !selectedFile.unlock;
            this.unlock.visible = !selectedFile.obtained;
            
            this.score.setNeedValue(this.unlock.isFocused() ? selectedFile.needScore : 0);
            
            List<FormattedCharSequence> description = this.font.split(selectedFile.description, Mth.floor(90 * this.getGuiLeft()));
            
            for(int l = 0; l < description.size(); ++l) {
                graphics.drawString(this.font, description.get(l), Mth.floor(this.width / 1.3D), 65 + l * 9, 0, false);
            }
            
            graphics.pose().popPose();
        }
        
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
        
        if (FOCUS_WIDGET.getFocus() != null) {
            inspectorX = FOCUS_WIDGET.getFollowFocus() != null ? Mth.floor(Util.transformingNumber(0, Mth.floor(inspectorX), INSPECTOR_SPEED)) : Mth.floor(Util.transformingNumber(this.width - this.width / 1.35D, Mth.floor(inspectorX + 1), INSPECTOR_SPEED));
        }
        
        tickableWidgets.forEach(Tickable::tick);
    }
    
    @Override
    public void onClose() {
        FOCUS_WIDGET.setFollowFocus(null);
        
        super.onClose();
    }
    
    public static void setTerminalColor(GuiGraphics graphics) {
        graphics.setColor(COLOR.get(0), COLOR.get(1), COLOR.get(2), COLOR.get(3));
    }
    
    public void addInspectorWidget(InspectorWidget inspectorWidget) {
        this.addRenderableWidget(inspectorWidget.widget);
        inspectorWidgets.add(inspectorWidget);
    }
    
    public FileWidget registerTerminalWidget(FileWidget fileWidget) {
        fileWidgets.add(fileWidget);
        return fileWidget;
    }
    
    public record InspectorWidget(int offsetX, int offsetY, int width, int height, AbstractWidget widget) {
        public InspectorWidget(int offsetX, int offsetY, int width, int height, AbstractWidget widget) {
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.width = width;
            this.height = height;
            this.widget = widget;
            
            this.widget.setPosition(offsetX, offsetY);
            this.widget.setWidth(width);
            this.widget.setHeight(height);
        }
        
        public InspectorWidget(int offsetX, int offsetY, AbstractWidget widget) {
            this(offsetX, offsetY, widget.getWidth(), widget.getHeight(), widget);
        }
    }
}
