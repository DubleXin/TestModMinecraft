package net.miraistd.testmod.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.networking.packet.ExperienceGainS2CPacket;
import net.miraistd.testmod.networking.packet.ExtendedPlayerSyncS2CPacket;
import net.miraistd.testmod.networking.packet.PlayerResourcesBridgeSyncS2CPacket;

public class Messenger {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id(){
        return packetId++;
    }

    public static void register(){
        INSTANCE = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(TestMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE.messageBuilder(ExtendedPlayerSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ExtendedPlayerSyncS2CPacket::new)
                .encoder(ExtendedPlayerSyncS2CPacket::toBytes)
                .consumerMainThread(ExtendedPlayerSyncS2CPacket::handle)
                .add();

        INSTANCE.messageBuilder(PlayerResourcesBridgeSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(PlayerResourcesBridgeSyncS2CPacket::new)
                .encoder(PlayerResourcesBridgeSyncS2CPacket::toBytes)
                .consumerMainThread(PlayerResourcesBridgeSyncS2CPacket::handle)
                .add();

        INSTANCE.messageBuilder(ExperienceGainS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ExperienceGainS2CPacket::new)
                .encoder(ExperienceGainS2CPacket::toBytes)
                .consumerMainThread(ExperienceGainS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message){
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToClient(MSG message, ServerPlayer client){
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> client), message);
    }
}
