package com.gershaveut.mana_mod.data;

import com.gershaveut.mana_mod.data.recipes.packs.MMRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.gershaveut.mana_mod.ManaMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MMDataGenerator {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator dataGenerator = event.getGenerator();
        PackOutput packOutput = event.getGenerator().getPackOutput();
        
        dataGenerator.addProvider(event.includeServer(), new MMRecipeProvider(packOutput));
    }
}
