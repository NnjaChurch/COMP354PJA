/**
 * Inbox object that receives messages from the View (GUI) and notifies the Controller.java object
 * Stores last received message (can be used to check last action sent for debugging / further implementation)
 * ENTRY POINT: Receives a message from the View by sendMessage()
 * EXIT POINT: Notifies the Controller.java of a new message.
 * @author Kevin McAllister (40031326) - Iteration 1
 */
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
