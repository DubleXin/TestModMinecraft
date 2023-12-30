package net.miraistd.testmod.Items;

import net.miraistd.testmod.Items.Halberd.Halberd;

public class Registrar {
    public static void RegisterAll(){
        registerItems();
    }
    private static void registerItems(){
        instantiateItems();
    }

    private static void instantiateItems() {
        new Halberd().registerSelf();
    }
}
