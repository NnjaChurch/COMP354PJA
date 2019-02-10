package testing;

import control.Inbox;
import control.Message;
import control.MessageType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InboxTest {

    @Test
    void getMessageFromEmptyInbox() {

        // Create Inbox
        Inbox testInbox = new Inbox();

        // Get Message from empty inbox (should be null)
        assertEquals(null, testInbox.getMessage());
    }

    void sendMessageAndRetrieveIt() {

        // Create Inbox
        Inbox testInbox = new Inbox();

        // Create Message
        Message testMessage = new Message(MessageType.SELECT, 1);

        // Send a Message to the Inbox
        testInbox.sendMessage(testMessage);

        // Check if inbox received the Message
        assertEquals(testMessage, (testInbox.getMessage()));

    }
}