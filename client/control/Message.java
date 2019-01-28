package control;

public class Message {

    // Attributes
    String mMessage;
    private int mCardAffected;

    // Constructor
    public Message(String message) {
        this.mMessage = message;
    }

    // Methods
    public String toString() {
        return this.mMessage;
    }
}
