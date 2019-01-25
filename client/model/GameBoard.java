package model;

public class GameBoard {

    // Attributes
    private Card[] mGameBoard;
    private KeyCard mKeyCard;

    // Constructor
    public GameBoard(KeyCard keyCard) {
        this.mKeyCard = keyCard;
        this.mGameBoard = generateGameBoard();
    }

    // Getters
    public Card getCard(int n) {
        return mGameBoard[n];
    }

    // Methods
    private Card[] generateGameBoard() {
        Card[] gameBoard = new Card[25];
        String codeWord;
        for(int i = 0; i < 25; i++) {
            codeWord = "PlaceHolder";
            // TODO: CODE TO GENERATE CODEWORDS (NO DUPLICATES)
            gameBoard[i] = new Card(mKeyCard.getCardType(i), codeWord);
        }
        return gameBoard;
    }

}
