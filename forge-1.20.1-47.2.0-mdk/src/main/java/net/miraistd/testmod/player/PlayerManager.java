package net.miraistd.testmod.player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mirai.pair;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.miraistd.testmod.TestMod;
import net.miraistd.testmod.client.ExtendedPlayer;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayerManager {
    private static final Hashtable<String, pair<Player, ExtendedPlayer>> _players = new Hashtable<>();

    public static void Connect(String name, Player player){
        var extendedPlayer = new ExtendedPlayer(player);
        _players.put(name, new pair<>(player, extendedPlayer));
    }
    public static boolean Disconnect(String name){
        if(!_players.contains(name))
            return false;

        _players.remove(name);
        return true;
    }

    public static Player GetPlayerByName(String name) {
        return _players.get(name).getFirst();
    }
    public static ExtendedPlayer GetExtendedPlayerByName(String name) {
        return  _players.get(name).getSecond();
    }
    public static ExtendedPlayer GetExtendedPlayerByPlayer(@NotNull Player player) {
        String name = player.getName().getString();
        return GetExtendedPlayerByName(name);
    }

    public static void LoadExtendedPlayerInfo(){
        ExecutorService executor = Executors.newSingleThreadExecutor();

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            Path filePath = Paths.get("extendedPlayersProfiles.json");
            Gson gson = new Gson();

            try {
                String jsonContent = new String(Files.readAllBytes(filePath));
                JsonObject jsonObject = JsonParser.parseString(jsonContent).getAsJsonObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executor);

        future.thenRun(executor::shutdown);
    }
    public static void SaveExtendedPlayerInfo(){
        ExecutorService executor = Executors.newSingleThreadExecutor();

        JsonObject modifiedObject = new JsonObject();

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            Path filePath = Paths.get("extendedPlayersProfiles.json");
            Gson gson = new Gson();

            try {
                String jsonString = gson.toJson(modifiedObject);
                Files.write(filePath, jsonString.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, executor);

        future.thenRun(executor::shutdown);
    }

    public static void SendMessageToAll(Player[] excludedReceivers, String text){
        for (var pair : _players.values()) {
            var receiver = pair.getFirst();
            if(receiver == null)
                continue;

            boolean isExcluded = false;
            for (Player excludedReceiver : excludedReceivers) {
                if (excludedReceiver.getUUID().equals(receiver.getUUID())) {
                    isExcluded = true;
                    break;
                }
            }

            if(isExcluded)
                continue;

            receiver.sendSystemMessage(Component.literal(text));
        }
    }
    public static void LogAllConnectedPlayers(){
        int i =1;
        for (var player : _players.values()) {
            TestMod.LOGGER.info("CONNECTED PLAYER {} >> {}", i,
                    player.getFirst().getName().getString());
        i++;
        }
    }
}
