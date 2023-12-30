package net.miraistd.testmod.client;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.miraistd.testmod.client.gui.StatusData;

public class ExtendedPlayer {
    @Getter private static Player Player;
    @Getter private static StatusData StatusData;

    public static void Validate(){
        if(Player == null)
            Player = Minecraft.getInstance().player;
        if(StatusData == null && Player != null)
            StatusData = new StatusData(Player);
    }

    public static void Reset(){
        Player = Minecraft.getInstance().player;
        StatusData = new StatusData(Player);
    }

    public static String getPlayerName(){
        return Player.getName().getString();
    }
}
