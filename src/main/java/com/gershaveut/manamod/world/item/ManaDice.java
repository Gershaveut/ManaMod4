package com.gershaveut.manamod.world.item;

import com.gershaveut.manamod.MMConfig;
import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.network.protocol.MMPacketHandler;
import com.gershaveut.manamod.network.protocol.game.SendSystemMessagePacket;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class ManaDice extends Item {
    public ManaDice(Properties properties) {
        super(properties);
    }
    
    @OnlyIn(Dist.CLIENT)
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        Random random = new Random();
        
        try {
            for (int i = 0; i < random.nextInt(1, 3); i++) {
                player.addEffect(new MobEffectInstance((MobEffect) MobEffects.class.getFields()[random.nextInt(1, MobEffects.class.getFields().length)].get(MobEffect.class), random.nextInt(6000) + 1, random.nextInt(6) + 1, true, true));
            }
        } catch (IllegalAccessException exception) {
            ManaMod.Log(org.slf4j.event.Level.ERROR, exception.getLocalizedMessage());
        }
        
        MMPacketHandler.INSTANCE.sendToServer(new SendSystemMessagePacket(Component.translatable("item.mana_mod.mana_dice.feedback.use")));
        player.getItemInHand(interactionHand).hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        
        if (MMConfig.SERVER.ITEM_COOLDOWN.get())
            player.getCooldowns().addCooldown(this, 100);
        
        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }
}
