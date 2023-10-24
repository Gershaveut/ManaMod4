package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;

public class FocusWidget extends AbstractWidget {
    private static final ResourceLocation FOCUS = ManaMod.prefixGui("terminal/focus");
    private static final int FOCUS_SPEED = 10;
    
    public @Nullable FileWidget lastFollowFocus;
    private @Nullable FileWidget followFocus;
    private double followFocusX;
    private double followFocusY;
    
    public FocusWidget(int width, int height) {
        super(0, 0, width, height, Component.literal("Focus"));
    }
    
    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (followFocus != null) {
            followFocusX = followFocus.getX() - (double) (followFocus.getWidth() / 4);
            followFocusY = followFocus.getY() - (double) (followFocus.getHeight() / 4);
            
            if (!followFocus.focusing) {
                this.setX(Mth.floor(followFocusX));
                this.setY(Mth.floor(followFocusY));
            }
            
            TerminalScreen.setTerminalColor(graphics);
            graphics.blitInscribed(FOCUS, this.getX(), this.getY(), 28, 28, followFocus.getWidth(), followFocus.getHeight());
        }
    }
    
    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }
    
    public void tick() {
        if (followFocus != null) {
            if (followFocus.focusing) {
                this.setX(Mth.floor(Util.transformingNumber(followFocusX, this.getX(), FOCUS_SPEED)));
                this.setY(Mth.floor(Util.transformingNumber(followFocusY, this.getY(), FOCUS_SPEED)));
                
                if (this.getX() == followFocusX && this.getY() == followFocusY)
                    followFocus.focusing = false;
            }
        }
    }
    
    public @Nullable FileWidget getFollowFocus() {
        return this.followFocus;
    }
    
    public void setFollowFocus(@Nullable FileWidget followFocus) {
        this.lastFollowFocus = this.followFocus;
        this.followFocus = followFocus;
    }
    
    public @Nullable FileWidget getFocus() {
        return this.followFocus != null ? this.followFocus : this.lastFollowFocus;
    }
}
