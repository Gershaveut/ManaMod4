package com.gershaveut.mana_mod.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.player.Player;

public class ManaFlight extends MobEffect {
    public ManaFlight() {
        super(MobEffectCategory.BENEFICIAL, 15792383);
    }
    
    @Override
    public void addAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int amplifier) {
        if (livingEntity instanceof Player player) {
            player.getAbilities().mayfly = true;
            if (amplifier > 0)
                player.getAbilities().setFlyingSpeed(0.025F * amplifier);
            player.onUpdateAbilities();
        }
    }
    
    @Override
    public void removeAttributeModifiers(LivingEntity livingEntity, AttributeMap attributeMap, int amplifier) {
        if (livingEntity instanceof Player player) {
            if (!player.isCreative()) {
                player.getAbilities().mayfly = false;
                player.getAbilities().flying = false;
            }
            player.getAbilities().setFlyingSpeed(0.05F);
            player.onUpdateAbilities();
        }
    }
    
    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) {
        if (livingEntity instanceof Player player)
            player.getAbilities().mayfly = true;
    }
    
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
