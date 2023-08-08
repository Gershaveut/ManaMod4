package com.gershaveut.mana_mod.network.protocol;

import com.gershaveut.mana_mod.ManaMod;
import com.gershaveut.mana_mod.network.protocol.game.ManaFriedPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class MMPacketHandler {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(ManaMod.prefix("main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void initialize() {
        int id = 0;
        INSTANCE.registerMessage(id++, ManaFriedPacket.class, ManaFriedPacket::encode, ManaFriedPacket::new, ManaFriedPacket::handle);
    }
}
