package net.miraistd.testmod;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.miraistd.testmod.CreativeTabs.CreativeTabRegistrar;
import net.miraistd.testmod.Items.ItemRegisterUtil;
import net.miraistd.testmod.Items.Registrar;
import net.miraistd.testmod.utils.Debug;
import org.slf4j.Logger;



@Mod(TestMod.MOD_ID)
public class TestMod {
    public static final String MOD_ID = "testmod";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final IEventBus ModEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    public TestMod() {
        Registrar.RegisterAll();
        CreativeTabRegistrar.registerAll();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
