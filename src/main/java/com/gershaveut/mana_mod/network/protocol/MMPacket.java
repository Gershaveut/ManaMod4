package com.gershaveut.mana_mod.network.protocol;

import com.gershaveut.mana_mod.network.protocol.game.ManaFriedPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface MMPacket {
    default void encode(FriendlyByteBuf friendlyByteBuf) {
    }

    static void handle(ManaFriedPacket message, Supplier<NetworkEvent.Context> ctx) {
    }
}
