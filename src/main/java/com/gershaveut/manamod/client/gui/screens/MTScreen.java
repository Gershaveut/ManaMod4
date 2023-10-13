package com.gershaveut.manamod.client.gui.screens;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.world.item.MMItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class MTScreen extends Screen {
    public MTScreen(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics);

        for(int i1 = -1; i1 <= 15; ++i1) {
            for(int j1 = -1; j1 <= 8; ++j1) {
                graphics.blit(ManaMod.prefix("mana"), 16 * i1, j1, 0.0F, 0.0F, 16, 16, 16, 16);
            }
        }

        super.render(graphics, mouseX, mouseY, partialTick);
    }
}
