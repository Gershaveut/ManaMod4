package com.gershaveut.mana_mod.world.item;

import com.gershaveut.mana_mod.MMConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ManaStone extends Item {
    public ManaStone(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        try {
            BlockPos spawn = level.getSharedSpawnPos();
            player.teleportTo(spawn.getX(), spawn.getY(), spawn.getZ());
            if (MMConfig.itemCooldown)
                player.getCooldowns().addCooldown(this, 1000);
        } catch (Exception exception) {
            Component feedback = Component.literal(exception.getLocalizedMessage());
            ((Tooltip) this).manaMod$setFeedback(feedback);
            player.sendSystemMessage(feedback);
        }
        
        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }
}
