package com.gershaveut.manamod.core.particles;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.gershaveut.manamod.ManaMod.MODID;

public class MMParticleType {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, MODID);
    
    public static final RegistryObject<SimpleParticleType> MANA_PARTICLE = PARTICLE_TYPES.register("mana_particle", () -> new SimpleParticleType(false));
}
