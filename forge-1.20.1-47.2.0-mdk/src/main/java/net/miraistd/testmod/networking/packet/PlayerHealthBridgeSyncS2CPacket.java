package net.miraistd.testmod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.networking.Messenger;
import net.miraistd.testmod.player.ClientExtendedPlayerData;

import java.util.function.Supplier;

public class PlayerHealthBridgeSyncS2CPacket {
    private final float health;

    public PlayerHealthBridgeSyncS2CPacket(float health){
        this.health = health;
    }
    public PlayerHealthBridgeSyncS2CPacket(FriendlyByteBuf buf){
        this.health = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeFloat(health);
    }

    /**
     Everything inside "context.enqueueWork" lambda is going to be invoked on the client side
     */
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {
            ClientExtendedPlayerData.setHealth(health);
            Messenger.sendToClient(new PlayerHealthBridgeSyncS2CPacket(health), player);
            TestMod.LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>packet sent, ANOTHER ONE: {}", health);
        });
        return true;
    }
}
