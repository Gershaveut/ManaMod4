package com.gershaveut.mana_mod.world.item;

import com.gershaveut.mana_mod.network.protocol.MMPacketHandler;
import com.gershaveut.mana_mod.network.protocol.game.ManaFriedPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

public class ManaFried extends Item {
    public ManaFried(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        MMPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with((Supplier<ServerPlayer>) livingEntity), new ManaFriedPacket());
        return super.finishUsingItem(itemStack, level, livingEntity);
    }
}
