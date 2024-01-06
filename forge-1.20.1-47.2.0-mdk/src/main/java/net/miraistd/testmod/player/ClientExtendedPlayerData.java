package net.miraistd.testmod.player;

import lombok.Getter;
import lombok.Setter;

public class ClientExtendedPlayerData {
    //mod data
    @Getter private static ExtendedPlayer DATA;

    public static void setDATA(ExtendedPlayer data){
        if(DATA == null)
            DATA = data;

        else {
            DATA.setMana(data.getMana());
        }
    }

    //base data
    @Getter @Setter private static String name;
    @Getter @Setter private static float health;
    @Getter @Setter private static float hunger;
    @Getter @Setter private static int minecraftLevel;
    @Getter @Setter private static float minecraftExp;
}
