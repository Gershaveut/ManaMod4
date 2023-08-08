package com.gershaveut.mana_mod;

import com.gershaveut.mana_mod.network.protocol.MMPacketHandler;
import com.gershaveut.mana_mod.world.item.MMCreativeModeTabs;
import com.gershaveut.mana_mod.world.item.MMItems;
import com.gershaveut.mana_mod.world.level.block.MMBlocks;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import java.util.Locale;

@Mod(ManaMod.MODID)
public class ManaMod {
    public static final String MODID = "mana_mod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ManaMod() {
        Log(Level.INFO, "Initializing");

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MMCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        MMBlocks.BLOCKS.register(modEventBus);
        MMItems.ITEMS.register(modEventBus);

        modEventBus.addListener(this::initialize);

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, MMConfig.SPEC);
    }

    public void initialize(FMLCommonSetupEvent event) {
        MMPacketHandler.registerMessages();
    }

    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
    }

    public static void Log(Level level, String message) {
        LOGGER.atLevel(level).log("[" + MODID + "] " + message);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        public static final KeyMapping KEY_DESCRIPTION_ITEM = new KeyMapping("key.mana_mod.key_description_item", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "key.categories.mana_mod");
        public static final KeyMapping KEY_USAGE_ITEM = new KeyMapping("key.mana_mod.key_usage_item", KeyConflictContext.GUI, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_CONTROL, "key.categories.mana_mod");

        @SubscribeEvent
        public static void registerBindings(RegisterKeyMappingsEvent event) {
            event.register(KEY_DESCRIPTION_ITEM);
            event.register(KEY_USAGE_ITEM);
        }
    }
}
