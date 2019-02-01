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
        Card[] gameBoard = new Card[25];
        String codeWord;
        for(int i = 0; i < 25; i++) {
            codeWord = "PlaceHolder";
            // TODO: CODE TO GENERATE CODEWORDS (NO DUPLICATES)
            // Add Card to GameBoard
            gameBoard[i] = new Card(i + 1, codeWord, mKeyCard.getCardType(i));
            // Attach GameObserver to Card
            gameBoard[i].addObserver(this.mGameObserver);
        }
        return gameBoard;
    }
}
