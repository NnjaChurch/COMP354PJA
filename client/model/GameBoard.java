/**
 * GameBoard object to store cards, copy of keycard and the GameObserver.java object
 * Creation of the object creates the 25 cards based on the keycard and gets a selection a 25 words as codewords.
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package model;

import control.GameObserver;
import control.Outbox;

public class GameBoard {

    // Attributes
    private Card[] mCards;
    private KeyCard mKeyCard;
    private GameObserver mGameObserver;

    // Constructor
    public GameBoard(KeyCard keyCard, Outbox outbox) {
        this.mKeyCard = keyCard;
        this.mCards = generateGameBoard();
        this.mGameObserver = new GameObserver(mKeyCard, outbox);
    }

    // Getters
    public Card getCard(int n) {
        return mCards[n];
    }

    public Card[] getBoard() {
        return this.mCards;
    }

    public KeyCard getKeyCard() {
        return this.mKeyCard;
    }

    // Methods
    private Card[] generateGameBoard() {
        DatabaseExtractor database = new DatabaseExtractor();
        Card[] gameBoard = new Card[25];
        String[] codeWords = database.bankOfWords();
        for(int i = 0; i < 25; i++) {
            // Add Card to GameBoard
            gameBoard[i] = new Card(i + 1, codeWords[i], mKeyCard.getCardType(i));
            // Attach GameObserver to Card
            gameBoard[i].addObserver(this.mGameObserver);
        }
        return gameBoard;
    }
}
