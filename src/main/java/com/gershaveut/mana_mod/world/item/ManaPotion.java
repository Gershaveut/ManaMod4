package com.gershaveut.mana_mod.world.item;

import com.gershaveut.mana_mod.world.effect.MMMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ManaPotion extends Item {
    public ManaPotion(Item.Properties properties) {
        super(properties);
    }
    
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        itemStack.hurtAndBreak(1, livingEntity, (p) -> p.broadcastBreakEvent(EquipmentSlot.MAINHAND));
        livingEntity.addEffect(new MobEffectInstance(MMMobEffects.MANA_FLIGHT.get(), 6000, 0, true, true));
        return itemStack;
    }
    
    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 48;
    }
    
    @Override
    public UseAnim getUseAnimation(ItemStack itemStack) {
        return UseAnim.DRINK;
    }
}
