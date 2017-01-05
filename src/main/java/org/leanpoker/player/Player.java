package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement gameState) {
        JsonObject gameStateObject = gameState.getAsJsonObject();
        JsonArray players = gameStateObject.getAsJsonArray("players");
        JsonObject player = players.get(gameStateObject.get("in_action").getAsInt()).getAsJsonObject();

                for (JsonElement card : player.getAsJsonArray("hole_cards")){
                    card.getAsJsonObject().get("rank");
                }

        return 1;
    }

    public static void showdown(JsonElement game) {
    }
}
