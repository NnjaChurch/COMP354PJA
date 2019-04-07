package JUnitTest;

import control.Inbox;
import control.Message;
import control.MessageType;
import org.junit.Test;

import static org.junit.Assert.*;

public class InboxTest {

    @Test
    public void getMessage() { //make sure that inbox class send message and store them correctly
        Inbox test= new Inbox();
        Message attribute = new Message(MessageType.NEW_GAME_B_NEXT_R_NEXT,9);
        test.sendMessage(attribute);
        assertEquals(test.getMessage(),attribute);

    }

}