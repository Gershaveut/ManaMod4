package com.gershaveut.manamod.network.protocol;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.network.protocol.game.*;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class MMPacketHandler {
    private static final String PROTOCOL_VERSION = "1.0";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(ManaMod.prefix("main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    
    public static void registerMessages() {
        int id = 0;
        INSTANCE.registerMessage(id++, UnstableManaTalismanPacket.class, UnstableManaTalismanPacket::encode, UnstableManaTalismanPacket::new, UnstableManaTalismanPacket::handle);
        INSTANCE.registerMessage(id++, ManaTalismanPacket.class, ManaTalismanPacket::encode, ManaTalismanPacket::new, ManaTalismanPacket::handle);
        INSTANCE.registerMessage(id++, ManaStonePacket.class, ManaStonePacket::encode, ManaStonePacket::new, ManaStonePacket::handle);
        INSTANCE.registerMessage(id++, SendSystemMessagePacket.class, SendSystemMessagePacket::encode, SendSystemMessagePacket::new, SendSystemMessagePacket::handle);
        INSTANCE.registerMessage(id++, ManaBagPacket.class, ManaBagPacket::encode, ManaBagPacket::new, ManaBagPacket::handle);
        INSTANCE.registerMessage(id, ManaFriedPacket.class, ManaFriedPacket::encode, ManaFriedPacket::new, ManaFriedPacket::handle);
    }
}
