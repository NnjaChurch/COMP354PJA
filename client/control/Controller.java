package control;

import model.GameBoard;
import model.KeyCard;

import java.util.Observable;
import java.util.Random;
import java.util.Stack;

public class Controller extends Observable {

    // Attributes
    private Stack<Command> mCommandStack;
    private Stack<Command> mUndoStack;
    private KeyCard[] mKeyCardCollection;
    private GameBoard mGameBoard;

    // Constructor
    public Controller(KeyCard[] keyCardCollection) {
        this.mCommandStack = new Stack<>();
        this.mUndoStack = new Stack<>();
        this.mKeyCardCollection = keyCardCollection;
        this.mGameBoard = new GameBoard(selectKeyCard(this.mKeyCardCollection));
    }

    // Methods
    public void getMessage(Message m) {
        // Handle message type
        if(m instanceof SelectMessage) {
            // Unpack message
            String message = m.toString();
            int cardAffected = ((SelectMessage) m).getCardAffected();
            // Create command and push to commandStack
            Command select = new Command(cardAffected, this.mGameBoard.clone());
            mCommandStack.push(select);
            // Handle select command
            if(mGameBoard.getCard(cardAffected).isRevealed()) {
                // Remove command from stack and do nothing
                mCommandStack.pop();
            }
            else {
                // Flush undoStack
                mUndoStack.clear();
                // Call Card reveal function
                mGameBoard.getCard(cardAffected).revealCard();
                // Notify observer
                setChanged();
                notifyObservers(mGameBoard.getCard(cardAffected));
            }
        }
        if(m instanceof NextMessage) {
            // Unpack message
            String message = m.toString();
            // TODO: CODE FOR NEXT ACTION (RANDOM / SEQUENTIAL)
            // Flush undoStack
            mUndoStack.clear();
            // Create command and push to commandStack
            // TODO: REPLACE 0 WITH OUTPUT OF NEXT ACTION CODE
            Command next = new Command(0, this.mGameBoard.clone());
            mCommandStack.push(next);
            // Handle next command

        }
        if(m instanceof UndoMessage) {
            // Unpack message
            String message = m.toString();
            if (mCommandStack.isEmpty() == false) {
               undoRedo(mCommandStack, mUndoStack);
            }
            else {
                // Do nothing
            }
        }
        if(m instanceof RedoMessage) {
            // Unpack message
            String message = m.toString();
            if(mUndoStack.isEmpty() == false) {
                undoRedo(mUndoStack, mCommandStack);
            }
            else {
                // Do nothing
            }
        }
        if(m instanceof NewGameMessage) {
            // Unpack message
            String message = m.toString();
            // Flush both stacks
            mCommandStack.clear();
            mUndoStack.clear();
            // Create new game
            mGameBoard = new GameBoard(selectKeyCard(this.mKeyCardCollection));
            // Notify observer
            setChanged();
            notifyObservers(mGameBoard);
        }
        if(m instanceof KeyCardMessage) {
            // Unpack message
            String message = m.toString();
            KeyCard keyCard = mGameBoard.getKeyCard();
            // Notify observer
            setChanged();
            notifyObservers(keyCard);
        }
    }

    private KeyCard selectKeyCard(KeyCard[] keyCardCollection) {
        Random r = new Random();
        return keyCardCollection[r.nextInt(keyCardCollection.length)];
    }

    private void undoRedo(Stack<Command> popStack, Stack<Command> pushStack) {
        // Get last command off the undoStack
        Command restore = popStack.pop();
        // Get GameBoard to restore and card affected
        GameBoard restoreState = restore.getState();
        int cardAffected = restore.getCardAffected();
        // Create command and push onto commandStack
        Command redo = new Command(cardAffected, new GameBoard(this.mGameBoard.clone()));
        pushStack.push(redo);
        // Update GameBoard
        this.mGameBoard = restoreState;
        // Notify observer
        setChanged();
        notifyObservers(mGameBoard.getCard(cardAffected));
    }

}
