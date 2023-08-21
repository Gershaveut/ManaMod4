package com.gershaveut.manamod.data;

import com.gershaveut.manamod.ManaMod;
import com.gershaveut.manamod.core.particles.MMParticleType;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ParticleDescriptionProvider;

public class MMParticleDescriptionProvider extends ParticleDescriptionProvider {
    protected MMParticleDescriptionProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper);
    }
    
    @Override
    protected void addDescriptions() {
        sprite(MMParticleType.MANA_PARTICLE.get(), ManaMod.prefix("mana_particle"));
    }
}
