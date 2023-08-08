package com.gershaveut.mana_mod.network.protocol.game;

import com.gershaveut.mana_mod.network.protocol.MMPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaFriedPacket implements MMPacket {
    public LivingEntity livingEntity;

    public ManaFriedPacket(LivingEntity livingEntity) {
        this.livingEntity = livingEntity;
    }

    public ManaFriedPacket(FriendlyByteBuf friendlyByteBuf) {
    }

    public static void handle(ManaFriedPacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            message.livingEntity.heal(2F);
            message.livingEntity.setArrowCount(0);
        });
        ctx.get().setPacketHandled(true);
    }
}
