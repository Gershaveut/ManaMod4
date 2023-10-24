package com.gershaveut.manamod.network.protocol.game;

import com.gershaveut.manamod.world.item.Tooltip;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UnstableManaTalismanPacket {
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
            Tooltip item = ((Tooltip) message.itemStack.getItem());
            
            if (level.isDay()) {
                level.setDayTime(13000);
                time = item.manaMod$getFeedbackMessage("night");
            } else {
                level.setDayTime(6000);
                time = item.manaMod$getFeedbackMessage("day");
            }
            
            item.manaMod$setFeedback(time);
            sender.sendSystemMessage(time, true);
        });
        ctx.get().setPacketHandled(true);
    }
}
