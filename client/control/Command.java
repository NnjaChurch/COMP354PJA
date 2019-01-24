package control;

public class Command {

    // Attributes
    private int mCardAffected;
    private GameState mState;

    // Constructor
    public Command(int mCardAffected) {
        this.mCardAffected = mCardAffected;
        this.mState = new GameState();
    }

    // Getters
    public int getCardAffected() {
        return this.mCardAffected;
    }

    // Methods
    public GameState undoCommand() {
        // Store game state to restore
        GameState undoState = this.mState;
        // Update mState to contain gameState for 'redo' action
        this.mState = new GameState();
        // Return game state to restore
        return undoState;
    }

    public GameState redoCommand() {
        // Store game state to restore
        GameState redoState = this.mState;
        // Update mState to contain gameState for 'undo' action
        this.mState = new GameState();
        // Return gameState to restore
        return redoState;
    }

}
