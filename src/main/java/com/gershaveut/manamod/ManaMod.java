package com.gershaveut.manamod;

import com.gershaveut.manamod.core.particles.MMParticleType;
import com.gershaveut.manamod.network.protocol.MMPacketHandler;
import com.gershaveut.manamod.world.effect.MMMobEffects;
import com.gershaveut.manamod.world.inventory.MMMenuType;
import com.gershaveut.manamod.world.item.MMCreativeModeTabs;
import com.gershaveut.manamod.world.item.MMItems;
import com.gershaveut.manamod.world.level.block.MMBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.event.Level;

@Mod(ManaMod.MODID)
public class ManaMod {
    public static final String MODID = "mana_mod";
    
    public ManaMod() {
        Log(Level.INFO, "Initializing");
        
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        MMCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        MMBlocks.BLOCKS.register(modEventBus);
        MMItems.ITEMS.register(modEventBus);
        MMMobEffects.MOB_EFFECTS.register(modEventBus);
        MMParticleType.PARTICLE_TYPES.register(modEventBus);
        MMMenuType.MENUS.register(modEventBus);
        
        modEventBus.addListener(this::initialize);
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, MMConfig.CLIENT.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, MMConfig.SERVER.SPEC);
    }
    
    public static ResourceLocation prefix(String name) {
        return new ResourceLocation(MODID, name);
    }
    
    public static ResourceLocation prefixGui(String name) {
        return new ResourceLocation(MODID, "textures/gui/" + name + ".png");
    }
    
    public static void Log(Level level, String message) {
        LogUtils.getLogger().atLevel(level).log("[" + MODID + "] " + message);
    }
    
    private void initialize(FMLCommonSetupEvent event) {
        MMPacketHandler.registerMessages();
        
        BrewingRecipeRegistry.addRecipe(new BrewingRecipe(Ingredient.of(Items.POTION), Ingredient.of(MMItems.MANA_HEART.get()), MMItems.MANA_POTION.get().getDefaultInstance()));
    }
}
