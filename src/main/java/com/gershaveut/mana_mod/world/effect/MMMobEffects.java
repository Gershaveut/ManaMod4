package com.gershaveut.mana_mod.world.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.gershaveut.mana_mod.ManaMod.MODID;

public class MMMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MODID);
    
    public static final RegistryObject<MobEffect> MANA_FLIGHT = MOB_EFFECTS.register("mana_flight", ManaFlight::new);
    public static final RegistryObject<MobEffect> MANAIFICATION = MOB_EFFECTS.register("manaification", Manaification::new);
}
