package org.leanpoker.player;

import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement gameState) {

        for(JsonElement player: gameState.getAsJsonObject().getAsJsonArray("players")){
            if (player.getAsJsonObject().get("id") == gameState.getAsJsonObject().get("in_action")){
                for (JsonElement card : player.getAsJsonObject().getAsJsonArray("hole_cards")){
                    card.getAsJsonObject().get("rank");
                }
            }

        }

        return 1;
    }

    public static void showdown(JsonElement game) {
    }
}
