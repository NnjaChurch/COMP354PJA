package model;

import java.util.Arrays;

public class GameBoard {

    // Attributes
    private Card[] mCards;
    private KeyCard mKeyCard;
    private int mBlueScore;
    private int mRedScore;
    private boolean mBlueTurn;

    // Constructor
    public GameBoard(KeyCard keyCard) {
        this.mKeyCard = keyCard;
        this.mCards = generateGameBoard();
        initializeGame();
    }

    public GameBoard(GameBoard oldBoard) {
        Card[] oldCards = oldBoard.getBoard();
        KeyCard oldKeyCard = oldBoard.getKeyCard();
        this.mCards = new Card[oldCards.length];
        for(int i = 0; i < oldCards.length; i++) {
            this.mCards[i] = oldCards[i].clone();
        }
        this.mKeyCard = oldKeyCard.clone();
        this.setBlueScore(oldBoard.getBlueScore());
        this.setRedScore(oldBoard.getRedScore());
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

    public int getBlueScore() {
        return this.mBlueScore;
    }

    public int getRedScore() {
        return this.mRedScore;
    }

    public boolean getCurrentTurn() {
        return this.mBlueTurn;
    }

    // Setters
    public void setBlueScore(int blueScore) {
        this.mBlueScore = blueScore;
    }

    public void setRedScore(int redScore) {
        this.mRedScore = redScore;
    }

    public void setCurrentTurn(boolean currentTurn) {
        this.mBlueTurn = currentTurn;
    }

    // Methods
    public GameBoard clone() {
        return new GameBoard(this);
    }
    
    public String toString() {
        return(Arrays.toString(mCards));
    }

    private Card[] generateGameBoard() {
        Card[] gameBoard = new Card[25];
        String codeWord;
        for(int i = 0; i < 25; i++) {
            codeWord = "PlaceHolder";
            // TODO: CODE TO GENERATE CODEWORDS (NO DUPLICATES)
            gameBoard[i] = new Card(i + 1, mKeyCard.getCardType(i), codeWord);
        }
        return gameBoard;
    }

    private void initializeGame() {
        if(mKeyCard.getBlueFirst() == true) {
            this.mBlueScore = 9;
            this.mRedScore = 8;
            this.mBlueTurn = true;
        }
        else {
            this.mBlueScore = 8;
            this.mRedScore = 9;
            this.mBlueTurn = false;
        }
    }

    public void changeTurn() {
        this.mBlueTurn = !mBlueTurn;
    }

}
