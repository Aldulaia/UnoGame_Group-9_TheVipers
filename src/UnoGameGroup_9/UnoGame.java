package UnoGameGroup_9;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents the game and it's functions
 *
 * @author Group_9 (The Vipers)
 */
public class UnoGame extends Game {

    // declared variable
    private UnoCard currentCard;
    private Deck deckOfCards;
    private ArrayList<UnoCard> pileOfCards;
    private int punishment;
    private Scanner input;
    private UnoPlayer player1, player2;
    private int pickCard;

    /**
     * It constructs the game and prepare the game to be initiated
     */
    public UnoGame() {

        super("Uno");
        deckOfCards = new Deck();
        deckOfCards.shuffle();
        punishment = 0;
        currentCard = startingCard();
        pileOfCards = new ArrayList<UnoCard>();
        pileOfCards.add(currentCard);
        input = new Scanner(System.in);
        player1 = new UnoPlayer("Player 1");
        player2 = new UnoPlayer("Player 2");
        handOutCards();

    }

    /**
     * This method is overwritten upon the super class to simulate the turn
     * between player one and player two
     */
    @Override
    public void play() {

        int whoTurn = 0;
        while (!declareWinner(player1, player2)) {
            if (whoTurn % 2 == 0) {
                playUnoGame(player1);
            } else {
                playUnoGame(player2);
            }
            whoTurn++;
        }

    }

    /**
     * This method is created to distribute the cards
     */
    private void handOutCards() {

        for (int i = 0; i < 10; i++) {

            if (i % 2 == 0) {
                player1.withDrawCards(deckOfCards.getTopCard());
            } else {
                player2.withDrawCards(deckOfCards.getTopCard());
            }

        }
    }

    /**
     * This method contains the entire process of the game
     *
     * @param p An UNO player as an argument
     */
    public void playUnoGame(UnoPlayer p) {

        lineDecoration();
        System.out.println(p + ", It is your turn\nThe current card on play is:\n" + currentCard);

        lineDecoration();
        gameBoard(p);
        lineDecoration();

        if (currentCard.isSpecialCard()) {
            punishment += currentCard.getValue();
            UnoCard pick;
            if (!hasWildCard(p)) {
                System.out.println("You dont have a card to override the current special card, so you are penalised");
                for (int i = 0; i < punishment; i++) {

                    if (deckOfCards.isEmpty()) {
                        deckOfCards = new Deck(pileOfCards);
                    }
                    pick = deckOfCards.getTopCard();
                    p.withDrawCards(pick);
                    System.out.println("You picked: \n" + pick);
                    stop();

                }
                punishment = 0;
                currentCard = deckOfCards.getTopCard();
                System.out.println("The new current card is: \n" + currentCard);
            }

        }

        if (!matchColour(p) && !matchValue(p) && !hasSpecialCard(p)) {
            UnoCard pick = null;
            System.out.println("You dont have a valid card to play, so you have to pick cards.");
            while (!matchColour(p) && !matchValue(p) && !hasSpecialCard(p)) {

                stop();
                pick = deckOfCards.getTopCard();
                p.withDrawCards(pick);
                System.out.println("You picked:\n" + pick);

            }

            System.out.println("You recieved a valid card!");
            stop();
            System.out.println("You have the following cards: ");
            p.play();
        }

        System.out.println("Please pick a card:");
        pickCard = input.nextInt() - 1;

        while (!validateTheChoice(p, pickCard)) {

            System.out.println("Invalid pick. Please pick a valid card.");
            pickCard = input.nextInt() - 1;

        }

        UnoCard play = p.pushCard(pickCard);

        p.claimUno();
        currentCard = play;
        pileOfCards.add(currentCard);

    }

    /**
     * This method is to check for a special value
     *
     * @param p The player as an object
     * @return A Boolean value
     */
    private boolean hasSpecialCard(UnoPlayer p) {

        for (UnoCard c : p.listOfCards()) {

            if (c.isSpecialCard()) {
                return true;
            }

        }

        return false;
    }

