package control;

public class Message {

    // Attributes
    private int mCardAffected;

    // Constructor
    public Message(int cardAffected) {
        this.mCardAffected = cardAffected;
    }

    // Getters
    public int getCardAffected() {
        return this.mCardAffected;
    }

    // Methods
    public String toString() {
        return ("This action affects Card: " + mCardAffected);
    }
}
