package net.miraistd.testmod.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.miraistd.testmod.player.PlayerManager;

public class Debug {
    public static void Log(Player receiver, String text){
        if(receiver == null)
            return;

        receiver.sendSystemMessage(Component.literal(text));
    }

    public static void LogAll(Player[] excludedReceivers, String text){
        PlayerManager.SendMessageToAll(excludedReceivers, text);
    }
}
