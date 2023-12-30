package net.miraistd.testmod;

import com.mojang.logging.LogUtils;
import net.minecraft.network.chat.Component;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.miraistd.testmod.Halberd.Halberd;
import net.miraistd.testmod.QuestionMarkBlock.QuestionMarkBlock;
import net.miraistd.testmod.client.ExtendedPlayer;
import net.miraistd.testmod.client.gui.core.HUD;
import org.slf4j.Logger;


@Mod(TestMod.MOD_ID)
public class TestMod {
    public static final String MOD_ID = "testmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void sendMessageToChat(String message) {
        Component message_component = Component.translatable(message);
        ExtendedPlayer.getPlayer().displayClientMessage(message_component, true);
    }

    public TestMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Halberd.register(modEventBus);
        QuestionMarkBlock.register(modEventBus);

        HUD.Register();
        ExtendedPlayer.Validate();
    }
}
