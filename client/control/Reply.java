/**
 * Reply object used to send information from the control section to the view section
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package control;

import model.CardType;

public class Reply {

    // Attributes
    private ReplyType mType;
    private int mCardAffected;
    private CardType mCardType;
    private int mBlueScore;
    private int mRedScore;
    private boolean mBlueTurn;


    // Constructor
    public Reply(ReplyType type, int cardAffected, CardType cardType, int blueScore, int redScore, boolean blueTurn) {
        this.mType = type;
        this.mCardAffected = cardAffected;
        this.mCardType = cardType;
        this.mBlueScore = blueScore;
        this.mRedScore = redScore;
        this.mBlueTurn = blueTurn;
    }

    // Getters
    public ReplyType getReplyType() {
        return this.mType;
    }

    public int getCardAffected() {
        return this.mCardAffected;
    }

    public CardType getCardType() {
        return this.mCardType;
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
}
