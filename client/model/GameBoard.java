/**
 * GameBoard object to store cards, copy of keycard and the GameObserver.java object
 * Creation of the object creates the 25 cards based on the keycard and gets a selection a 25 words as codewords.
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package model;

import control.GameObserver;
import control.Outbox;

import java.util.ArrayList;

public class GameBoard {

    // Attributes
    private ArrayList<Card> mCards;
    private KeyCard mKeyCard;
    private GameObserver mGameObserver;

    // Constructor
    public GameBoard(KeyCard keyCard, Outbox outbox) {
        this.mKeyCard = keyCard;
        this.mGameObserver = new GameObserver(mKeyCard, outbox);
        this.mCards = generateGameBoard();
        mGameObserver.setCards(mCards);
    }

    // Getters
    public Card getCard(int n) {
        return mCards.get(n);
    }

    public ArrayList<Card> getBoard() {
        return this.mCards;
    }

    public KeyCard getKeyCard() {
        return this.mKeyCard;
    }

    public ArrayList<Card> getCards() {
        return this.mCards;
    }

    public boolean getCurrentTurn() {
        return this.mGameObserver.getCurrentTurn();
    }

    public GameObserver getGameObserver() {
        return this.mGameObserver;
    }

    // Methods
    private ArrayList<Card> generateGameBoard() {
        DatabaseExtractor database = new DatabaseExtractor();
        ArrayList<Card> gameBoard = new ArrayList<>(25);
        Card card;
        //String[] codeWords = database.bankOfWords();
        SimpleCard[] simpleCard = database.bankOfCard(); 
        for(int i = 0; i < 25; i++) {
            // Create Card
            card = new Card(i, simpleCard[i].word,mKeyCard.getCardType(i),simpleCard[i].hints ); //Ke:use the simpleCard to get the hint from database but still pass it as a Card.
            // Attach GameObserver to Card
            card.addObserver(this.mGameObserver);
            // Add Card to GameBoard
            gameBoard.add(card);
        }
        return gameBoard;
    }
    
    //ROB CODE
    public int getCardIndexOfCodeword(String codeword)
    {    	
    	for (int i = 0; i < mCards.size(); i++)
    	{
    		if (mCards.get(i).getCodeWord() == codeword)
    		{
    			return i;
    		}
    	}
    	
    	return -1;
    }
    //END ROB CODE
}
