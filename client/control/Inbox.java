package control;

public class Inbox {

    // Attributes
    Message mMessage;

    // Constructors
    public Inbox(Message message) {
        this.mMessage = message;
    }

    // Getters
    public Message getMessage() {
        return this.mMessage;
    }

    // Methods
    public void updateMessage(MessageType type, int cardAffected) {
        mMessage.setMessage(type, cardAffected);
    }
}
