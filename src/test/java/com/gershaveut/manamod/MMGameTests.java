package com.gershaveut.manamod;

import com.gershaveut.manamod.world.item.MMItems;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraftforge.gametest.GameTestHolder;

import static com.gershaveut.manamod.ManaMod.MODID;

@GameTestHolder(MODID)
public class MMGameTests {
    @GameTest
    public static void test(GameTestHelper helper) {
        helper.assertTrue(MMItems.UNSTABLE_MANA_TALISMAN.get().use(helper.getLevel(), helper.makeMockPlayer(), InteractionHand.MAIN_HAND).getResult() == InteractionResult.PASS, "TEST");
    }
}
