/**
 * Strategy class (static) with methods to process a Computer turn
 * Implements a random selection strategy and a next available strategy
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package control;

import model.Card;
import model.DatabaseExtractor;

import java.util.ArrayList;
import java.util.Random;

public class Strategy {
	
	public static ArrayList<Card> storedHintedCards;
	public static String storedHint;
	public static final String NULL_CARD_ERROR = "NULL CARD ERROR";

    public static String pickRandomCard(ArrayList<Card> cards) { //ROB CODE - Had to change the return type to String for modularity's sake
    	if (cards.size() == 0) //ROB CODE - Error checking
    	{
        	return NULL_CARD_ERROR;
    	}
    	
        Random r = new Random();
        boolean cardPicked = false;
        int currentCard = -1;
        boolean allCardsRevealed = true;

        //Check if all cards have already been revealed
        for(Card card: cards) {
            if(!card.isRevealed()) {
                allCardsRevealed = false;
            }
        }

        if (!allCardsRevealed) {
            while(cardPicked == false) {
                // Pick random card of the list
                currentCard = r.nextInt(cards.size()); //ROB - changed this from 25 to cards.size() so we can use the function to select a random card from those given by the hint as well
                // Check if card has been picked
                if(cards.get(currentCard).isRevealed() == false) {
                    cardPicked = true;
                }
            }
        }
        
        if (currentCard == -1) //ROB CODE - Error checking
        {
        	return NULL_CARD_ERROR;
        }
        return cards.get(currentCard).getCodeWord(); //ROB CODE - Had to change the return type to String for modularity's sake
    }

    public static String pickNextCard(ArrayList<Card> cards) { //ROB CODE - Had to change the return type to String for modularity's sake
    	if (cards.size() == 0) //ROB CODE - Error checking
    	{
        	return NULL_CARD_ERROR;
    	}
    	
        boolean cardPicked = false;
        int currentCard = 0;
        boolean allCardsRevealed = true;

        //Check if all cards have already been revealed
        for(Card card: cards) {
            if(!card.isRevealed()) {
                allCardsRevealed = false;
            }
        }

        if (!allCardsRevealed) {
            while(cardPicked == false) {
                // Check if card has been picked
                if(cards.get(currentCard).isRevealed() == false) {
                    // Flag to exit loop if card has not been picked yet
                    cardPicked = true;
                }
                else {
                    // Increment if card has already been picked
                    currentCard++;
                }
            }
        }
        else {
            currentCard = -1;
        }

        if (currentCard == -1) //ROB CODE - Error checking
        {
        	return NULL_CARD_ERROR;
        }
        return cards.get(currentCard).getCodeWord(); //ROB CODE - Had to change the return type to String for modularity's sake
    }

    //ROB CODE
    public static void getHintedCards(ArrayList<Card> mGameBoard) {
    	storedHintedCards.clear();
    	
    	Card currentCard;
    	for (int i = 0; i < mGameBoard.size(); i++)
    	{
    		currentCard = mGameBoard.get(i);
    		if (!currentCard.isRevealed() && currentCard.containtsHint(storedHint))
    		{
    			storedHintedCards.add(currentCard);
    		}
    	}
    }
    
    //ROB CODE (FIXIT) - Ke will give this function meat
    public static void generateHint()
    {
    	storedHint = "hint";
    }
}
