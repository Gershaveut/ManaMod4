package com.gershaveut.mana_mod;

import com.gershaveut.mana_mod.world.item.CreativeModeTabs;
import com.gershaveut.mana_mod.world.item.Items;
import com.gershaveut.mana_mod.world.item.TooltipItem;
import com.mojang.logging.LogUtils;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.event.Level;

import static com.gershaveut.mana_mod.ManaMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
@Mod(ManaMod.MODID)
public class ManaMod {
    public static final String MODID = "mana_mod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ManaMod() {
        Log(Level.INFO, "Initializing");

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        CreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        Items.BLOCKS.register(modEventBus);
        Items.ITEMS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(TooltipItem.keyTooltip);
    }

    public static void Log(Level level, String message) {
        LOGGER.atLevel(level).log("[" + MODID + "] " + message);
    }
}
