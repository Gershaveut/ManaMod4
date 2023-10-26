package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.Util;
import com.gershaveut.manamod.util.TextureInscribed;
import com.gershaveut.manamod.world.item.Tooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.texture.Tickable;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileWidget extends AbstractButton implements Tickable {
    private static final ResourceLocation TERMINAL_WIDGETS = ManaMod.prefixGui("terminal/file_widget");
    private static final List<Float> BLINK = new ArrayList<>(TerminalScreen.COLOR);
    
    public final FileWidgetType fileWidgetType;
    public final @Nullable ItemStack item;
    public final @Nullable TextureInscribed texture;
    public final FileWidget parent;
    public final FocusWidget focusWidget;
    public Component description;
    public int needScore;
    public boolean unlock;
    public int offsetX;
    public int offsetY;
    public boolean focusing;
    public boolean obtained;
    public double timer;
    
    private final int width;
    private final int height;
    private double progress;
    private boolean blink;
    
    private FileWidget(@Nullable ItemStack item, @Nullable TextureInscribed texture, Component component, int offsetX, int offsetY, FileWidget.Properties properties) {
        super(0, 0, Mth.floor(10 * 2.5D), Mth.floor(10 * 2.5), component);
        
        this.width = 10;
        this.height = 10;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.item = item;
        this.texture = texture;
        this.parent = properties.parent;
        this.focusWidget = TerminalScreen.FOCUS_WIDGET;
        this.obtained = properties.parent == null || properties.obtained;
        this.blink = !this.obtained;
        this.fileWidgetType = properties.parent == null ? FileWidgetType.ROOT : properties.fileWidgetType;
        this.timer = properties.timer;
        this.description = properties.description != null ? properties.description : item != null ? ((Tooltip) item.getItem()).manaMod$getDescription().copy().withStyle(ChatFormatting.WHITE) : Component.empty();
        this.needScore = properties.needScore;
        
        this.update();
    }
    
    public FileWidget(ItemStack item, int offsetX, int offsetY, FileWidget.Properties properties) {
        this(item, null, Component.translatable(item.getDescriptionId()).withStyle(ChatFormatting.WHITE), offsetX, offsetY, properties);
    }
    
    public FileWidget(ItemStack item, Component component, int offsetX, int offsetY, FileWidget.Properties properties) {
        this(item, null, component, offsetX, offsetY, properties);
    }
    
    public FileWidget(TextureInscribed texture, Component component, int offsetX, int offsetY, FileWidget.Properties properties) {
        this(null, texture, component, offsetX, offsetY, properties);
    }
    
    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        TerminalScreen.setTerminalColor(graphics);
        
        if (parent != null)
            this.renderConnectivity(graphics, mouseX, mouseY, partialTick);
        
        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 1);
        
        if (!obtained && blink)
            graphics.setColor(BLINK.get(0), BLINK.get(1), BLINK.get(2), TerminalScreen.COLOR.get(3));
        graphics.blit(TERMINAL_WIDGETS, this.getX(), this.getY(), this.fileWidgetType.x, this.fileWidgetType.y, 24, 24);
        if (this.progress < 100)
            graphics.blit(TERMINAL_WIDGETS, this.getX(), this.getY(), this.fileWidgetType.x + 48, this.fileWidgetType.y, Mth.floor(this.getProgress() * 24), 24);
        else
            obtained = true;
        graphics.setColor(1F, 1F, 1F, 1F);
        
        if (item != null)
            graphics.renderFakeItem(item, getCenter(this.getX(), this.width), getCenter(this.getY(), this.height));
        else {
            assert this.texture != null;
            graphics.blitInscribed(this.texture.resourceLocation(), getCenter(this.getX(), this.width), getCenter(this.getY(), this.height), this.texture.boundsWidth(), this.texture.boundsHeight(), this.width, this.height);
        }
        
        graphics.pose().popPose();
    }
    
    public void renderConnectivity(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        int j = this.parent.getX() + 12;
        int k = this.parent.getY() + 13;
        int l = this.getX() + 13;
        int i1 = this.getY() + 13;
        int j1 = -1;
        
        graphics.hLine(l, j, i1, j1);
        graphics.vLine(j, i1, k, j1);
    }
    
    @Override
    public void tick() {
        this.update();
        
        if (blink) {
            Util.blinkingColor(BLINK, TerminalScreen.COLOR, 0.5F, 0.25F);
        }
    }
    
    private void update() {
        if (parent != null) {
            if (parent.visible) {
                this.visible = parent.obtained;
            } else {
                this.visible = false;
            }
        }
    }
    
    public void unlock() {
        this.unlock = true;
        this.blink = false;
        
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(() -> {
            if (this.progress < 100) {
                this.setProgress(this.progress + 25);
            } else {
                executor.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
    
    @Override
    public void onPress() {
        this.focusWidget.setFollowFocus(this);
        focusing = true;
    }
    
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
        this.defaultButtonNarrationText(narrationElementOutput);
    }
    
    public int getCenter(int coordinate, int direction) {
        return Mth.floor(coordinate + direction / 2D - direction / 2D * 0.1);
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    public double getProgress() {
        return progress / 100.0D;
    }
    
    public void setProgress(double progress) {
        this.progress = progress < 100 ? progress : 100;
    }
    
    public static class Properties {
        private FileWidget parent;
        private FileWidgetType fileWidgetType = FileWidgetType.COMMON;
        private boolean obtained;
        private double timer;
        private Component description;
        private int needScore;
        
        public Properties parent(FileWidget parent) {
            this.parent = parent;
            return this;
        }
        
        public Properties fileWidgetType(FileWidgetType fileWidgetType) {
            this.fileWidgetType = fileWidgetType;
            return this;
        }
        
        public Properties timer(double time) {
            this.timer = time;
            this.obtained = false;
            return this;
        }
        
        public Properties description(Component description) {
            this.description = description;
            return this;
        }
        
        public Properties needScore(int needScore) {
            this.needScore = needScore;
            return this;
        }
    }
}
