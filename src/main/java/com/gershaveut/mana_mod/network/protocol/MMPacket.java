package com.gershaveut.mana_mod.network.protocol;

import net.minecraft.network.FriendlyByteBuf;

public interface MMPacket {
    default void encode(FriendlyByteBuf buf) {
    }
}
