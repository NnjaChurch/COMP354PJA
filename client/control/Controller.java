/**
 * Controller object that handles the processing of Messages from the View (GUI)
 * Invokes the update() method and handles modifies the model accordingly
 * ENTRY POINT: Detects update in the Inbox.java observable
 * EXIT POINT: Updates a Card object or creates a New Game
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package control;

import javafx.scene.Scene;
import model.*;
import view.BoardPane;

import java.util.*;

public class Controller implements Observer {

    // Attributes
    private Stack<Message> mMessageStack;
    private Stack<Message> mUndoStack;
    private ArrayList<Message> mMessageLog;
    private KeyCard[] mKeyCardCollection;
    private GameBoard mGameBoard;
    private Inbox mInbox;
    private Outbox mOutbox;
    private Scene mGameScene;
    private boolean mBlueRandom;
    private boolean mRedRandom;

    // Constructor
    public Controller(KeyCard[] keyCardCollection, Inbox inbox, Outbox outbox) {
        this.mMessageStack = new Stack<>();
        this.mUndoStack = new Stack<>();
        this.mMessageLog = new ArrayList<>();
        this.mKeyCardCollection = keyCardCollection;
        this.mGameBoard = new GameBoard(selectKeyCard(this.mKeyCardCollection), outbox);
        this.mInbox = inbox;
        this.mOutbox = outbox;
    }

    // Setter
    public void setGameScene(Scene gameScene) {
        this.mGameScene = gameScene;
    }

    // Getters
    public KeyCard getKeyCard() {
        return mGameBoard.getKeyCard();
    }

    public ArrayList<Card> getCardList() {
        return mGameBoard.getCards();
    }

    public ArrayList<Message> getMessageLog() {
        return this.mMessageLog;
    }

    // Methods
    private KeyCard selectKeyCard(KeyCard[] keyCardCollection) {
        Random r = new Random();
        return keyCardCollection[r.nextInt(keyCardCollection.length)];
    }

    private void startNewGame(boolean blueRandom, boolean redRandom) {
        // Wipe Stacks and Logs
        this.mMessageStack.clear();
        this.mUndoStack.clear();
        this.mMessageLog.clear();

        // Update the Strategy Flags
        this.mBlueRandom = blueRandom;
        this.mRedRandom = redRandom;

        // Create new Instance of Game
        this.mGameBoard = new GameBoard(selectKeyCard(this.mKeyCardCollection), mOutbox);

        // Create and update Scene
        BoardPane newBoardPane = new BoardPane(mGameBoard.getCards(), mGameBoard.getKeyCard(), mInbox);
        this.mGameScene.setRoot(newBoardPane);

        // Rebind Observer
        this.mOutbox.deleteObservers();
        this.mOutbox.addObserver(newBoardPane);
    }

    @Override
    public void update(Observable o, Object arg) {

        // Check if Message Object
        if(arg instanceof Message) {

            // Unpack Message
            MessageType type = ((Message) arg).getMessageType();
            int cardAffected = ((Message) arg).getCardAffected();

            // Handle Message
            if(type == MessageType.NEW_GAME_B_RANDOM_R_RANDOM) {
                startNewGame(true, true);
            }
            if(type == MessageType.NEW_GAME_B_NEXT_R_NEXT) {
                startNewGame(false, false);

            }
            if(type == MessageType.NEW_GAME_B_NEXT_R_RANDOM) {
                startNewGame(false, true);

            }
            if(type == MessageType.NEW_GAME_B_RANDOM_R_NEXT) {
                startNewGame(true, false);

            }
            if(type == MessageType.SELECT) {

                // Get Card object
                Card card = mGameBoard.getCard(cardAffected);

                // Check if Card is already revealed
                if(!card.isRevealed()) {

                    // Flush Undo stack
                    mUndoStack.clear();

                    // Push Message onto Message stack
                    mMessageStack.push(new Message(type, cardAffected));

                    // Add Message to Message log
                    mMessageLog.add(new Message(type, cardAffected));

                    // Reveal Card (will notify Observer)
                    card.revealCard();
                }
                // Otherwise do nothing
            }
            if(type == MessageType.NEXT) {

                // Flush Undo Stack
                mUndoStack.clear();

                // Check which strategy to use
                if(mGameBoard.getCurrentTurn()) {
                    if(mBlueRandom) {
                        cardAffected = Strategy.pickRandomCard(mGameBoard.getBoard());
                    }
                    else {
                        cardAffected = Strategy.pickNextCard(mGameBoard.getBoard());
                    }
                }
                else {
                    if(mRedRandom) {
                        cardAffected = Strategy.pickRandomCard(mGameBoard.getBoard());
                    }
                    else {
                        cardAffected = Strategy.pickNextCard(mGameBoard.getBoard());
                    }
                }

                // Check if Card was selected (-1 mean no more cards)

                if (cardAffected != -1) {
                    // Get Card Object
                    Card card = mGameBoard.getCard(cardAffected);

                    // Flush Undo Stack
                    mUndoStack.clear();

                    // Push Message onto Message stack
                    mMessageStack.push(new Message(type, cardAffected));

                    // Add Message to Message log
                    mMessageLog.add(new Message(type, cardAffected));

                    // Reveal Card (will notify Observer)
                    card.revealCard();
                }
                else {
                    // Do nothing
                }
            }
            if(type == MessageType.UNDO) {

                if (!mMessageStack.isEmpty()) {
                    // Get last Message off Message stack
                    Message undoMessage = mMessageStack.pop();

                    // Unpack Message
                    MessageType undoType = undoMessage.getMessageType();
                    int undoCardAffected = undoMessage.getCardAffected();

                    // Get Card object
                    Card undoCard = mGameBoard.getCard(undoCardAffected);

                    // Push the Undo onto the Undo stack
                    mUndoStack.push(new Message(undoType, undoCardAffected));

                    // Add Message to Message log
                    mMessageLog.add(new Message(undoType, undoCardAffected));

                    // Hide Card (will notify Observer)
                    undoCard.hideCard();
                }
            }
            if(type == MessageType.REDO) {

                if (!mUndoStack.isEmpty()) {
                    // Get last Message off Undo stack
                    Message redoMessage = mUndoStack.pop();

                    // Unpack Message
                    MessageType redoType = redoMessage.getMessageType();
                    int redoCardAffected = redoMessage.getCardAffected();

                    // Get Card object
                    Card redoCard = mGameBoard.getCard(redoCardAffected);

                    // Push the Redo onto the Message stack
                    mMessageStack.push(new Message(redoType, redoCardAffected));

                    // Add Message to Message log
                    mMessageLog.add(new Message(redoType, redoCardAffected));

                    // Reveal Card (will notify Observer)
                    redoCard.revealCard();
                }
            }
        }
    }
}