    /**
     * This method is to check if the user choose a valid choice or not
     *
     * @param p The UNO player
     * @param choice An integer represents the index
     * @return A Boolean value
     */
    private boolean validateTheChoice(UnoPlayer p, int choice) {

        if (choice <= p.listOfCards().size()) {

            if (p.listOfCards().get(choice).getColor().equals(currentCard.getColor()) || p.listOfCards().get(choice).getValue() == currentCard.getValue() || p.listOfCards().get(choice).isSpecialCard()) {
                return true;
            }

        }

        return false;
    }

    /**
     * This method is to pause the rhythm of the game and let the user engage by
     * pressing Enter.
     */
    private void stop() {

        System.out.println("Press enter to continue......");
        input.nextLine();

    }

    /**
     * this method is to check if the color matches.
     *
     * @param p The player as an object
     * @return A Boolean value
     */

    private boolean matchColour(UnoPlayer p) {

        for (UnoCard c : p.listOfCards()) {

            if (c.getColor().equals(currentCard.getColor())) {
                return true;
            }

        }

        return false;
    }

    /**
     * This method check if the card value matches
     *
     * @param p The player as an object
     * @return A Boolean value
     */
    private boolean matchValue(UnoPlayer p) {

        for (UnoCard c : p.listOfCards()) {

            if (c.getValue() == currentCard.getValue()) {

                return true;

            }
        }

        return false;
    }

    /**
     * This method check is the player carries a wildCard
     *
     * @param p The player as an object
     * @return A Boolean value
     */
    private boolean hasWildCard(UnoPlayer p) {

        for (UnoCard c : p.listOfCards()) {

            if (c.isSpecialCard()) {
                if (c.getValue() >= currentCard.getValue()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method is to decorate the output on the console
     */

    private void lineDecoration() {

        System.out.println("== == == == == == == == == == == == == ==");
    }

    /**
     * This method is to return the starting card and can not be a special card
     *
     * @return The starting card of the game
     */
    private UnoCard startingCard() {

        UnoCard temp = deckOfCards.oneLess();
        while (temp.isSpecialCard()) {
            deckOfCards.shuffle();
            temp = deckOfCards.oneLess();
        }

        temp = deckOfCards.getTopCard();
        return temp;
    }

    /**
     *
     * @param p1 The first player
     * @param p2 The second player
     * @return A Boolean value
     */
    @Override
    public boolean declareWinner(UnoPlayer p1, UnoPlayer p2) {

        if (p1.checkWinner()) {
            System.out.println("______________________________");
            System.out.println(p1 + " has won");
            System.out.println("______________________________");
            return true;
        } else if (p2.checkWinner()) {
            System.out.println("______________________________");
            System.out.println(p2 + " has won");
            System.out.println("______________________________");
            return true;
        }

        return false;
    }

    /**
     * This method is to shoe the players turn and turn the focus on the current
     * turn
     *
     * @param p The player
     */
    public void gameBoard(UnoPlayer p) {

        if (p.toString().equals("Player 1")) {
            System.out.println("                Player 1");
            player1.play();
            player2.coverCards();
            System.out.println("                Player 2");
            System.out.println("");
        } else {

            System.out.println("                Player 1");
            player1.coverCards();
            player2.play();
            System.out.println("                Player 2");
            System.out.println("");

        }

    }

    /**
     * This method is created for decoration
     */

    public void pyramidPattern() {

        for (int i = 0; i < 5; i++) {
            for (int j = 5 - i; j > 1; j--) {
                System.out.print("  ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(" UNO  ");
            }

            System.out.println();
        }
        System.out.println(" ");
        System.out.println(" Advisor and Developed By: ");
        System.out.println(" Altaher, AlQasim, Onisha, Gurkirat ");
        System.out.println(" Group_9 (The Vipers) ");
        System.out.println(" ");
    }

}
