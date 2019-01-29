package control;

import model.GameBoard;

public class Command {

    // Attributes
    private int mCardAffected;
    private GameBoard mState;

    // Constructor
    public Command(int mCardAffected, GameBoard currentBoard) {
        this.mCardAffected = mCardAffected;
        this.mState = currentBoard.clone();
    }

    // Getters
    public int getCardAffected() {
        return this.mCardAffected;
    }

    public GameBoard getState() {
        return this.mState.clone();
    }
}
