/*
Naming is done for this class, and all is needed is to replace declareWinner with another method 
and add Override.
 */
package UnoGameGroup_9;

import java.util.ArrayList;
import java.util.Scanner;

public class UnoGame extends Game {

    private UnoCard currentCard; // the current card or previously played card or starting card
    private GroupOfCards deckOfCards; // the deck of the game
    private ArrayList<UnoCard> pileOfCards; //when players throw card, it piles up here. Also, creates a new deck if the current deck is empty
    private int punishment; // when wildcards stack up the penalty stacks up. If a player is unable to counter the current wildcard on play, player is penalised "penalty" number of cards
    private Scanner input;
    private UnoPlayer player1, player2; //player 1 and 2
    private int pickCard; // players pick

    public UnoGame() {
        /*constructor
		 * constructs the game
		 * prepares the game to play
         */
        super("Uno");
        deckOfCards = new GroupOfCards();
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

    @Override
    public void play() {
        /* this method simulates turns between the two players. when turn is even, player 1 plays and when 
			   turn is odd player 2 plays.
         */
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

    private void handOutCards() {
        //this method distributes cards to the players
        for (int i = 0; i < 10; i++) {

            if (i % 2 == 0) {
                player1.withDrawCards(deckOfCards.getTopCard());
            } else {
                player2.withDrawCards(deckOfCards.getTopCard());
            }

        }
    }

    public void playUnoGame(UnoPlayer p) {
        /*	 this method takes player that is currently playing as an argument.
			 this method contains entire process for the game.
         */

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
                        deckOfCards = new GroupOfCards(pileOfCards);
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

//		}   
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
        //System.out.println(pick);

        while (!validateTheChoice(p, pickCard)) {

            System.out.println("Invalid pick. Please pick a valid card.");
            pickCard = input.nextInt() - 1;

        }

        UnoCard play = p.pushCard(pickCard);

        p.claimUno();
        currentCard = play;
        pileOfCards.add(currentCard);
        //reviveDeck();
    }

    private boolean hasSpecialCard(UnoPlayer p) {
        // TODO Auto-generated method stub

        for (UnoCard c : p.listOfCards()) {

            if (c.isSpecialCard()) {
                return true;
            }

        }

        return false;
    }

    private boolean validateTheChoice(UnoPlayer p, int choice) {

        /*
		 * checks if the user selection was a valid choice or not
		 * to be a valid choice: the player must have the card, the card must be either the same color or value as the current card(card in play/previously played card)
         */
        if (choice <= p.listOfCards().size()) {
            //add for special

            if (p.listOfCards().get(choice).getColor().equals(currentCard.getColor()) || p.listOfCards().get(choice).getValue() == currentCard.getValue() || p.listOfCards().get(choice).isSpecialCard()) {
                return true;
            }

        }

        return false;
    }

    private void stop() {
        /*
		 * creates a pause
		 * asks the user to press enter
		 * purpose is simply to get user engagement
         */
        System.out.println("Press enter to continue......");
        input.nextLine();

    }

    private boolean matchColour(UnoPlayer p) {
        /*
		 * checks if player has card of the same color as the current card that is being played
         */
        for (UnoCard c : p.listOfCards()) {

            if (c.getColor().equals(currentCard.getColor())) {
                return true;
            }

        }

        return false;
    }

    private boolean matchValue(UnoPlayer p) {
        /*
		 * checks if the player has the card of the same value as the current that is being played. 
         */

        for (UnoCard c : p.listOfCards()) {

            if (c.getValue() == currentCard.getValue()) {

                return true;

            }
        }

        return false;
    }

    private boolean hasWildCard(UnoPlayer p) {

        /*
		 * checks if player has a wild card (special card)
		 * special cards can be played when you don't have a card with the same color or the value of the card that is being currently played
		 * if the current card is a special card, then player must have a special card with the same or greater value to play.
         */
        for (UnoCard c : p.listOfCards()) {

            if (c.isSpecialCard()) {
                if (c.getValue() >= currentCard.getValue()) {
                    return true;
                }
            }
        }

        return false;
    }

    private void lineDecoration() {
        /*
		 * draws asterik lines
         */

        System.out.println("== == == == == == == == == == == == == ==");
    }

    private UnoCard startingCard() {

        /*
		 * gets a valid starting card.
		 * starting card cannot be a special card
         */
        UnoCard temp = deckOfCards.peeeeeeks();
        while (temp.isSpecialCard()) {
            deckOfCards.shuffle();
            temp = deckOfCards.peeeeeeks();
        }

        temp = deckOfCards.getTopCard();
        return temp;
    }

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
        System.out.println(" Altaher, AlQasim, Onisha ");
        System.out.println(" Group_9 ");
        System.out.println(" ");
    }

}
