package com.gershaveut.mana_mod.network.protocol.game;

import com.gershaveut.mana_mod.network.protocol.MMPacket;
import com.gershaveut.mana_mod.world.item.Tooltip;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UnstableManaTalismanPacket implements MMPacket {
    private final ItemStack itemStack;
    
    public UnstableManaTalismanPacket(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
    
    public UnstableManaTalismanPacket(FriendlyByteBuf buf) {
        this.itemStack = buf.readItem();
    }
    
    public void encode(FriendlyByteBuf buf) {
        buf.writeItem(itemStack);
    }
    
    public static void handle(UnstableManaTalismanPacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            assert sender != null;
            ServerLevel level = sender.serverLevel();
            Component time;
            
            if (level.isDay()) {
                level.setDayTime(13000);
                time = Component.translatable("item.mana_mod.unstable_mana_talisman.night");
            } else {
                level.setDayTime(6000);
                time = Component.translatable("item.mana_mod.unstable_mana_talisman.day");
            }
            
            ((Tooltip) message.itemStack.getItem()).manaMod$setFeedback(time);
            sender.sendSystemMessage(time, true);
        });
        ctx.get().setPacketHandled(true);
    }
}
