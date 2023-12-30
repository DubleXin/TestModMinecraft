package net.miraistd.testmod;

import com.mojang.logging.LogUtils;
import lombok.AccessLevel;
import lombok.Getter;
import net.minecraft.network.chat.Component;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.miraistd.testmod.Items.Halberd.Halberd;
import net.miraistd.testmod.Blocks.QuestionMarkBlock.QuestionMarkBlock;
import net.miraistd.testmod.client.ExtendedPlayer;
import net.miraistd.testmod.client.gui.core.HUD;
import org.slf4j.Logger;


@Mod(TestMod.MOD_ID)
public class TestMod {
    public static final String MOD_ID = "testmod";
    public static final Logger LOGGER = LogUtils.getLogger();
    @Getter
    private static final IEventBus ModEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public TestMod() {
        Halberd.register(ModEventBus);
        QuestionMarkBlock.register(ModEventBus);

        HUD.Register();
    }
}
