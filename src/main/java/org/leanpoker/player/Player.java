package org.leanpoker.player;

import com.google.gson.JsonElement;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement gameState) {
        gameState.getAsJsonObject().getAsJsonArray("players");

        return 1;
    }

    public static void showdown(JsonElement game) {
    }
}
