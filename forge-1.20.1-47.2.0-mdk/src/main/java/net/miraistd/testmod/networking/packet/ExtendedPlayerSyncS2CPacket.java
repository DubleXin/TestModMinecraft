package net.miraistd.testmod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.miraistd.testmod.networking.Messenger;
import net.miraistd.testmod.player.ClientExtendedPlayerData;
import net.miraistd.testmod.player.ExtendedPlayer;

import java.util.function.Supplier;

public class ExtendedPlayerSyncS2CPacket {
    private final ExtendedPlayer data;

    public ExtendedPlayerSyncS2CPacket(ExtendedPlayer data){
        this.data = data;
    }
    public ExtendedPlayerSyncS2CPacket(FriendlyByteBuf buf){
        this.data = ExtendedPlayer.fromBytes(buf);
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeFloat(data.getMana());

        var statusData = data.getStatusData();
        buf.writeInt(statusData.getJob().getValue());
        buf.writeInt(statusData.getLevel());
        buf.writeFloat(statusData.getExperience());
        buf.writeInt(statusData.getJobLevel());
        buf.writeFloat(statusData.getJobExperience());
        buf.writeInt(statusData.getSpareStatPoints());
        buf.writeInt(statusData.getSpareAbilityPoints());
    }

    /**
     Everything inside "context.enqueueWork" lambda is going to be invoked on the client side
     */
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {
            ClientExtendedPlayerData.setDATA(data);
            Messenger.sendToClient(new ExtendedPlayerSyncS2CPacket(ClientExtendedPlayerData.getDATA()), player);
        });
        return true;
    }
}
