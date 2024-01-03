package net.miraistd.testmod.player;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.miraistd.testmod.client.gui.Jobs;
import net.miraistd.testmod.client.gui.StatusData;
@Getter
public class ExtendedPlayer {
    @Setter
    float mana;

    private StatusData statusData;

    public ExtendedPlayer(){
        statusData = new StatusData();
    }

    public ExtendedPlayer(float mana, StatusData statusData){
        this.mana = mana;
        this.statusData = statusData;
    }

    public void copyFrom(ExtendedPlayer source){
        statusData = source.statusData;
    }

    public void SaveNBTData(CompoundTag nbt){
        nbt.putFloat("mana", mana);

        nbt.putInt("job_id", statusData.getJob().getValue());
        nbt.putInt("status_level", statusData.getLevel());
        nbt.putFloat("status_exp", statusData.getExperience());
        nbt.putInt("job_level", statusData.getJobLevel());
        nbt.putFloat("job_exp", statusData.getJobExperience());
        nbt.putInt("stat_points", statusData.getSpareStatPoints());
        nbt.putInt("ability_points",statusData.getSpareAbilityPoints());
    }

    public void LoadNBTData(CompoundTag nbt){
        mana = nbt.getFloat("mana");

        int jobId = nbt.getInt("job_id");
        int level = nbt.getInt("status_level");
        float experience = nbt.getFloat("status_exp");
        int jobLevel = nbt.getInt("job_level");
        float jobExperience = nbt.getFloat("job_exp");
        int spareStatPoints = nbt.getInt("stat_points");
        int spareAbilityPoints = nbt.getInt("ability_points");

        statusData = new StatusData(Jobs.values()[jobId], level, experience, jobLevel,
                jobExperience, spareStatPoints, spareAbilityPoints);
    }

    public static ExtendedPlayer fromBytes(FriendlyByteBuf buf){
        float mana = buf.readFloat();

        int jobId = buf.readInt();
        int level = buf.readInt();
        float experience = buf.readFloat();
        int jobLevel = buf.readInt();
        float jobExperience = buf.readFloat();
        int spareStatPoints = buf.readInt();
        int spareAbilityPoints = buf.readInt();

        var statusData = new StatusData(Jobs.values()[jobId], level, experience, jobLevel,
                jobExperience, spareStatPoints, spareAbilityPoints);
        return new ExtendedPlayer(mana, statusData);
    }
}
