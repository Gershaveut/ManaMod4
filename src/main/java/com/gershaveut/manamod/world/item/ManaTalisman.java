package com.gershaveut.manamod.world.item;

import com.gershaveut.manamod.MMConfig;
import com.gershaveut.manamod.network.protocol.MMPacketHandler;
import com.gershaveut.manamod.network.protocol.game.ManaTalismanPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ManaTalisman extends Item {
    public ManaTalisman(Item.Properties properties) {
        super(properties);
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack itemStack = this.getDefaultInstance();
        
        if (player.isShiftKeyDown()) {
            MMPacketHandler.INSTANCE.sendToServer(new ManaTalismanPacket(ManaTalismanPacket.Weather.RAIN, itemStack));
        } else if (InteractionHand.MAIN_HAND == interactionHand) {
            MMPacketHandler.INSTANCE.sendToServer(new ManaTalismanPacket(ManaTalismanPacket.Weather.CLEAR, itemStack));
        } else if (InteractionHand.OFF_HAND == interactionHand) {
            MMPacketHandler.INSTANCE.sendToServer(new ManaTalismanPacket(ManaTalismanPacket.Weather.THUNDER, itemStack));
        }
        
        if (MMConfig.itemCooldown)
            player.getCooldowns().addCooldown(this, 100);
        
        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }
}
