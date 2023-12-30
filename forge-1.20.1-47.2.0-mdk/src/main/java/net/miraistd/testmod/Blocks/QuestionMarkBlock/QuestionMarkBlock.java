package net.miraistd.testmod.Blocks.QuestionMarkBlock;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.miraistd.testmod.TestMod;

import java.util.function.Supplier;

public class QuestionMarkBlock {
    public static DeferredRegister<Block> Blocks =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);

    public static void register(IEventBus eventBus){
        Blocks.register(eventBus);
    }

    private static <T extends Block>RegistryObject<T> registrBlock(String name, Supplier<T> block, CreativeModeTab tab){
        return Blocks.register(name, block);
    }
    // TODO: make to the end
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab){
        return null;
    }
}
