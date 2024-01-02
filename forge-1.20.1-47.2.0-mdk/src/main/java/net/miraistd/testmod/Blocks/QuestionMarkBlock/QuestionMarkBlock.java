package net.miraistd.testmod.Blocks.QuestionMarkBlock;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.miraistd.testmod.Blocks.BlockRegistrarUtil;
import net.miraistd.testmod.Items.ICustomStaticEntity;
import net.miraistd.testmod.TestMod;

import java.util.function.Supplier;

public class QuestionMarkBlock implements ICustomStaticEntity {

    private static final String _name = "question_mark_block";
    @Override
    public String getName() {
        return _name;
    }

    @Override
    public void registerSelf() {
        BlockRegistrarUtil.RegisterBlock(
                (Void) ->
                        new Block(BlockBehaviour.Properties.copy(Blocks.BAMBOO_MOSAIC)
                                .sound(SoundType.COPPER)
                                .instabreak()
                        )
                , this
        );
    }
}
