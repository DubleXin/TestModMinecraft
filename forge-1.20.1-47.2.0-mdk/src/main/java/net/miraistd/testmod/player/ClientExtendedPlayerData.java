package net.miraistd.testmod.player;

import lombok.Getter;
import lombok.Setter;

public class ClientExtendedPlayerData {
    //mod data
    @Getter @Setter private static ExtendedPlayer data;

    //base data
    @Getter @Setter private static String name;
    @Getter @Setter private static float health;
    @Getter @Setter private static float hunger;
    @Getter @Setter private static int minecraftLevel;
    @Getter @Setter private static float minecraftExp;
}
