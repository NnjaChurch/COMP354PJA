/**
 * Outbox object that receives replies from the GameObserver.java object and notifies the View (GUI)
 * Stores the last received Reply (can be used to check last action for debugging / further implementation)
 * ENTRY POINT: Receives a Reply from the GameObserver by sendReply()
 * EXIT POINT: Notifies the View of a new Reply
 * @author Kevin McAllister (40031326) - Iteration 1
 */
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
