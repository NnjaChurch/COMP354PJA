package control;

import model.GameBoard;
import model.KeyCard;

public class TestClass {

    public static void main(String[] args) {
        // Setup for testing
        KeyCard[] keyCardCollection = generateKeyCards(10);
        Controller control = new Controller(keyCardCollection);
        Message message = new Message();
        message.addObserver(control);
        Inbox inbox = new Inbox(message);

    }

    private static KeyCard[] generateKeyCards(int keyCardCount) {
        KeyCard[] keyCardCollection = new KeyCard[keyCardCount];
        for(int i = 0; i < keyCardCount; i++) {
            keyCardCollection[i] = new KeyCard(i);
        }
        return keyCardCollection;
    }
}
