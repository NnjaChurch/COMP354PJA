package control;

public class Reply {

    // Attributes
    private ReplyType mType;
    private int mCardAffected;
    private int mBlueScore;
    private int mRedScore;
    private boolean mBlueTurn;


    // Constructor
    public Reply(ReplyType type, int cardAffected, int blueScore, int redScore, boolean blueTurn) {
        this.mType = type;
        this.mCardAffected = cardAffected;
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
