package com.gershaveut.mana_mod.data;

import com.gershaveut.mana_mod.data.recipes.packs.MMRecipeProvider;
import com.gershaveut.mana_mod.data.worldgen.MMWorldGenProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

import static com.gershaveut.mana_mod.ManaMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMDataGenerator {
    private static DataGenerator dataGenerator;
    private static boolean includeServer;
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        dataGenerator = event.getGenerator();
        includeServer = event.includeServer();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        
        addProvider(new MMRecipeProvider(packOutput));
        addProvider(new MMWorldGenProvider(packOutput, lookupProvider));
    }
    
    private static <T extends DataProvider> void addProvider(T provider) {
        dataGenerator.addProvider(includeServer, provider);
    }
}
