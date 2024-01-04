package net.miraistd.testmod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.miraistd.testmod.networking.Messenger;
import net.miraistd.testmod.player.ClientExtendedPlayerData;

import java.util.function.Supplier;

public class PlayerResourcesBridgeSyncS2CPacket {
    private final String name;
    private final float health;
    private final float hunger;
    private final int level;
    private final float experience;

    public PlayerResourcesBridgeSyncS2CPacket(String name, float health, float hunger, int level, float experience){
        this.name = name;
        this.health = health;
        this.hunger = hunger;
        this.level = level;
        this.experience = experience;
    }
    public PlayerResourcesBridgeSyncS2CPacket(FriendlyByteBuf buf){
        this.name = buf.readUtf();
        this.health = buf.readFloat();
        this.hunger = buf.readFloat();
        this.level = buf.readInt();
        this.experience = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeUtf(name);
        buf.writeFloat(health);
        buf.writeFloat(hunger);
        buf.writeInt(level);
        buf.writeFloat(experience);
    }

    /**
     Everything inside "context.enqueueWork" lambda is going to be invoked on the client side
     */
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {
            ClientExtendedPlayerData.setName(name);
            ClientExtendedPlayerData.setHealth(health);
            ClientExtendedPlayerData.setHunger(hunger);
            ClientExtendedPlayerData.setMinecraftLevel(level);
            ClientExtendedPlayerData.setMinecraftExp(experience);
            Messenger.sendToClient(new PlayerResourcesBridgeSyncS2CPacket(name, health, hunger, level, experience), player);
        });
        return true;
    }
}
