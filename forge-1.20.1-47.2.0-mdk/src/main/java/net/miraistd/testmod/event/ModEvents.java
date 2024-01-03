package net.miraistd.testmod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.miraistd.testmod.Items.ItemRegisterUtil;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.gui.core.HUD;
import net.miraistd.testmod.networking.Messenger;
import net.miraistd.testmod.networking.packet.ExtendedPlayerSyncS2CPacket;
import net.miraistd.testmod.networking.packet.PlayerHealthBridgeSyncS2CPacket;
import net.miraistd.testmod.player.ExtendedPlayer;
import net.miraistd.testmod.player.ExtendedPlayerProvider;
import net.miraistd.testmod.utils.Debug;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = TestMod.MOD_ID)
    public static class ForgeEvents{
        //TODO:make actual interface for subscriptions on player events
        @SubscribeEvent
        public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getItemStack().getItem() == ItemRegisterUtil.getItemsMap().get("debug_item").getFirst().get())
            Debug.LogAll(new Player[0], "test");
        }
        @SubscribeEvent
        public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            Player client = event.getEntity();

            //TODO change to packet
            String name = client.getName().getString();
            Debug.LogAll (
                    new Player[]{client},
                    ">> " + name + " logged in"
            );
        }
        @SubscribeEvent
        public static void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
            Player player = event.getEntity();
            TestMod.LOGGER.info("WANNA LOG OUT >> {}", player.getName().getString());

            //TODO change to packet
            String name = event.getEntity().getName().getString();
            Debug.LogAll (
                    new Player[]{event.getEntity()},
                    ">> " + name + " logged out"
            );
        }
        @SubscribeEvent
        public static void onPlayerJoinWorld(EntityJoinLevelEvent event){
            if(!event.getLevel().isClientSide()) {
                if (event.getEntity() instanceof ServerPlayer player)
                    player.getCapability(ExtendedPlayerProvider.EXTENDED_PLAYER).ifPresent (
                            data -> Messenger.sendToClient(new ExtendedPlayerSyncS2CPacket(data), player)
                    );
            }
        }
        @SubscribeEvent
        public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
            /*
            TODO change to packet

            ExtendedPlayer.Reset(event.getEntity());

            TODO change to packet

             Debug.Log (
            client,
            ">> " + client.getName().getString() + " respawned"
             );
            */
        }
        @SubscribeEvent
        public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
            if(event.getObject() instanceof Player)
                if(!event.getObject().getCapability(ExtendedPlayerProvider.EXTENDED_PLAYER).isPresent())
                    event.addCapability (
                            new ResourceLocation(TestMod.MOD_ID,"properties"),
                            new ExtendedPlayerProvider()
                    );
        }
        @SubscribeEvent
        public static void onPlayerCloned(PlayerEvent.Clone event){
            if(event.isWasDeath()){
                var extendedPlayerCapability = event.getOriginal()
                        .getCapability(ExtendedPlayerProvider.EXTENDED_PLAYER);

                extendedPlayerCapability.ifPresent(oldStore ->
                    extendedPlayerCapability .ifPresent(newStore ->
                            newStore.copyFrom(oldStore)));
            }
        }
        @SubscribeEvent
        public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
            event.register(ExtendedPlayer.class);
        }
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event){
            if(event.side == LogicalSide.SERVER){
                event.player.getCapability(ExtendedPlayerProvider.EXTENDED_PLAYER).ifPresent(data -> {
                    data.setMana(event.player.getRandom().nextFloat());

                    Messenger.sendToClient(new ExtendedPlayerSyncS2CPacket(data), (ServerPlayer) event.player);
                    Messenger.sendToClient(new PlayerHealthBridgeSyncS2CPacket(event.player.getHealth()), (ServerPlayer) event.player);
                });
            }

        }
    }

    @Mod.EventBusSubscriber (modid = TestMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            TestMod.LOGGER.info("HELLO FROM CLIENT SETUP");
            TestMod.LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
        @SubscribeEvent
        public static void RegisterGuiOverlays(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("mod_hud", HUD.RENDER);
        }
        @SubscribeEvent
        public static void addCreativeTab(BuildCreativeModeTabContentsEvent event){
        }
        @SubscribeEvent
        public static void commonSetup(final FMLCommonSetupEvent event) {
            Messenger.register();
        }
    }
}
