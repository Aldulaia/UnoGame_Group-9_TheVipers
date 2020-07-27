package UnoGameGroup_9;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the Player, also methods and operations implemented.
 *
 * @author Group_9 (The Vipers)
 */
public class UnoPlayer extends Player {

    private ArrayList<UnoCard> handOfCards;
    private String playerName;

    /**
     * It constructs and creates an array that holds the player cards.
     *
     * @param name The name of the player
     */
    public UnoPlayer(String name) {

        super();
        handOfCards = new ArrayList<UnoCard>();
        this.playerName = name;

    }

    /**
     * @return The size of the deck on hand
     */
    public int numOfCards() {

        return handOfCards.size();
    }

    /**
     * @return the array list that holds the cards on hand
     */
    public ArrayList<UnoCard> listOfCards() {

        return handOfCards;
    }

    /**
     * This method is to add the object to the hand of the player
     *
     * @param c an object that represents the UNO card
     */
    public void withDrawCards(UnoCard c) {

        handOfCards.add(c);

    }

    /**
     * This method is to remove and displace the used card out of the player's
     * hand
     *
     * @param c The number that represents the index
     * @return A removed card to be displaced
     */

    public UnoCard pushCard(int c) {

        return handOfCards.remove(c);
    }

    /**
     * This method is called when the player own last card on his deck
     */
    public void claimUno() {

        Scanner input = new Scanner(System.in);

        if (handOfCards.size() == 1) {

            System.out.println("Uno");
            System.out.println("Press Enter...");
            input.nextLine();
        }
    }

    /**
     * This method overwritten upon the super class to initiate the game
     */
    @Override
    public void play() {

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

    /**
     * To hide the cards of the player, called by another method in the UNO
     * class
     */

    public void coverCards() {

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

    /**
     * Checks if the has already won or not
     *
     * @return A Boolean value
     */
    public boolean checkWinner() {
        if (handOfCards.size() == 0) {
            return true;
        }
        return false;
    }

    public String toString() {

        return this.playerName;
    }

}
