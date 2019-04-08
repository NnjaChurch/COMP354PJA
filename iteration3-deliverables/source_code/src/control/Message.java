/**
 * Message object used to send information to the control section from the view section
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package control;

public class Message {

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
}
