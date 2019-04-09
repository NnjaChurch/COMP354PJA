package JUnitTest;

import control.Message;
import control.MessageType;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {


    @Test
    public void getMessageType() {
        Message test = new Message(MessageType.NEW_GAME_B_NEXT_R_RANDOM,14);
        assertEquals(MessageType.NEW_GAME_B_NEXT_R_RANDOM,test.getMessageType());

    }

    @Test
    public void getCardAffected() {
        Message test = new Message(MessageType.NEW_GAME_B_NEXT_R_RANDOM,14);
        assertEquals(14,test.getCardAffected());
    }
}