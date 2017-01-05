package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    static final String VERSION = "call everything";

    public static int betRequest(JsonElement gameState) {
        int bet = 0;
        ArrayList<String> letters = new ArrayList<>(Arrays.asList("J", "Q", "K", "A"));

        JsonObject gameStateObject = gameState.getAsJsonObject();

        JsonArray players = gameStateObject.getAsJsonArray("players");
        JsonObject ourPlayer = players.get(gameStateObject.get("in_action").getAsInt()).getAsJsonObject();
        int stack = ourPlayer.get("stack").getAsInt();
        ArrayList<Integer> myCards = getRank(ourPlayer.get("hole_cards").getAsJsonArray());

        // The cards on the table,might be empty.
        ArrayList<Integer> communityCards = getRank(gameStateObject.getAsJsonArray("community_cards"));

        // Put your card observing logic here ------------------------------------
        bet = highCards(bet, myCards);
        //------------------------------------------------------------------------


        int buyIn = gameStateObject.get("current_buy_in").getAsInt();
        int myBet = ourPlayer.get("bet").getAsInt();

        return bet;
    }

    public static int highCards(int bet, ArrayList<Integer> myCards) {
        if (myCards.get(0) > 9 && myCards.get(1) > 9) {
            bet=1000;
        }
        else if (myCards.get(0) > 10 || myCards.get(1) > 10) {
//            if (stack<200) bet=stack;
            bet=200;
        }
        return bet;
    }

    public static ArrayList<Integer> getRank(JsonArray cardsArray) {
        ArrayList<Integer> cardValues = new ArrayList<>();
        for (JsonElement card : cardsArray){
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

        default : // Optional
        cardValues.add(Integer.valueOf(value));
        }

    }
    return cardValues;
    }

    public static void showdown(JsonElement game) {
    }
}
