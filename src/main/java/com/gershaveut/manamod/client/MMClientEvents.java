package com.gershaveut.manamod.client;

import com.gershaveut.manamod.client.particle.ManaParticle;
import com.gershaveut.manamod.core.particles.MMParticleType;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.gershaveut.manamod.ManaMod.MODID;

public class MMClientEvents {
    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class MMClientForgeEvents {
        @SubscribeEvent
        public static void onKeyPressed(ScreenEvent.KeyPressed event) {
            if (KeyMappings.KEY_DESCRIPTION_ITEM.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), event.getScanCode())))
                KeyMappings.KEY_DESCRIPTION_ITEM.setDown(true);

            if (KeyMappings.KEY_USAGE_ITEM.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), event.getScanCode())))
                KeyMappings.KEY_USAGE_ITEM.setDown(true);
        }

        @SubscribeEvent
        public static void onKeyReleased(ScreenEvent.KeyReleased event) {
            if (KeyMappings.KEY_DESCRIPTION_ITEM.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), event.getScanCode())))
                KeyMappings.KEY_DESCRIPTION_ITEM.setDown(false);

            if (KeyMappings.KEY_USAGE_ITEM.isActiveAndMatches(InputConstants.getKey(event.getKeyCode(), event.getScanCode())))
                KeyMappings.KEY_USAGE_ITEM.setDown(true);
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class MMClientModBusEvents {
        @SubscribeEvent
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            event.register(KeyMappings.KEY_DESCRIPTION_ITEM);
            event.register(KeyMappings.KEY_USAGE_ITEM);
        }

        @SubscribeEvent
        public static void registerParticles(RegisterParticleProvidersEvent event) {
            event.registerSpriteSet(MMParticleType.MANA_PARTICLE.get(), ManaParticle.Provider::new);
        }
    }
}
