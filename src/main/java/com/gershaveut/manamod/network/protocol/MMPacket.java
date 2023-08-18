package com.gershaveut.manamod.network.protocol;

import net.minecraft.network.FriendlyByteBuf;

public interface MMPacket {
    default void encode(FriendlyByteBuf buf) {
    }
}
