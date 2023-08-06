package com.gershaveut.mana_mod.world.item;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class TooltipBlockItem extends BlockItem {
    protected Tooltip tooltip;

    public TooltipBlockItem(Block block, Properties properties, Tooltip tooltip) {
        super(block, properties);
        this.tooltip = tooltip;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
        this.tooltip.appendHoverText(itemStack, level, tooltip, tooltipFlag, () -> {
            assert level != null;
            assert Minecraft.getInstance().player != null;
            use(level, Minecraft.getInstance().player, Minecraft.getInstance().player.getUsedItemHand());
        });
    }
}
