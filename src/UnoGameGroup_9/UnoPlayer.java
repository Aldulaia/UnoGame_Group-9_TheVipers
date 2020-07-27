package UnoGameGroup_9;

import java.util.ArrayList;
import java.util.Scanner;

public class UnoPlayer extends Player {

    private ArrayList<UnoCard> handOfCards;
    private String playerName; //player name

    public UnoPlayer(String name) {
        /*
		 * creates a array list that will store player cards
		 * assigns name to the player
         */

        //player object are created in uno class
        super();
        handOfCards = new ArrayList<UnoCard>();
        this.playerName = name;

    }

    public int numOfCards() {
        /*
		 * returns number of cards player has in hand
         */
        return handOfCards.size();
    }

    public ArrayList<UnoCard> listOfCards() {
        /*
		 * returns all the cards player has in hand as an ArrayList
		 * This is used mainly to check if player has any valid cards to play.(Check the Uno class)
         */

        return handOfCards;
    }

    public void withDrawCards(UnoCard c) {
        /*
		 * 
         */
        handOfCards.add(c);

    }

    public UnoCard pushCard(int c) {
        /*
		 * player throws a card from hand which is at position 'c'. c is a integer value and is passed as an argument.
         */

        return handOfCards.remove(c);
    }

    public void claimUno() {
        /*
		 * player says uno if they only have 1 card in the hand.
         */
        Scanner input = new Scanner(System.in);

        if (handOfCards.size() == 1) {

            System.out.println("Uno");
            System.out.println("Press Enter...");
            input.nextLine();
        }
    }

    @Override
    public void play() {
        /*
		 * this is graphical representation of a card
		 * just to make cards look more look like cards
		 * used in showboard() method in Uno class
         */

        String[] stringCards = {" ----- ", "|     |", "|     |", " ----- "};
        String p = "";

        for (int l = 0; l < stringCards.length; l++) {

            for (int n = 0; n < handOfCards.size(); n++) {

                if (!handOfCards.get(n).isSpecialCard()) {
                    if (l == 1) {

                        p = p + "| " + handOfCards.get(n).getColor() + " |" + " ";

                    } else if (l == 2) {

                        p = p + "|  " + handOfCards.get(n).getValue() + "  |" + " ";
                    } else {
                        p = p + stringCards[l] + " ";
                    }

                } else if (handOfCards.get(n).isSpecialCard()) {

                    if (l == 1) {

                        p = p + "| " + "+" + handOfCards.get(n).getValue() + "  |" + " ";

                    } else {
                        p = p + stringCards[l] + " ";
                    }

                }

            }

            p += "\n";

        }

        System.out.print(p);
    }

    public void coverCards() {

        /*
		 * to hide player cards
		 * used in showboard() method in Uno class
         */
        String[] card = {" ----- ", "|     |", "|     |", " ----- "};
        String b = "";

        for (int i = 0; i < card.length; i++) {

            for (int j = 0; j < handOfCards.size(); j++) {

                b = b + card[i] + " ";

            }
            b += "\n";
        }

        System.out.print(b);
    }

    public boolean checkWinner() {
        /*
		 * checks if player has won or not
         */
        if (handOfCards.size() == 0) {
            return true;
        }
        return false;
    }

    public String toString() {
        /*
		 * text representation of player
         */
        return this.playerName;
    }

}
