/**
 * Game Observer object that handles card updates and notifies changes to the View (GUI)
 * Processes Card changes and updates scores / turns accordingly
 * ENTRY POINT: Detects update in Card and runs update() to process
 * EXIT POINT: Creates and sends a Reply.java object to the Outbox to notify the View
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package control;

import model.Card;
import model.CardType;
import model.KeyCard;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

public class GameObserver implements Observer {

    // Attributes
    private Outbox mOutbox;
    private int mBlueScore;
    private int mRedScore;
    private boolean mBlueTurn;
    private Stack<Boolean> mTurnStack;

    // Constructors
    public GameObserver(KeyCard keyCard, Outbox outbox) {
        this.mOutbox = outbox;
        this.mTurnStack = new Stack<>();
        initializeGame(keyCard);
    }

    // Getters
    public boolean getCurrentTurn() {
        return this.mBlueTurn;
    }

    //Methods
    private void initializeGame(KeyCard keyCard) {
        if(keyCard.getBlueFirst()) {
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

    private void incrementBlue() {
      mBlueScore++;
    }

    private void incrementRed() {
        mRedScore++;
    }

    private void decrementBlue() {
        mBlueScore--;
    }

    private void decrementRed() {
        mRedScore--;
    }

    private void sameTurn() {
        this.mTurnStack.push(mBlueTurn);
    }

    private void nextTurn() {
        this.mTurnStack.push(mBlueTurn);
        this.mBlueTurn = !mBlueTurn;
    }

    private void previousTurn() {
        if (!this.mTurnStack.isEmpty()) {
            boolean previousTurn = this.mTurnStack.pop();
            this.mBlueTurn = previousTurn;
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        // Check if Card object
        if(arg instanceof Card) {
            // Cast to Card object for method calls
            Card card = (Card)arg;

            // If Card was revealed (Next, Select, Redo)
            if(card.isRevealed()) {
                // If Card was Assassin
                if(card.getType() == CardType.BLACK) {
                    // Swap turn to other team
                    nextTurn();

                    // Send Reply to end the game with the updated values
                    mOutbox.sendReply(new Reply(ReplyType.END, card.getCardNumber(), card.getType(), mBlueScore, mRedScore, mBlueTurn));
                }
                // If Card was Bystander
                if(card.getType() == CardType.YELLOW) {
                    // Swap turn to other team
                    nextTurn();

                    // Send Reply to update game
                    mOutbox.sendReply(new Reply(ReplyType.UPDATE, card.getCardNumber(), card.getType(), mBlueScore, mRedScore, mBlueTurn));
                }
                // If Card was Blue
                if(card.getType() == CardType.BLUE) {
                    // Decrement blueScore
                    decrementBlue();
                    if(mBlueScore == 0) {
                        mOutbox.sendReply(new Reply(ReplyType.END, card.getCardNumber(), card.getType(), mBlueScore, mRedScore, true));
                    }
                    // If currently Blue turn
                    else {
                        if (mBlueTurn == true) {
                            // Log turn state
                            sameTurn();

                            // Send Reply to update game
                            mOutbox.sendReply(new Reply(ReplyType.UPDATE, card.getCardNumber(), card.getType(), mBlueScore, mRedScore, mBlueTurn));
                        }
                        // If currently Red turn
                        else {
                            // Swap turn to other team
                            nextTurn();

                            // Send Reply to update game
                            mOutbox.sendReply(new Reply(ReplyType.UPDATE, card.getCardNumber(), card.getType(), mBlueScore, mRedScore, mBlueTurn));
                        }
                    }
                }
                // If Card was Red
                if(card.getType() == CardType.RED) {
                    // Decrement redScore
                    decrementRed();
                    if (mRedScore == 0) {
                        mOutbox.sendReply((new Reply(ReplyType.END, card.getCardNumber(), card.getType(), mBlueScore, mRedScore, false)));
                    }
                    // If currently Blue turn
                    else {
                        if (mBlueTurn == true) {
                            // Swap to other team
                            nextTurn();

                            // Send Reply to update game
                            mOutbox.sendReply(new Reply(ReplyType.UPDATE, card.getCardNumber(), card.getType(), mBlueScore, mRedScore, mBlueTurn));
                        }
                        // If currently Red turn
                        else {
                            // Log turn state
                            sameTurn();

                            // Send Reply to update game
                            mOutbox.sendReply(new Reply(ReplyType.UPDATE, card.getCardNumber(), card.getType(), mBlueScore, mRedScore, mBlueTurn));
                        }
                    }
                }
            }
            // If Card was hidden (Undo)
            else {
                if(card.getType() == CardType.YELLOW) {
                    // Load previous turn state
                    previousTurn();

                    // Send Reply to update game
                    mOutbox.sendReply(new Reply(ReplyType.UPDATE, card.getCardNumber(), null, mBlueScore, mRedScore, mBlueTurn));
                }
                if(card.getType() == CardType.BLUE) {
                    // Increment blueScore
                    incrementBlue();

                    // Load previous turn state
                    previousTurn();

                    // Send Reply to update game
                    mOutbox.sendReply(new Reply(ReplyType.UPDATE, card.getCardNumber(), null, mBlueScore, mRedScore, mBlueTurn));
                }
                if(card.getType() == CardType.RED) {
                    // Increment redScore
                    incrementRed();

                    // Load previous turn state
                    previousTurn();

                    // Send Reply to update game
                    mOutbox.sendReply(new Reply(ReplyType.UPDATE, card.getCardNumber(), null, mBlueScore, mRedScore, mBlueTurn));
                }
            }
        }
    }
}
