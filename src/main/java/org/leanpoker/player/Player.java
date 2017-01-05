package org.leanpoker.player;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement gameState) {
        ArrayList<String> letters = new ArrayList<>(Arrays.asList("J", "D", "K", "A"));
        List<Integer> cardValues = new ArrayList<Integer>();
        for(JsonElement player: gameState.getAsJsonObject().getAsJsonArray("players")){
            if (player.getAsJsonObject().get("id") == gameState.getAsJsonObject().get("in_action")){
                for (JsonElement card : player.getAsJsonObject().getAsJsonArray("hole_cards")){
                    String value = String.valueOf(card.getAsJsonObject().get("rank"));
                    if (letters.contains(value)) cardValues.add(11+letters.indexOf(value));
                    else cardValues.add(Integer.valueOf(value));
                }
            }

        }

        return 1;
    }

    public static void showdown(JsonElement game) {
    }
}
