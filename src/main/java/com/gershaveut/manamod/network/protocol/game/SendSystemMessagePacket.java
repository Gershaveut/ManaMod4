package com.gershaveut.manamod.network.protocol.game;

import com.gershaveut.manamod.network.protocol.MMPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SendSystemMessagePacket implements MMPacket {
    private final Component component;

    public SendSystemMessagePacket(Component component) {
        this.component = component;
    }

    public SendSystemMessagePacket(FriendlyByteBuf buf) {
        this.component = buf.readComponent();
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeComponent(this.component);
    }

    public static void handle(SendSystemMessagePacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            assert sender != null;
            sender.sendSystemMessage(message.component, true);
        });
        ctx.get().setPacketHandled(true);
    }
}
