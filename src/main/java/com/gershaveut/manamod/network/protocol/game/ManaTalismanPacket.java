package com.gershaveut.manamod.network.protocol.game;

import com.gershaveut.manamod.world.item.Tooltip;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaTalismanPacket {
    public enum Weather {
        RAIN,
        THUNDER,
        CLEAR
    }
    
    private final ItemStack itemStack;
    private final Weather weather;
    
    public ManaTalismanPacket(Weather weather, ItemStack itemStack) {
        this.weather = weather;
        this.itemStack = itemStack;
    }
    
    public ManaTalismanPacket(FriendlyByteBuf buf) {
        this.weather = buf.readEnum(Weather.class);
        this.itemStack = buf.readItem();
    }
    
    public void encode(FriendlyByteBuf buf) {
        buf.writeEnum(weather);
        buf.writeItem(itemStack);
    }
    
    public static void handle(ManaTalismanPacket message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer sender = ctx.get().getSender();
            assert sender != null;
            ServerLevel level = sender.serverLevel();
            Tooltip item = ((Tooltip) message.itemStack.getItem());
            Component weather = switch (message.weather) {
                default -> {
                    level.setWeatherParameters(0, 6000, true, false);
                    yield item.manaMod$getFeedbackMessage("rain");
                }
                case THUNDER -> {
                    level.setWeatherParameters(0, 6000, true, true);
                    yield item.manaMod$getFeedbackMessage("thunder");
                }
                case CLEAR -> {
                    level.setWeatherParameters(6000, 0, false, false);
                    yield item.manaMod$getFeedbackMessage("clear");
                }
            };
            
            item.manaMod$setFeedback(weather);
            sender.sendSystemMessage(weather, true);
        });
        ctx.get().setPacketHandled(true);
    }
}
