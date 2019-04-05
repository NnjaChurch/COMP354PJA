/**
 * Strategy class (static) with methods to process a Computer turn
 * Implements a random selection strategy and a next available strategy
 * @author Kevin McAllister (40031326) - Iteration 1
 * @author Ke Ma , Robert Laviolette - Iteration 2
 */
package control;

import model.Card;
import model.CardType;
import model.DatabaseExtractor;

import java.util.ArrayList;
import java.util.Hashtable;
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
    	storedHintedCards = new ArrayList<Card>();

    	for(Card card : mGameBoard) {
    		if(!card.isRevealed() && card.containtsHint(storedHint)) {
    			storedHintedCards.add(card);
    		}
    	}
    }
    
    //ROB CODE (FIXIT) - Ke will give this function meat
    
    //KE CODE
    public static String generateHint(ArrayList<Card> cardList, CardType type)
    {
    	Hashtable<String,Integer> table =new Hashtable<String,Integer>();

    	ArrayList<Card> cards = new ArrayList<Card>(); 	
    	
    	for(Card card : cardList) {
    		if ((card.getType() == type) && !card.isRevealed()) {
    			cards.add(card);
    		}
    	}
    	
    	for(Card card : cards) {
    		String[] hints = card.getHints();
    		for(String hint : hints) {
    			if(table.get(hint) == null) {
    				table.put(hint, 1);
    			}
    			else {
    				int temp = table.get(hint);
    				temp++;
    				table.put(hint, temp);
    			}
    		}
    	}
    	int c = 0;
    	for (String key : table.keySet()) {
    	    if (table.get(key) > c) {
    	    	c = table.get(key);
    	    	storedHint = key; 	    	
    	    }
    	}
    	return storedHint;
    	   	
    }
    
    //KE CODE END
    public static String getStoredHint() {
        return storedHint;
    }
}
