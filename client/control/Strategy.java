/**
 * Strategy class (static) with methods to process a Computer turn
 * Implements a random selection strategy and a next available strategy
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package control;

import model.Card;

import java.util.Random;

public class Strategy {

    public static int pickRandomCard(Card[] cards) {
        Random r = new Random();
        boolean cardPicked = false;
        int currentCard = -1;
        while(cardPicked == false) {
            // Pick random card of 25
            currentCard = r.nextInt(25);
            // Check if card has been picked
            if(cards[currentCard].isRevealed() == false) {
                cardPicked = true;
            }
        }
        return currentCard;
    }

    public static int pickNextCard(Card[] cards) {
        boolean cardPicked = false;
        int currentCard = 0;
        while(cardPicked == false) {
            // Check if card has been picked
            if(cards[currentCard].isRevealed() == false) {
                // Flag to exit loop if card has not been picked yet
                cardPicked = true;
            }
            else {
                // Increment if card has already been picked
                currentCard++;
            }
        }
        return currentCard;
    }
}
