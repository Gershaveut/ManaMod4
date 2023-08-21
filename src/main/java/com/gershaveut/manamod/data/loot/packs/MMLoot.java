package com.gershaveut.manamod.data.loot.packs;

import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;

import java.util.Collections;
import java.util.Set;

public class MMLoot {
    private static final Set<ResourceLocation> MM_LOOT = Sets.newHashSet();
    
    private static ResourceLocation register(ResourceLocation id) {
        if (MM_LOOT.add(id))
            return id;
        
        throw new IllegalArgumentException(String.valueOf(id));
    }
    
    public static Set<ResourceLocation> allBuiltin() {
        return Collections.unmodifiableSet(MM_LOOT);
    }
}
