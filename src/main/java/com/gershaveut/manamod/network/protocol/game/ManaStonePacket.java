package com.gershaveut.manamod.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaStonePacket {
    public ManaStonePacket() {
    }
    
    public ManaStonePacket(FriendlyByteBuf buf) {
    }
    
    public void encode(FriendlyByteBuf buf) {
    }
    
    public static void handle(ManaStonePacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            assert sender != null;
            BlockPos spawn = sender.level().getSharedSpawnPos();
            sender.teleportTo(spawn.getX(), spawn.getY(), spawn.getZ());
            sender.resetFallDistance();
        });
        ctx.get().setPacketHandled(true);
    }
}
