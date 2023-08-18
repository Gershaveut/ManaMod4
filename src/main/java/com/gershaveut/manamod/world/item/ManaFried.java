package com.gershaveut.manamod.world.item;

import com.gershaveut.manamod.network.protocol.MMPacketHandler;
import com.gershaveut.manamod.network.protocol.game.ManaFriedPacket;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ManaFried extends Item {
    public ManaFried(Properties properties) {
        super(properties);
    }
    
    @Override
    @OnlyIn(Dist.CLIENT)
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        MMPacketHandler.INSTANCE.sendToServer(new ManaFriedPacket());
        return super.finishUsingItem(itemStack, level, livingEntity);
    }
}
