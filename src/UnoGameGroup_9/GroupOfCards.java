package UnoGameGroup_9;

import java.util.ArrayList;
import java.util.Collections;

public class GroupOfCards {

    private ArrayList<UnoCard> deck;

    public GroupOfCards() {

        /*
			 * The constructor creates a new deck
			 * There are 4 colors: red, blue, green, yellow
			 * Each suit has two of the same card except 0 (it only appears once in each suit).
			 * For example: green suit has two 1s but only 1 zero
         */
        deck = new ArrayList<UnoCard>();
        COLORS[] colors = {COLORS.RED, COLORS.BLU, COLORS.GRN, COLORS.YLW};
        VALUE[] numbers
                = {
                    VALUE.ONE, VALUE.ONE, VALUE.TWO,
                    VALUE.TWO, VALUE.THREE, VALUE.THREE,
                    VALUE.FOUR, VALUE.FOUR, VALUE.FIVE,
                    VALUE.FIVE, VALUE.SIX, VALUE.SIX,
                    VALUE.SEVEN, VALUE.SEVEN, VALUE.EIGHT,
                    VALUE.EIGHT, VALUE.NINE, VALUE.NINE,
                    VALUE.ZERO
                }; //regular cards
        SPECIAL_NUM[] specialnumbers = {SPECIAL_NUM.PLUS_TWO, SPECIAL_NUM.PLUS_TWO, SPECIAL_NUM.PLUS_FOUR, SPECIAL_NUM.PLUS_FOUR}; //special cards +2, +2, +4 and +4

        for (COLORS clr : colors) { //adding regular cards to the deck
            for (VALUE val : numbers) {
                deck.add(new UnoCard(val, clr)); //adding new cards to our deck
            }
        }

        for (SPECIAL_NUM i : specialnumbers) { //adding special cards to the deck
            deck.add(new UnoCard(i));
        }

    }

    public GroupOfCards(ArrayList<UnoCard> c) { //overloaded constructor
        /*
			 * incase the current deck becomes empty, all the thrown cards are collected and it becomes the new deck;
			 * 
         */
        deck = c;
    }

    public boolean isEmpty() { //is deck empty?
        /*
			 * Checks the size of the deck. If it is greater than 0 then returns false. If not, returns true 
         */

        if (deck.size() > 0) {
            return false;
        }
        return true;
    }

    public void shuffle() {

        /*
			 *  Shuffles the deck
         */
        Collections.shuffle(deck);

    }

    public UnoCard getTopCard() {
        /*
			 * gets the topmost card from a inverted deck
         */
        return deck.remove(deck.size() - 1);
    }

    public UnoCard peeeeeeks() {

        return deck.get(deck.size() - 1);
    }

    public String toString() {

        String deck = "";

        for (UnoCard t : this.deck) {

            deck = deck + t + " ";
        }

        return deck;

    }

}
