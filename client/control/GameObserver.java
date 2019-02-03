package control;

import model.Card;
import model.CardType;
import model.KeyCard;

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
        initializeGame(keyCard);
    }

    // Getters
    public int getBlueScore() {
        return this.mBlueScore;
    }

    public int getRedScore() {
        return this.mRedScore;
    }

    public boolean getCurrentTurn() {
        return this.mBlueTurn;
    }

    //Methods
    private void initializeGame(KeyCard keyCard) {
        if(keyCard.getBlueFirst() == true) {
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

    public void incrementBlue() {
      mBlueScore++;
    }

    public void incrementRed() {
        mRedScore++;
    }

    public void decrementBlue() {
        mBlueScore--;
    }

    public void decrementRed() {
        mRedScore--;
    }

    public void sameTurn() {
        this.mTurnStack.push(mBlueTurn);
    }

    public void nextTurn() {
        this.mTurnStack.push(mBlueTurn);
        this.mBlueTurn = !mBlueTurn;
    }

    public void previousTurn() {
        boolean previousTurn = this.mTurnStack.pop();
        this.mBlueTurn = previousTurn;
    }

    public void replyToOutbox(Card card) {
        mOutbox.sendReply(new Reply(ReplyType.UPDATE, card.getCardNumber(), mBlueScore, mRedScore, mBlueTurn));
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
                    mOutbox.sendReply(new Reply(ReplyType.END, card.getCardNumber(), mBlueScore, mRedScore, mBlueTurn));
                }
                // If Card was Bystander
                if(card.getType() == CardType.YELLOW) {
                    // Swap turn to other team
                    nextTurn();

                    // Send Reply to update game
                    replyToOutbox(card);
                }
                // If Card was Blue
                if(card.getType() == CardType.BLUE) {
                    // Decrement blueScore
                    decrementBlue();

                    // If currently Blue turn
                    if(mBlueTurn == true) {
                        // Log turn state
                        sameTurn();

                        // Send Reply to update game
                        replyToOutbox(card);
                    }
                    // If currently Red turn
                    else {
                        // Swap turn to other team
                        nextTurn();

                        // Send Reply to update game
                        replyToOutbox(card);
                    }
                }
                // If Card was Red
                if(card.getType() == CardType.RED) {
                    // Decrement redScore
                    decrementRed();

                    // If currently Blue turn
                    if(mBlueTurn == true) {
                        // Swap to other team
                        nextTurn();

                        // Send Reply to update game
                        replyToOutbox(card);
                    }
                    // If currently Red turn
                    else {
                        // Log turn state
                        sameTurn();

                        // Send Reply to update game
                        replyToOutbox(card);
                    }
                }
            }
            // If Card was hidden (Undo)
            else {
                if(card.getType() == CardType.YELLOW) {
                    // Load previous turn state
                    previousTurn();

                    // Send Reply to update game
                    replyToOutbox(card);
                }
                if(card.getType() == CardType.BLUE) {
                    // Increment blueScore
                    incrementBlue();

                    // Load previous turn state
                    previousTurn();

                    // Send Reply to update game
                    replyToOutbox(card);
                }
                if(card.getType() == CardType.RED) {
                    // Increment redScore
                    incrementRed();

                    // Load previous turn state
                    previousTurn();

                    // Send Reply to update game
                    replyToOutbox(card);
                }
            }
        }
    }
}
