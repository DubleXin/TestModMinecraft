package net.miraistd.testmod.Halberd;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.miraistd.testmod.TestMod;

public class Halberd {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);

    public static RegistryObject<Item> HalberdItem = ITEMS.register("halberd",
            ()-> new Item(new Item.Properties()));
    public static void  register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
