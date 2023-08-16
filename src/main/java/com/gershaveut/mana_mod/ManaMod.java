package com.gershaveut.mana_mod;

import com.gershaveut.mana_mod.client.MMClientEvents;
import com.gershaveut.mana_mod.network.protocol.MMPacketHandler;
import com.gershaveut.mana_mod.world.effect.MMMobEffects;
import com.gershaveut.mana_mod.world.item.MMCreativeModeTabs;
import com.gershaveut.mana_mod.world.item.MMItems;
import com.gershaveut.mana_mod.world.level.block.MMBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
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
        MMMobEffects.MOB_EFFECTS.register(modEventBus);
        
        modEventBus.addListener(this::initialize);
        //modEventBus.addListener(this::registerEvent);
        //modEventBus.addListener(MMClientEvents::registerEvents);
        //modEventBus.register(MMClientEvents.class);
        
        //MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(MMClientEvents.class);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, MMConfig.SPEC);
    }
    
    private void initialize(FMLCommonSetupEvent event) {
        MMPacketHandler.registerMessages();
        
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(Items.POTION), Ingredient.of(MMItems.MANA_HEAT.get()), MMItems.MANA_POTION.get().getDefaultInstance()));
    }
    
    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name.toLowerCase(Locale.ROOT));
    }
    
    public static void Log(Level level, String message) {
        LOGGER.atLevel(level).log("[" + MODID + "] " + message);
    }
}
