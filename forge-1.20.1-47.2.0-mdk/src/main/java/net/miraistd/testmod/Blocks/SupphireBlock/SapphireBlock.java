package net.miraistd.testmod.Blocks.SupphireBlock;

import lombok.Getter;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.miraistd.testmod.Blocks.BlockRegistrarUtil;
import net.miraistd.testmod.Items.ICustomStaticEntity;
import net.miraistd.testmod.Items.ItemRegisterUtil;
@Getter
public class SapphireBlock implements ICustomStaticEntity {
    private static String _name;
    public SapphireBlock(){
        _name = "sapphire_block";
    }

    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void registerSelf() {
        BlockRegistrarUtil.RegisterBlock(
                (Void) ->
                        new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.AMETHYST))
                , this
        );
        ItemRegisterUtil.RegisterItem(
                (Void) ->
                    new BlockItem(BlockRegistrarUtil.getBlockMap().get(_name).getFirst().get() , new Item.Properties())
                , this
        );
    }
}
