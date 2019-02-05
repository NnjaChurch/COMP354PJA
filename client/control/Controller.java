/**
 * Controller object that handles the processing of Messages from the View (GUI)
 * Invokes the update() method and handles modifies the model accordingly
 * ENTRY POINT: Detects update in the Inbox.java observable
 * EXIT POINT: Updates a Card object or creates a New Game
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package control;

import model.*;

import java.util.*;

public class Controller implements Observer {

    // Attributes
    private Stack<Message> mMessageStack;
    private Stack<Message> mUndoStack;
    private ArrayList<Message> mMessageLog;
    private KeyCard[] mKeyCardCollection;
    private GameBoard mGameBoard;

    // Constructor
    public Controller(KeyCard[] keyCardCollection, Outbox outbox) {
        this.mMessageStack = new Stack<>();
        this.mUndoStack = new Stack<>();
        this.mMessageLog = new ArrayList<>();
        this.mKeyCardCollection = keyCardCollection;
        this.mGameBoard = new GameBoard(selectKeyCard(this.mKeyCardCollection), outbox);
    }

    private KeyCard selectKeyCard(KeyCard[] keyCardCollection) {
        Random r = new Random();
        return keyCardCollection[r.nextInt(keyCardCollection.length)];
    }

    @Override
    public void update(Observable o, Object arg) {

        // Check if Message Object
        if(arg instanceof Message) {

            // Unpack Message
            MessageType type = ((Message) arg).getMessageType();
            int cardAffected = ((Message) arg).getCardAffected();

            // Handle Message
            if(type == MessageType.NEW_GAME) {
                // TODO: CALL FUNCTION TO REBUILD GAME
            }
            if(type == MessageType.SELECT) {

                // Get Card object
                Card card = mGameBoard.getCard(cardAffected);

                // Check if Card is already revealed
                if(card.isRevealed() == false) {

                    // Flush Undo stack
                    mUndoStack.clear();

                    // Push Message onto Message stack
                    mMessageStack.push(new Message(type, cardAffected));

                    // Add Message to Message log
                    mMessageLog.add(new Message(type, cardAffected));

                    // Reveal Card (will notify Observer)
                    card.revealCard();
                }
                else {
                    // Do Nothing
                }
            }
            if(type == MessageType.NEXT) {

                // Flush Undo Stack
                mUndoStack.clear();
                // TODO: SELECT EITHER: RANDOM OR NEXT
                cardAffected = Strategy.pickNextCard(mGameBoard.getBoard());
                cardAffected = Strategy.pickRandomCard(mGameBoard.getBoard());

                // Get Card Object
                Card card = mGameBoard.getCard(cardAffected);

                // Card will guarantee be not revealed (due to the way the strategy is coded)

                // Flush Undo Stack
                mUndoStack.clear();

                // Push Message onto Message stack
                mMessageStack.push(new Message(type, cardAffected));

                // Add Message to Message log
                mMessageLog.add(new Message(type, cardAffected));

                // Reveal Card (will notify Observer)
                card.revealCard();
            }
            if(type == MessageType.UNDO) {

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
            if(type == MessageType.REDO) {

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