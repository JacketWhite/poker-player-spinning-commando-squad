package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    static final String VERSION = "We have to go deeper!!";

    public static int betRequest(JsonElement gameState) {
        int bet = 0;
        ArrayList<String> letters = new ArrayList<>(Arrays.asList("J", "Q", "K", "A"));



        JsonObject gameStateObject = gameState.getAsJsonObject();

        JsonArray players = gameStateObject.getAsJsonArray("players");
        JsonObject ourPlayer = players.get(gameStateObject.get("in_action").getAsInt()).getAsJsonObject();
        int stack = ourPlayer.get("stack").getAsInt();
        ArrayList<Integer> myCards = getRank(ourPlayer.get("hole_cards").getAsJsonArray());

        int bigBlind = gameStateObject.get("small_blind").getAsInt() * 2;
        int buyIn = gameStateObject.get("current_buy_in").getAsInt();
        int pot = gameStateObject.get("pot").getAsInt();
        int myBet = ourPlayer.get("bet").getAsInt();
        int minimumRaise = gameStateObject.get("minimum_raise").getAsInt();
        int myPot = ourPlayer.get("stack").getAsInt();
        int previousRound = 0;
        int round = gameStateObject.get("round").getAsInt();

        // The cards on the table,might be empty.
        ArrayList<Integer> communityCards = getRank(gameStateObject.getAsJsonArray("community_cards"));

        // Put your card observing logic here ------------------------------------
        boolean bajvan = true;
        for (JsonElement player : players) {
            if (player.getAsJsonObject().get("name").getAsString().equals("Glorious Ape") && player.getAsJsonObject().get("bet").getAsInt() <= buyIn) {
               bajvan = false;
            }
            else if ((player.getAsJsonObject().get("name").getAsString().equals("Glorious Ape") && player.getAsJsonObject().get("bet").getAsInt() <= buyIn)){
                bajvan = false;
            }
            else {bajvan = true;}

        }

        if (highPairs(myCards)) bet = raise(myBet, buyIn, minimumRaise, myPot);
        else if ((buyIn < 600 - bigBlind) && bajvan) bet = raise(myBet, buyIn, minimumRaise, minimumRaise);




//        else if (getIntoFlop(myCards, pot, buyIn, communityCards)) call(myBet, buyIn);
//        else if (havePairOnTable(myCards, communityCards)) bet = raise(myBet, buyIn, minimumRaise, 100);
//        else if (twoHighCards(myCards, stack)) bet = raise(myBet, buyIn, minimumRaise, 200);
//        else if (oneHighCards(myCards, stack) && ) ;


        //------------------------------------------------------------------------

        return bet;
    }

    public static boolean havePairOnTable(ArrayList<Integer> myCards, ArrayList<Integer> communityCards) {
        for (Integer card: myCards) {
            if (card > 10) {
                if (communityCards.contains(card)) return true;
            }
        }
        return false;
    }

    public static boolean getIntoFlop(ArrayList<Integer> myCards, int pot, int buyIn, ArrayList<Integer> table) {
        if (oneHighCards(myCards) && table.size()==0) {
            if (pot * 7 > buyIn ) return true;
        }
        return false;
    }

    public static boolean twoHighCards(ArrayList<Integer> myCards, int stack) {
        if (myCards.get(0) > 9 && myCards.get(1) > 9) {
            return true;
        }
        return false;
        }
    public static boolean oneHighCards(ArrayList<Integer> myCards) {
        if (myCards.get(0) > 10 || myCards.get(1) > 10) {
            return true;
        }
        return false;
    }

    public static boolean tierOneCards(ArrayList<Integer> myCards, int stack) {
            // high tier pairs
        if (myCards.get(0) > 12 && myCards.get(0) == myCards.get(1)) {
            return true;

            // two high tier cards ( EG. AK, KA ...)
        } else if (myCards.get(0) > 12 || myCards.get(1) > 12) {
            return true;
        }
        return false;
    }

    public static boolean tierTwoCards(ArrayList<Integer> myCards, int stack) {
        if (myCards.get(0) > 10 && myCards.get(0) == myCards.get(1)) {
            return true;
        } else if (myCards.get(0) > 10 || myCards.get(1) > 10) {
            return true;
        }
        return false;
    }


    public static boolean highPairs(ArrayList<Integer> myCards) {
        if (myCards.get(0) > 11 && myCards.get(1) > 11 && myCards.get(0).equals(myCards.get(1))) return true;
        return false;
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
    public static int raise(int myBet, int buyIn, int minimumRaise, int bet){
        int number;
        number = (minimumRaise < bet)? buyIn - myBet + bet : buyIn - myBet + minimumRaise;
        return number;
    }
    public static int call(int myBet, int buyIn){
        return buyIn - myBet;
    }
    public static void showdown(JsonElement game) {
    }
}
