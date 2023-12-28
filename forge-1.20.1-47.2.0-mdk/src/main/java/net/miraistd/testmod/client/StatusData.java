package net.miraistd.testmod.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class StatusData {
    private final static Player _player = Minecraft.getInstance().player;
    private static LevelingData _levelingData;

    private static float _mana;

    public static LevelingData get_levelingData() {
        return _levelingData;
    }

    public static float get_mana() {
        return _mana;
    }

    public static float get_health() {
        if(_player == null)
            return 0;
        return _player.getHealth();
    }

    public static Player get_owner() {
        return _player;
    }
}

