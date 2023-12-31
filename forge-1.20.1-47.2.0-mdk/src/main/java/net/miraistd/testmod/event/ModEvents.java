package net.miraistd.testmod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.gui.core.HUD;
import net.miraistd.testmod.player.PlayerManager;
import net.miraistd.testmod.utils.Debug;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = TestMod.MOD_ID)
    public static class ForgeEvents{
        @SubscribeEvent
        public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            Player player = event.getEntity();
            PlayerManager.Connect(player.getName().getString(), player);

            new HUD(PlayerManager.GetExtendedPlayerByPlayer(player));

            //TODO change to packet
            String name = event.getEntity().getName().getString();
            Debug.LogAll (
                    new Player[]{event.getEntity()},
                    ">> " + name + " logged in"
            );
        }
        @SubscribeEvent
        public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
            Player player = event.getEntity();
            TestMod.LOGGER.info("WANNA LOG OUT >> {}", player.getName().getString());
            boolean successfullyDisconnected = PlayerManager.Disconnect(player.getName().getString());

            if(!successfullyDisconnected) {
                //TODO: Introduce emergency data salvation
                TestMod.LOGGER.info("LOGGED OUT WITH NO USE >> {}", player.getName().getString());
                return;
            }

            TestMod.LOGGER.info("LOGGED OUT >> {}", player.getName().getString());

            //TODO change to packet
            String name = event.getEntity().getName().getString();
            Debug.LogAll (
                    new Player[]{event.getEntity()},
                    ">> " + name + " logged out"
            );
        }
        @SubscribeEvent
        public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
            //Debug purposes
            PlayerManager.LogAllConnectedPlayers();

            //TODO change to packet
            Player client = PlayerManager.GetPlayerByName(Minecraft.getInstance().getUser().getName());
            if (client == null || !event.getEntity().getUUID().equals(client.getUUID()))
                return;

            //TODO change to packet
            PlayerManager.GetExtendedPlayerByPlayer(client).Reset(event.getEntity());
            //TODO change to packet
            Debug.Log (
                    client,
                    ">> " + client.getName().getString() + " respawned"
            );
        }
    }

    @Mod.EventBusSubscriber (modid = TestMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            TestMod.LOGGER.info("HELLO FROM CLIENT SETUP");
            TestMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
        @SubscribeEvent
        public static void addCreativeTab(BuildCreativeModeTabContentsEvent event){
        }
        @SubscribeEvent
        public static void commonSetup(final FMLCommonSetupEvent event) {
        }
    }
}
