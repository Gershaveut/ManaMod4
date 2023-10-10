package com.gershaveut.manamod.network.protocol.game;

import com.gershaveut.manamod.network.protocol.MMPacket;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

import java.util.function.Supplier;

public class ManaBagPacket implements MMPacket {
    public ManaBagPacket() {
    }
    
    public ManaBagPacket(FriendlyByteBuf buf) {
    }
    
    public static void handle(ManaBagPacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            assert sender != null;
            NetworkHooks.openScreen(sender, new SimpleMenuProvider(
                    (containerId, playerInventory, player) -> ChestMenu.threeRows(containerId, playerInventory),
                    Component.translatable("item.mana_mod.mana_bag")
            ));
        });
        ctx.get().setPacketHandled(true);
    }
}
