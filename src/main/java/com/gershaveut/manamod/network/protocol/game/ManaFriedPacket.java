package com.gershaveut.manamod.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaFriedPacket {
    public ManaFriedPacket() {
    }
    
    public ManaFriedPacket(FriendlyByteBuf buf) {
    }
    
    public void encode(FriendlyByteBuf buf) {
    }
    
    public static void handle(ManaFriedPacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            assert sender != null;
            sender.setRespawnPosition(sender.getCommandSenderWorld().dimension(), sender.getOnPos(), sender.getRespawnAngle(), true, true);
        });
        ctx.get().setPacketHandled(true);
    }
}
