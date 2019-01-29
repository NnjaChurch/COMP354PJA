package control;

public class CardMessage extends Message {

    private int mCardAffected;

    public CardMessage(String message, int cardAffected) {
        super(message);
        this.mCardAffected = cardAffected;
    }

    // Getters
    public int getCardAffected() {
        return this.mCardAffected;
    }

    // Methods
    public String toString() {
        return(mMessage + " for card " + mCardAffected);
    }

}
