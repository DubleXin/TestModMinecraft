package net.miraistd.testmod.client;

import lombok.Getter;
import net.minecraft.world.entity.player.Player;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.gui.StatusData;
import net.miraistd.testmod.player.PlayerManager;

@Getter
public class ExtendedPlayer {
    private final String Name;
    private Player Player;
    private StatusData StatusData;

    public ExtendedPlayer(Player player){
        Player = player;
        Name = player.getName().getString();
        StatusData = new StatusData(Player);
        TestMod.LOGGER.info("STATUS DATA GENERATED FOR : {} >> AND IT'S HEALTH IS : {}", Name,
                net.miraistd.testmod.client.gui.StatusData.getHealth());
    }

    public void Validate(){
        if(Player == null)
            Player = PlayerManager.GetPlayerByName(Name);
        if(StatusData == null && Player != null)
            StatusData = new StatusData(Player);
    }

    public void Reset(Player player){
        Player = player;
        StatusData = new StatusData(Player);
    }
}
