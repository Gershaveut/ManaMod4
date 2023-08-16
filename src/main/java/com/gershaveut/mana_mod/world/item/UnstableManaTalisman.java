package com.gershaveut.mana_mod.world.item;

import com.gershaveut.mana_mod.MMConfig;
import com.gershaveut.mana_mod.network.protocol.MMPacketHandler;
import com.gershaveut.mana_mod.network.protocol.game.UnstableManaTalismanPacket;
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
        
        if (MMConfig.itemCooldown)
            player.getCooldowns().addCooldown(this, 100);
        
        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }
}
