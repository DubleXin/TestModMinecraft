package net.miraistd.testmod.client.gui;

import lombok.Getter;
import net.minecraft.world.entity.player.Player;
import net.miraistd.testmod.client.LevelingData;

@Getter
public class StatusData {
    private static Player _player;

    private LevelingData LevelingData;
    private float Mana;

    public static float getHealth() {
        if(_player == null)
            return 0;
        return _player.getHealth();
    }

    public StatusData(Player player) {
        _player = player;
    }
}

