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
        ArrayList<String> letters = new ArrayList<>(Arrays.asList("J", "Q", "K", "A"));
        List<Integer> cardValues = new ArrayList<Integer>();
        JsonObject gameStateObject = gameState.getAsJsonObject();
        JsonArray players = gameStateObject.getAsJsonArray("players");
        JsonObject ourPlayer = players.get(gameStateObject.get("in_action").getAsInt()).getAsJsonObject();

        try {
        for (JsonElement card : ourPlayer.get("hole_cards").getAsJsonArray()){
                String value = card.getAsJsonObject().get("rank").getAsString();
            switch(value) {
                case "J" :
                    cardValues.add(11);
                    break; // optional

                case "Q" :
                    cardValues.add(12);
                    break; // optional

                case "K" :
                    cardValues.add(13);
                    break;
                case "A" :
                    cardValues.add(14);
                    break;

                // You can have any number of case statements.
                default : // Optional
                   cardValues.add(Integer.valueOf(value));
            }
//                if (letters.contains(value)) {
//                    cardValues.add(11+letters.indexOf(value));
//                    System.out.println("COMMENT - letter value: " + letters.indexOf(value));
//                }
//                else cardValues.add(Integer.valueOf(value));
        }
        System.out.println("COMMENT " + cardValues);
        }
        catch (Exception e){return 333;}

        int buyIn = gameStateObject.get("current_buy_in").getAsInt();
        int myBet = ourPlayer.get("bet").getAsInt();

        return buyIn;
    }

    public static void showdown(JsonElement game) {
    }
}
