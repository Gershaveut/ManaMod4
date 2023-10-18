package com.gershaveut.manamod.client.gui.screens.terminal;

import com.gershaveut.manamod.ManaMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FocusWidget extends AbstractWidget {
    private static final ResourceLocation FOCUS = ManaMod.prefixGui("terminal/focus");
    private static final int FOCUS_SPEED = 20;
    
    private FileWidget followFocus;
    public FileWidget lastFollowFocus;

    public FocusWidget(int width, int height) {
        super(0, 0, width, height, Component.literal("Focus"));
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (followFocus != null) {
            
            if (!followFocus.focusing) {
                this.setX(Mth.floor(followFocus.getX() - (double) (followFocus.getWidth() / 4)));
                this.setY(Mth.floor(followFocus.getY() - (double) (followFocus.getHeight() / 4)));
                this.setWidth(followFocus.getWidth());
                this.setHeight(followFocus.getHeight());
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
            double followFocusX = followFocus.getX() - (double) (followFocus.getWidth() / 4);
            double followFocusY = followFocus.getY() - (double) (followFocus.getHeight() / 4);
            
            if (followFocus.focusing) {
                for (int i = 0; i < FOCUS_SPEED; i++) {
                    this.setX(Mth.floor(this.getX() + Math.signum(followFocusX - this.getX())));
                    this.setY(Mth.floor(this.getY() + Math.signum(followFocusY - this.getY())));
                    this.setWidth(Mth.floor(this.getWidth() + Math.signum(followFocus.getWidth() - this.getWidth())));
                    this.setHeight(Mth.floor(this.getHeight() + Math.signum(followFocus.getHeight() - this.getHeight())));
                }
                
                if (this.getX() == followFocusX && this.getY() == followFocusY)
                    followFocus.focusing = false;
            }
        }
    }
    
    public void setFollowFocus(FileWidget followFocus) {
        this.lastFollowFocus = this.followFocus;
        this.followFocus = followFocus;
    }

    public FileWidget getFollowFocus() {
        return this.followFocus;
    }
}
