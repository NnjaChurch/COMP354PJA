import control.Controller;
import control.Inbox;
import control.Outbox;
import model.KeyCard;

public class TestClass {

    public static void main(String[] args) {
        // Setup for testing
        KeyCard[] keyCardCollection = generateKeyCards(10);
        Inbox inbox = new Inbox();
        Outbox outbox = new Outbox();
        Controller control = new Controller(keyCardCollection, outbox);
    }

    private static KeyCard[] generateKeyCards(int keyCardCount) {
        KeyCard[] keyCardCollection = new KeyCard[keyCardCount];
        for(int i = 0; i < keyCardCount; i++) {
            keyCardCollection[i] = new KeyCard(i);
        }
        return keyCardCollection;
    }
}
