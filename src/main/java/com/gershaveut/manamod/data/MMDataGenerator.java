package com.gershaveut.manamod.data;

import com.gershaveut.manamod.data.models.MMBlockModelProvider;
import com.gershaveut.manamod.data.models.MMItemModelProvider;
import com.gershaveut.manamod.data.models.blockstates.MMBlockStateProvider;
import com.gershaveut.manamod.data.recipes.packs.MMRecipeProvider;
import com.gershaveut.manamod.data.worldgen.MMWorldGenProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

import static com.gershaveut.manamod.ManaMod.MODID;

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
        ExistingFileHelper fileHelper = event.getExistingFileHelper();
        
        addProvider(new MMRecipeProvider(packOutput));
        addProvider(new MMWorldGenProvider(packOutput, lookupProvider));
        addProvider(new MMItemModelProvider(packOutput, fileHelper));
        addProvider(new MMBlockModelProvider(packOutput, fileHelper));
        addProvider(new MMLanguageProvider(packOutput));
        addProvider(new MMBlockStateProvider(packOutput, fileHelper));
    }
    
    private static <T extends DataProvider> void addProvider(T provider) {
        dataGenerator.addProvider(includeServer, provider);
    }
}
