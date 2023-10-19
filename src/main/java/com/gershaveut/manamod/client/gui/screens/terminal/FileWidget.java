package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FileWidget extends AbstractButton {
    private static final ResourceLocation TERMINAL_WIDGETS = ManaMod.prefixGui("terminal/terminal_widgets");
    private static final float[] FLASH = new float[4];
    private static final int FLASH_SPEED = 20;

    public final FileWidgetType fileWidgetType;
    public final @Nullable ItemStack item;
    public final @Nullable Texture texture;
    public final FileWidget parent;
    public final FocusWidget focusWidget;
    public int offsetX;
    public int offsetY;
    public boolean focusing;
    public boolean obtained;
    public double timer;
    private double progress;
    private final int width;
    private final int height;
    private boolean flashing;
    
    private FileWidget(@Nullable ItemStack item, @Nullable Texture texture, Component component, int offsetX, int offsetY, FileWidget.Properties properties) {
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
        this.flashing = !this.obtained;
        this.fileWidgetType = properties.parent == null ? FileWidgetType.ROOT : properties.fileWidgetType;
        this.timer = properties.timer;
        
        for (int i = 0; i < FLASH.length; i++) {
            FLASH[i] = TerminalScreen.COLOR[i] * FLASH_SPEED;
        }
        
        this.update();
    }
    
    public FileWidget(ItemStack item, int offsetX, int offsetY, FileWidget.Properties properties) {
        this(item, null, Component.translatable(item.getDescriptionId()).withStyle(ChatFormatting.WHITE), offsetX, offsetY, properties);
    }

    public FileWidget(ItemStack item, Component component, int offsetX, int offsetY, FileWidget.Properties properties) {
        this(item, null, component, offsetX, offsetY, properties);
    }

    public FileWidget(Texture texture, Component component, int offsetX, int offsetY, FileWidget.Properties properties) {
        this(null, texture, component, offsetX, offsetY, properties);
    }

    @Override
    public void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.setColor(TerminalScreen.COLOR[0], TerminalScreen.COLOR[1], TerminalScreen.COLOR[2], TerminalScreen.COLOR[3]);
        
        if (parent != null)
            this.renderConnectivity(graphics, mouseX, mouseY, partialTick);

        graphics.pose().pushPose();
        graphics.pose().translate(0, 0, 1);
        
        if (!obtained)
            graphics.setColor(FLASH[0] / FLASH_SPEED, FLASH[1] / FLASH_SPEED, FLASH[2] / FLASH_SPEED, FLASH[3]);
        graphics.blit(TERMINAL_WIDGETS, this.getX(), this.getY(), this.fileWidgetType.x, this.fileWidgetType.y, 24, 24);
        graphics.blit(TERMINAL_WIDGETS, this.getX(), this.getY(), this.fileWidgetType.x + 48, this.fileWidgetType.y, Mth.floor(this.getProgress() * 24), 24);
        graphics.setColor(1F, 1F, 1F, 1F);
        this.obtained = true;
        if (item != null)
            graphics.renderFakeItem(item, getCenter(this.getX(), this.width), getCenter(this.getY(), this.height));
        else {
            assert this.texture != null;
            graphics.blitInscribed(this.texture.resourceLocation, getCenter(this.getX(), this.width), getCenter(this.getY(), this.height), this.texture.boundsWidth, this.texture.boundsHeight, this.width, this.height);
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
    
    public void tick() {
        this.update();
        
        if (TerminalScreen.COLOR[0] * FLASH_SPEED <= FLASH[0])
            flashing = true;
        else if (TerminalScreen.COLOR[0] / 2 * FLASH_SPEED >= FLASH[0])
            flashing = false;
            
        for (int i = 0; i < 3; i++) {
            FLASH[i] += Math.signum((flashing ? TerminalScreen.COLOR[i] / 2 * FLASH_SPEED : TerminalScreen.COLOR[i] * FLASH_SPEED) - FLASH[i]);
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
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleWithFixedDelay(() -> {
            if (this.progress < 100) {
                this.setProgress(this.progress + 1);
            } else {
                executor.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
    
    @Override
    public void onPress() {
        this.focusWidget.setFollowFocus(this);
        focusing = true;
        this.unlock();
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
    
    public record Texture(ResourceLocation resourceLocation, int boundsWidth, int boundsHeight) {
    }

    public static class Properties {
        private FileWidget parent;
        private FileWidgetType fileWidgetType = FileWidgetType.COMMON;
        private boolean obtained = true;
        private double timer;

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
    }
}
