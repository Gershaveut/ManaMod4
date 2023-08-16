package com.gershaveut.mana_mod.world.level.block;

import com.gershaveut.mana_mod.world.effect.MMMobEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ManaCake extends CakeBlock {
    public ManaCake(Properties properties) {
        super(properties);
    }
    
    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (player.canEat(false))
            player.addEffect(new MobEffectInstance(MMMobEffects.MANAIFICATION.get(), 60, 1, true, true));
        
        return super.use(blockState, level, blockPos, player, interactionHand, blockHitResult);
    }
}
