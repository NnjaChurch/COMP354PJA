package control;

import java.util.Observable;

public class Message extends Observable {

    // Attributes
    private MessageType mType;
    private int mCardAffected;

    // Constructor
    public Message() {
        this.mType = null;
        this.mCardAffected = -1;
    }

    public Message(MessageType type, int cardAffected) {
        this.mType = type;
        this.mCardAffected = cardAffected;
    }

    // Getters
    public MessageType getMessageType() {
        return this.mType;
    }

    public int getCardAffected() {
        return this.mCardAffected;
    }

    // Setters
    public void setMessage(MessageType type, int cardAffected) {
        // Update Variables
        this.mType = type;
        this.mCardAffected = cardAffected;
        // Notify Observers
        setChanged();
        notifyObservers(this);
    }
}
