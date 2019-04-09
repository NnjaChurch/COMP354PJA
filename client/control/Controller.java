/**
 * Controller object that handles the processing of Messages from the View (GUI)
 * Invokes the update() method and handles modifies the model accordingly
 * ENTRY POINT: Detects update in the Inbox.java observable
 * EXIT POINT: Updates a Card object or creates a New Game
 * @author Kevin McAllister (40031326) - Iteration 1
 * @author Robert Laviolette, Benson Chan - Iteration 2
 */
package control;

import javafx.scene.Scene;
import model.*;
import view.BoardPane;
import view.Style;

import java.util.*;

public class Controller implements Observer {

    // Attributes
    private Stack<LogMessage> mMessageStack;
    private Stack<LogMessage> mUndoStack;
    private ArrayList<LogMessage> mMessageLog;
    private KeyCard[] mKeyCardCollection;
    private GameBoard mGameBoard;
    private Inbox mInbox;
    private Outbox mOutbox;
    private Scene mGameScene;
    private BoardPane mBoardPane;
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
        this.mBlueRandom = true;
        this.mRedRandom = true;

        // Initialize Scene
        this.mBoardPane = new BoardPane(getCardList(), getKeyCard(), mInbox);
        this.mGameScene = new Scene(mBoardPane, Style.WINDOW_SIZE_WIDTH, Style.WINDOW_SIZE_HEIGHT);
    }

    // Setters
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

    public ArrayList<LogMessage> getMessageLog() {
        return this.mMessageLog;
    }

    public Scene getScene() {
        return this.mGameScene;
    }

    // Methods
    private KeyCard selectKeyCard(KeyCard[] keyCardCollection) {
        Random r = new Random();
        return keyCardCollection[r.nextInt(keyCardCollection.length)];
    }

    public void startNewGame(boolean blueRandom, boolean redRandom) {
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
        this.mBoardPane = new BoardPane(mGameBoard.getCards(), mGameBoard.getKeyCard(), mInbox);
        this.mGameScene.setRoot(mBoardPane);

        // Rebind Observer
        this.mOutbox.deleteObservers();
        this.mOutbox.addObserver(mBoardPane);

        // Generate Initial Hint
        String hint;
        if(mGameBoard.getCurrentTurn()) {
            hint = Strategy.generateHint(mGameBoard.getCards(), CardType.BLUE);
        }
        else {
            hint = Strategy.generateHint(mGameBoard.getCards(), CardType.RED);
        }
        this.mGameBoard.getGameObserver().pushFirstHint(hint);
        this.mOutbox.updateHint(hint);
        this.mOutbox.sendReply(new Reply(ReplyType.HINT, mGameBoard.getCurrentTurn()));

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
            if(type == MessageType.SELECT) {//Annes modified this function

                // Get Card object
                Card card = mGameBoard.getCard(cardAffected);

                // Check if Card is already revealed
                if (!card.isRevealed()) {

                    // Flush Undo stack
                    mUndoStack.clear();

                    // Push Message onto Message stack
                    mMessageStack.push(new LogMessage(type, cardAffected, Strategy.getStoredHint()));

                    // Add Message to Message log
                    mMessageLog.add(new LogMessage(type, cardAffected, Strategy.getStoredHint()));

                    // Reveal Card (will notify Observer)
                    card.revealCard();
                }
                // Otherwise do nothing
            }
            if(type == MessageType.NEXT) {

                // Flush Undo Stack
                mUndoStack.clear();

                // Generate an ArrayList of Cards based on the hint
                Strategy.getHintedCards(mGameBoard.getBoard()); //ROB CODE

                // Check which strategy to use for the list of applicable cards
                String wordOfCardAffected;
                if(mGameBoard.getCurrentTurn()) {
                    if(mBlueRandom) {
                    	wordOfCardAffected = Strategy.pickRandomCard(Strategy.storedHintedCards); //ROB CODE - changed mGameBoard.getBoard() to Strategy.storedHintedCards
                    }
                    else {
                    	wordOfCardAffected = Strategy.pickNextCard(Strategy.storedHintedCards); //ROB CODE - changed mGameBoard.getBoard() to Strategy.storedHintedCards
                    }
                }
                else {
                    if(mRedRandom) {
                    	wordOfCardAffected = Strategy.pickRandomCard(Strategy.storedHintedCards); //ROB CODE - changed mGameBoard.getBoard() to Strategy.storedHintedCards
                    }
                    else {
                    	wordOfCardAffected = Strategy.pickNextCard(Strategy.storedHintedCards); //ROB CODE - changed mGameBoard.getBoard() to Strategy.storedHintedCards
                    }
                }
                
                //ROB CODE
                //Convert wordOfCardAffected to cardAffected for below
                if (wordOfCardAffected != Strategy.NULL_CARD_ERROR)
                {
                	cardAffected = mGameBoard.getCardIndexOfCodeword(wordOfCardAffected);
                }
                else
                {
                	cardAffected = -1;
                }
                //END ROB CODE

                // Check if Card was selected (-1 mean no more cards)
                if (cardAffected != -1) {
                    // Get Card Object
                    Card card = mGameBoard.getCard(cardAffected);

                    // Flush Undo Stack
                    mUndoStack.clear();

                    // Push Message onto Message stack
                    mMessageStack.push(new LogMessage(type, cardAffected, Strategy.getStoredHint()));

                    // Add Message to Message log
                    mMessageLog.add(new LogMessage(type, cardAffected, Strategy.getStoredHint()));

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
                    LogMessage undoMessage = mMessageStack.pop();

                    // Unpack Message
                    MessageType undoType = undoMessage.getType();
                    int undoCardAffected = undoMessage.getCardAffected();
                    String undoHint = undoMessage.getHint();
                    
                    // Clear Hint in Outbox
                    mOutbox.updateHint("");

                    // Get Card object
                    Card undoCard = mGameBoard.getCard(undoCardAffected);

                    // Push the Undo onto the Undo stack
                    mUndoStack.push(new LogMessage(undoType, undoCardAffected, undoHint));

                    // Add Message to Message log
                    mMessageLog.add(new LogMessage(undoType, undoCardAffected, undoHint));

                    // Hide Card (will notify Observer)
                    undoCard.hideCard();
                }
            }
            if(type == MessageType.REDO) {

                if (!mUndoStack.isEmpty()) {
                    // Get last Message off Undo stack
                    LogMessage redoMessage = mUndoStack.pop();

                    // Unpack Message
                    MessageType redoType = redoMessage.getType();
                    int redoCardAffected = redoMessage.getCardAffected();
                    String redoHint = redoMessage.getHint();
                    
                    // Update Hint in Outbox
                    mOutbox.updateHint(redoHint);

                    // Get Card object
                    Card redoCard = mGameBoard.getCard(redoCardAffected);

                    // Push the Redo onto the Message stack
                    mMessageStack.push(new LogMessage(redoType, redoCardAffected, redoHint));

                    // Add Message to Message log
                    mMessageLog.add(new LogMessage(redoType, redoCardAffected, redoHint));

                    // Reveal Card (will notify Observer)
                    redoCard.revealCard();
                }
            }
        }
    }
}