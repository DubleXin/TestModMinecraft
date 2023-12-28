package net.miraistd.testmod.client;

import net.minecraft.world.entity.player.Player;

public class StatusData {
    private static Player _player;
    private static LevelingData _levelingData;

    private static float _mana;

    public static LevelingData get_levelingData() {
        return _levelingData;
    }

    public static float get_mana() {
        return _mana;
    }

    public static float get_health() {
        return _player.getHealth();
    }
}

