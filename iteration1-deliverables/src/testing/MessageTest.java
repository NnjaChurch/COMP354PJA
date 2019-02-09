package testing;

import control.Message;
import control.MessageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void getDefaultMessageTypeCardAffected() {

        // Create Message
        Message testMessage = new Message();

        // Get Default Message Type
        assertEquals(null, testMessage.getMessageType());

        // Get Default Card Affected
        assertEquals(-1, testMessage.getCardAffected());
    }

    @Test
    void createCustomMessageAndGetInfo() {

        // Create Custom Message
        Message testMessage = new Message(MessageType.SELECT, 8);

        // Get Custom Message Type
        assertEquals(MessageType.SELECT, testMessage.getMessageType());

        // Get Custom Card Affected
        assertEquals(8, testMessage.getCardAffected());
    }

}