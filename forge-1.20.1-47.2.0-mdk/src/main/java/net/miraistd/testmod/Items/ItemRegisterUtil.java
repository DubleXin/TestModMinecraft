package net.miraistd.testmod.Items;

import lombok.Getter;
import mirai.action;
import mirai.pair;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.miraistd.testmod.TestMod;

import java.util.HashMap;

public class ItemRegisterUtil {

    @Getter
    private static HashMap<String, pair<RegistryObject<Item>, ICustomStaticEntity>> ItemsMap = new HashMap<>();
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
    public static void RegisterItem(action<? extends Item, Void> action, ICustomStaticEntity item) {
        ItemsMap.put(item.getName(),
                new pair<>(ITEMS.register(item.getName(), ()-> action.Invoke(null)), item));
    }

}
