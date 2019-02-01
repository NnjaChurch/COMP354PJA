package control;

import java.util.Observable;

public class Inbox extends Observable {

    // Attributes
    private Message mMessage;

    // Constructors
    public Inbox() {
        this.mMessage = null;
    }

    // Getters
    public Message getMessage() {
        return this.mMessage;
    }

    // Methods
    public void sendMessage(Message m) {

        // Update the Message
        this.mMessage = m;

        // Notify Observer
        setChanged();
        notifyObservers(this.mMessage);
    }
}
