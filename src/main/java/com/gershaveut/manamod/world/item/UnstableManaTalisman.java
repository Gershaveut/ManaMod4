package com.gershaveut.manamod.world.item;

import com.gershaveut.manamod.MMConfig;
import com.gershaveut.manamod.network.protocol.MMPacketHandler;
import com.gershaveut.manamod.network.protocol.game.UnstableManaTalismanPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UnstableManaTalisman extends Item {
    public UnstableManaTalisman(Properties properties) {
        super(properties);
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        MMPacketHandler.INSTANCE.sendToServer(new UnstableManaTalismanPacket(this.getDefaultInstance()));
        
        if (MMConfig.SERVER.ITEM_COOLDOWN.get())
            player.getCooldowns().addCooldown(this, 100);
        
        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }
}
