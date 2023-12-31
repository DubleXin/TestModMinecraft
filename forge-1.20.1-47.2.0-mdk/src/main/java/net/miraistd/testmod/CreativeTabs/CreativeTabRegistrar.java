package net.miraistd.testmod.CreativeTabs;

import mirai.action;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.miraistd.testmod.Items.ItemRegisterUtil;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.utils.ICustomRegistrar;

public class CreativeTabRegistrar implements ICustomRegistrar {
    public static final DeferredRegister<CreativeModeTab> TABS_REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TestMod.MOD_ID);

    public static void register(IEventBus eventBus){
        TABS_REGISTER.register(eventBus);
    }
    public static void add(String name, action<? extends CreativeModeTab, Void> action){
        TABS_REGISTER.register(name, ()-> action.Invoke(null));
    }
    public static void registerAll(){
        add("ragnarok_all", (Void) ->
                CreativeModeTab.builder().icon(() ->
                            new ItemStack(ItemRegisterUtil.getItemsMap().get("sapphire").getFirst().get()))
                        .title(Component.translatable("creativetab.ragnarok_all"))
                        .displayItems((params, output) -> addAllItemsToTab(output)
                        )
                        .build());


        register(TestMod.ModEventBus);
    }

    private static void addAllItemsToTab(CreativeModeTab.Output output) {
        for (String itemName : ItemRegisterUtil.getItemsMap().keySet()){
            var item = ItemRegisterUtil.getItemsMap().get(itemName).getFirst().get();
            output.accept(item);
        }
    }
}
