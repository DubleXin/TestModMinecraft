package net.miraistd.testmod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.miraistd.testmod.networking.Messenger;
import net.miraistd.testmod.player.ClientExtendedPlayerData;

import java.util.function.Supplier;

public class ExperienceGainS2CPacket {
    private final float experience;
    private final float jobExperience;

    public ExperienceGainS2CPacket(float experience, float jobExperience){
        this.experience = experience;
        this.jobExperience = jobExperience;
    }
    public ExperienceGainS2CPacket(FriendlyByteBuf buf){
        this.experience = buf.readFloat();
        this.jobExperience = buf.readFloat();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeFloat(experience);
        buf.writeFloat(jobExperience);
    }

    /**
     Everything inside "context.enqueueWork" lambda is going to be invoked on the client side
     */
    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        ServerPlayer player = context.getSender();
        context.enqueueWork(() -> {
            var statusData = ClientExtendedPlayerData.getDATA().getStatusData();

            statusData.addExperience(experience);
            statusData.addJobExperience(jobExperience);

            Messenger.sendToClient(new ExperienceGainS2CPacket(experience, jobExperience), player);
        });
        return true;
    }
}
