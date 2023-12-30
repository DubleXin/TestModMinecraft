package net.miraistd.testmod.Items;

import net.miraistd.testmod.Items.Halberd.Halberd;
import net.miraistd.testmod.Items.Sapphire.RawSapphire;
import net.miraistd.testmod.Items.Sapphire.Sapphire;
import net.miraistd.testmod.TestMod;

public class Registrar {
    public static void RegisterAll(){
        registerItems();
        registerToEventBus();
    }
    private static void registerItems(){
        instantiateItems();
    }

    private static void registerToEventBus() {
        ItemRegisterUtil.register(TestMod.getModEventBus());
    }

    private static void instantiateItems() {
        new Halberd().registerSelf();
        new Sapphire().registerSelf();
        new RawSapphire().registerSelf();
    }
}
