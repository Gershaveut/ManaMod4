package com.gershaveut.mana_mod.world.level.block;


import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.gershaveut.mana_mod.ManaMod.MODID;

public class Blocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Block> MANA_BLOCK = BLOCKS.register("mana_block", () -> new Block(BlockBehaviour.Properties.of().strength(0.5f).mapColor(MapColor.COLOR_CYAN).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> MANA_BRICKS = BLOCKS.register("mana_bricks", () -> new Block(BlockBehaviour.Properties.of().strength(1.0f, 4.5F).mapColor(MapColor.COLOR_CYAN).sound(SoundType.WOOL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> CHISELED_MANA_BRICKS = BLOCKS.register("chiseled_mana_bricks", () -> new Block(BlockBehaviour.Properties.copy(MANA_BRICKS.get())));
    public static final RegistryObject<Block> MANA_BRICK_WALL = BLOCKS.register("mana_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(MANA_BRICKS.get())));
    public static final RegistryObject<Block> MANA_BRICK_SLAB = BLOCKS.register("mana_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(MANA_BRICKS.get())));
    public static final RegistryObject<Block> MANA_CAKE = BLOCKS.register("mana_cake", () -> new CakeBlock(BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.WOOL)));
    public static final RegistryObject<Block> MANA_ORE = BLOCKS.register("mana_ore", () -> new Block(BlockBehaviour.Properties.of().strength(3.0F).mapColor(MapColor.COLOR_GRAY).sound(SoundType.WOOL).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MANA_SHIELD = BLOCKS.register("mana_shield", () -> new Block(BlockBehaviour.Properties.of().strength(-1.0f, 3600000.0F).mapColor(MapColor.COLOR_CYAN).sound(SoundType.GLASS)));
    public static final RegistryObject<Block> MANA_SYNTHESIZER = BLOCKS.register("mana_synthesizer", () -> new Block(BlockBehaviour.Properties.of().strength(3F).mapColor(MapColor.COLOR_MAGENTA).sound(SoundType.ANVIL)));
    public static final RegistryObject<Block> UNSTABLE_MANA_BLOCK = BLOCKS.register("unstable_mana_block", () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).mapColor(MapColor.COLOR_CYAN).sound(SoundType.WOOL)));
}
