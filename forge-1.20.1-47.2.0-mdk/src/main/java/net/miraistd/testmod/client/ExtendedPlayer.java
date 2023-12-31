package net.miraistd.testmod.client;

import lombok.Getter;
import net.minecraft.world.entity.player.Player;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.gui.StatusData;
import net.miraistd.testmod.player.PlayerManager;

public class ExtendedPlayer {
    @Getter
    private final String Name;
    @Getter
    private StatusData StatusData;

    private Player _player;
    public Player getPlayer(){
        return _player;
    }
    public Player getPlayerSafe(){
        if(_player == null)
            _player = PlayerManager.GetPlayerByName(Name);

        return _player;
    }

    public ExtendedPlayer(Player player){
        _player = player;
        Name = player.getName().getString();
        StatusData = new StatusData(_player);
        TestMod.LOGGER.info("STATUS DATA GENERATED FOR : {} >> AND IT'S HEALTH IS : {}", Name,
                net.miraistd.testmod.client.gui.StatusData.getHealth());
    }

    public void Validate(){
        if(_player == null)
            _player = PlayerManager.GetPlayerByName(Name);
        if(StatusData == null && _player != null)
            StatusData = new StatusData(_player);
    }

    public void Reset(Player player){
        _player = player;
        StatusData = new StatusData(_player);
    }
}
