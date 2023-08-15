package com.gershaveut.mana_mod.network.protocol.game;

import com.gershaveut.mana_mod.network.protocol.MMPacket;
import com.gershaveut.mana_mod.world.item.Tooltip;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaTalismanPacket implements MMPacket {
    private final ItemStack itemStack;
    
    public ManaTalismanPacket(ItemStack itemStack) {
        this.itemStack = itemStack;
    }
    
    public ManaTalismanPacket(FriendlyByteBuf buf) {
        this.itemStack = buf.readItem();
    }
    
    public void encode(FriendlyByteBuf buf) {
        buf.writeItem(itemStack);
    }
    
    public static void handle(ManaTalismanPacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            assert sender != null;
            ServerLevel level = sender.serverLevel();
            
            if (level.isRaining()) {
                level.setRainLevel(0);
                level.setThunderLevel(0);
                Component clear = Component.translatable("item.mana_mod.mana_talisman.clear");
                ((Tooltip) message.itemStack.getItem()).manaMod$setFeedback(clear);
                sender.sendSystemMessage(clear, true);
            } else {
                level.setRainLevel(6000);
                Component rain = Component.translatable("item.mana_mod.mana_talisman.rain");
                ((Tooltip) message.itemStack.getItem()).manaMod$setFeedback(rain);
                sender.sendSystemMessage(rain, true);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
