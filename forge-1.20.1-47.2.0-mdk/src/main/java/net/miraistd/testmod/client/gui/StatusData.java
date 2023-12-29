package net.miraistd.testmod.client.gui;

import lombok.*;
import net.minecraft.client.*;
import net.minecraft.world.entity.player.*;

public class StatusData {
    private final static Player _player = Minecraft.getInstance().player;

    @Getter private static LevelingData _levelingData;
    @Getter private static float _mana;

    public static float get_health() {
        if(_player == null)
            return 0;
        return _player.getHealth();
    }

    public static Player get_owner() {
        return _player;
    }
}

