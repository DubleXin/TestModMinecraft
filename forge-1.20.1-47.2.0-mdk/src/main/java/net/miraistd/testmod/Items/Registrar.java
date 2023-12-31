package net.miraistd.testmod.Items;

import net.miraistd.testmod.Blocks.BlockRegistrarUtil;
import net.miraistd.testmod.Blocks.SupphireBlock.SapphireBlock;
import net.miraistd.testmod.Items.Halberd.Halberd;
import net.miraistd.testmod.Items.Sapphire.RawSapphire;
import net.miraistd.testmod.Items.Sapphire.Sapphire;
import net.miraistd.testmod.TestMod;

public class Registrar {
    public static void RegisterAll(){
        registerItems();
        registerBlocks();
        registerToEventBus();
    }
    private static void registerItems(){
        instantiateItems();
    }
    private static void registerBlocks(){
        instantiateBLocks();
    }

    private static void registerToEventBus() {
        ItemRegisterUtil.register(TestMod.ModEventBus);
        BlockRegistrarUtil.register(TestMod.ModEventBus);
    }

    private static void instantiateItems() {
        new Halberd().registerSelf();
        new Sapphire().registerSelf();
        new RawSapphire().registerSelf();
    }
    private static void instantiateBLocks() {
        new SapphireBlock().registerSelf();
    }
}
