package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    static final String VERSION = "call everything";

    public static int betRequest(JsonElement gameState) {
        ArrayList<String> letters = new ArrayList<>(Arrays.asList("J", "D", "K", "A"));
        List<Integer> cardValues = new ArrayList<Integer>();
        JsonObject gameStateObject = gameState.getAsJsonObject();
        JsonArray players = gameStateObject.getAsJsonArray("players");
        JsonObject ourPlayer = players.get(gameStateObject.get("in_action").getAsInt()).getAsJsonObject();

        for (JsonElement card : ourPlayer.getAsJsonArray("hole_cards")){
            String value = String.valueOf(card.getAsJsonObject().get("rank"));
            if (letters.contains(value)) cardValues.add(11+letters.indexOf(value));
            else cardValues.add(Integer.valueOf(value));
        }

        int buyIn = gameStateObject.get("current_buy_in").getAsInt();
        int myBet = ourPlayer.get("bet").getAsInt();

        return 100;
    }

    public static void showdown(JsonElement game) {
    }
}
