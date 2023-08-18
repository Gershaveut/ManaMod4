package com.gershaveut.manamod.network.protocol;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.network.protocol.game.ManaFriedPacket;
import com.gershaveut.manamod.network.protocol.game.ManaStonePacket;
import com.gershaveut.manamod.network.protocol.game.ManaTalismanPacket;
import com.gershaveut.manamod.network.protocol.game.UnstableManaTalismanPacket;
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
        INSTANCE.registerMessage(id, ManaFriedPacket.class, ManaFriedPacket::encode, ManaFriedPacket::new, ManaFriedPacket::handle);
    }
}
