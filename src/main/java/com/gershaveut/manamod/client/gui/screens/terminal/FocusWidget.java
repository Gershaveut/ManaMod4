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

    private final int FOCUS_SPEED = 5;
    private FileWidget followFocus;
    public FileWidget lastFollowFocus;

    public FocusWidget(int width, int height) {
        super(0, 0, width, height, Component.literal("Focus"));
    }

    @Override
    protected void renderWidget(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        if (followFocus != null) {
            double followFocusX = followFocus.getX() - (double) (followFocus.getWidth() / 4);
            double followFocusY = followFocus.getY() - (double) (followFocus.getHeight() / 4);

            if (followFocus.getFocusing()) {
                for (int i = 0; i < FOCUS_SPEED; i++) {
                    this.setX(this.getX() + Math.signum(followFocusX - this.getX()));
                    this.setY(this.getY() + Math.signum(followFocusY - this.getY()));
                    this.setWidth(this.getWidth() + Math.signum(followFocus.getWidth() - this.getWidth()));
                    this.setHeight(this.getHeight() + Math.signum(followFocus.getHeight() - this.getHeight()));
                }

                if (this.getX() == followFocusX && this.getY() == followFocusY)
                    followFocus.setFocusing(false);
            } else {
                this.setX(followFocusX);
                this.setY(followFocusY);
                this.setWidth(followFocus.getWidth());
                this.setHeight(followFocus.getHeight());
            }
            
            graphics.blitInscribed(FOCUS, Mth.floor(this.getX()), Mth.floor(this.getY()), 28, 28, followFocus.getWidth(), followFocus.getHeight());
        }
    }

    @Override
    protected void updateWidgetNarration(NarrationElementOutput narrationElementOutput) {
    }

    public void setX(double x) {
        this.setX(Mth.floor(x));
    }

    public void setY(double y) {
        this.setY(Mth.floor(y));
    }

    public void setWidth(double x) {
        this.setWidth(Mth.floor(x));
    }

    public void setHeight(double y) {
        this.setHeight(Mth.floor(y));
    }

    public void setFollowFocus(FileWidget followFocus) {
        this.lastFollowFocus = this.followFocus;
        this.followFocus = followFocus;
    }

    public FileWidget getFollowFocus() {
        return this.followFocus;
    }
}
