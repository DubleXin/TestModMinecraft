package net.miraistd.testmod.Items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.miraistd.testmod.TestMod;

import java.util.Dictionary;
import java.util.Hashtable;

public class ItemRegisterUtil {
    Dictionary<String, Integer> ItemsDictionary = new Hashtable<>();
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
    public ItemRegisterUtil(){
        //ItemsDictionary.put("key", 80085);
        //ItemsDictionary
    }
}
