package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import javax.annotation.Nullable;

public class FocusWidget extends AbstractWidget {
    private static final ResourceLocation FOCUS = ManaMod.prefixGui("terminal/focus");
    private static final int FOCUS_SPEED = 20;
    
    private @Nullable FileWidget followFocus;
    private double followFocusX;
    private double followFocusY;
    public @Nullable FileWidget lastFollowFocus;

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
            
            graphics.setColor(TerminalScreen.COLOR[0], TerminalScreen.COLOR[1], TerminalScreen.COLOR[2], TerminalScreen.COLOR[3]);
            graphics.blitInscribed(FOCUS, Mth.floor(this.getX()), Mth.floor(this.getY()), 28, 28, followFocus.getWidth(), followFocus.getHeight());
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }
    
    public void tick() {
        if (followFocus != null) {
            if (followFocus.focusing) {
                for (int i = 0; i < FOCUS_SPEED; i++) {
                    this.setX(Mth.floor(this.getX() + Math.signum(followFocusX - this.getX())));
                    this.setY(Mth.floor(this.getY() + Math.signum(followFocusY - this.getY())));
                }
                
                if (this.getX() == followFocusX && this.getY() == followFocusY)
                    followFocus.focusing = false;
            }
        }
    }
    
    public void setFollowFocus(@Nullable FileWidget followFocus) {
        this.lastFollowFocus = this.followFocus;
        this.followFocus = followFocus;
    }

    public @Nullable FileWidget getFollowFocus() {
        return this.followFocus;
    }
}
