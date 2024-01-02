package net.miraistd.testmod.Blocks;

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
import net.miraistd.testmod.Items.ICustomStaticEntity;
import net.miraistd.testmod.Items.ItemRegisterUtil;
import net.miraistd.testmod.TestMod;

import java.util.HashMap;

public class BlockRegistrarUtil {
    @Getter
    private static HashMap<String, pair<RegistryObject<Block>, ICustomStaticEntity>> BlockMap = new HashMap<>();
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
    public static void RegisterBlock(action<? extends Block, Void> action, ICustomStaticEntity block) {
        BlockMap.put(block.getName(),
                new pair<>(BLOCKS.register(block.getName(), ()-> action.Invoke(null)), block));
        ItemRegisterUtil.RegisterItem(
                (Void) ->
                        new BlockItem(BlockRegistrarUtil.getBlockMap().get(block.getName()).getFirst().get()
                                , new Item.Properties())
                , block);
    }
}
