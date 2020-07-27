package UnoGameGroup_9;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class is created to demonstrate the group of card, that each player will
 * hold during the game
 *
 * @author Group_9 (The Vipers)
 */
public class GroupOfCards {

    private ArrayList<UnoCard> deck;

    /**
     * This constructor is to create a new deck Holds four colors: red, blue ,
     * yellow and green
     */
    public GroupOfCards() {

        deck = new ArrayList<UnoCard>();
        COLORS[] colors
                = {
                    COLORS.RED,
                    COLORS.BLU,
                    COLORS.GRN,
                    COLORS.YLW
                };
        VALUE[] numbers
                = {
                    VALUE.ONE, VALUE.ONE, VALUE.TWO,
                    VALUE.TWO, VALUE.THREE, VALUE.THREE,
                    VALUE.FOUR, VALUE.FOUR, VALUE.FIVE,
                    VALUE.FIVE, VALUE.SIX, VALUE.SIX,
                    VALUE.SEVEN, VALUE.SEVEN, VALUE.EIGHT,
                    VALUE.EIGHT, VALUE.NINE, VALUE.NINE,
                    VALUE.ZERO
                };
        SPECIAL_NUM[] specialnumbers
                = {
                    SPECIAL_NUM.PLUS_TWO,
                    SPECIAL_NUM.PLUS_TWO,
                    SPECIAL_NUM.PLUS_FOUR,
                    SPECIAL_NUM.PLUS_FOUR
                };

        // adding a card to the deck
        for (COLORS clr : colors) {
            for (VALUE val : numbers) {
                deck.add(new UnoCard(val, clr));
            }
        }

        // adding a special card to the deck
        for (SPECIAL_NUM i : specialnumbers) {
            deck.add(new UnoCard(i));
        }

    }

    /**
     * This constructor is to increase the the deck, by reassigning the thrown
     * cards again
     *
     * @param c An object of the ArrayList that holds the cards
     */
    public GroupOfCards(ArrayList<UnoCard> c) {

        deck = c;
    }

    /**
     * This method id to check whether the deck size is empty or not
     *
     * @return A Boolean value determined by the size of the deck
     */
    public boolean isEmpty() {

        if (deck.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * To shuffle the deck of cards to create an equal environment
     */
    public void shuffle() {

        Collections.shuffle(deck);

    }

    /**
     * This method is to return The most top card
     *
     * @return The top of the cards from an inverted deck
     */
    public UnoCard getTopCard() {

        return deck.remove(deck.size() - 1);
    }

    /**
     * This method is to hand out one card Less
     *
     * @return the less one object of the deck
     */
    public UnoCard oneLess() {

        return deck.get(deck.size() - 1);
    }

    /**
     * this method overwrites upon the super class, represents and returns the
     * values of the class.
     *
     * @return the object that represents the deck
     */
    public String toString() {

        String deck = "";

        for (UnoCard t : this.deck) {

            deck = deck + t + " ";
        }

        return deck;

    }

}
