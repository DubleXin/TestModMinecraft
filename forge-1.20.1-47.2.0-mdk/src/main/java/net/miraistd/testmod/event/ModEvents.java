package net.miraistd.testmod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.miraistd.testmod.Halberd.Halberd;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.ExtendedPlayer;
import net.miraistd.testmod.client.gui.core.HUD;
import net.miraistd.testmod.utils.Debug;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = TestMod.MOD_ID)
    public static class ForgeEvents{
        @SubscribeEvent
        public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            if(ExtendedPlayer.getPlayer() == null ||
                    !event.getEntity().getUUID().equals(ExtendedPlayer.getPlayer().getUUID()))
                ExtendedPlayer.Reset();
            else{
                String name = event.getEntity().getName().getString();
                Debug.Log(ExtendedPlayer.getPlayer(),">> " + name + " Logged In");
            }
        }
        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
            if(ExtendedPlayer.getPlayer() == null)
                ExtendedPlayer.Reset();
        }
        @SubscribeEvent
        public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
            if(!event.getEntity().getUUID().equals(ExtendedPlayer.getPlayer().getUUID()))
                return;

            ExtendedPlayer.Reset();
            Debug.Log (
                    ExtendedPlayer.getPlayer(),
                    ">> " + ExtendedPlayer.getPlayerName() + " Respawned"
            );
        }
    }

    @Mod.EventBusSubscriber (modid = TestMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerAboveAll("mod_hud", HUD.RENDER);
        }
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            TestMod.LOGGER.info("HELLO FROM CLIENT SETUP");
            TestMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
        @SubscribeEvent
        public static void addCreativeTab(BuildCreativeModeTabContentsEvent event){
            if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
                event.accept(Halberd.HalberdItem);
                TestMod.sendMessageToChat("INGREDIENTS");
            }
        }
        @SubscribeEvent
        public static void commonSetup(final FMLCommonSetupEvent event) {
        }
    }
}
