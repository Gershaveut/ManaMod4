package com.gershaveut.manamod.client.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class ManaParticle extends TextureSheetParticle {
    protected ManaParticle(ClientLevel level, double x, double y, double z, double vx, double vy, double vz) {
        super(level, x, y, z, vx, vy, vz);
        
        this.lifetime = 50;
        this.hasPhysics = true;
    }
    
    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }
    
    public record Provider(SpriteSet sprite) implements ParticleProvider<SimpleParticleType> {
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            ManaParticle manaParticle = new ManaParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            manaParticle.pickSprite(this.sprite);
            return manaParticle;
        }
    }
}
