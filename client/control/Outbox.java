package control;

import java.util.Observable;

public class Outbox extends Observable {

    // Attributes
    private Reply mReply;

    // Constructors
    public Outbox() {
        this.mReply = null;
    }

    // Getters
    public Reply getReply() {
        return this.mReply;
    }

    // Methods
    public void sendReply(Reply r) {

        // Update the Reply
        this.mReply = r;

        // Notify Observer
        setChanged();
        notifyObservers(this.mReply);
    }


}
