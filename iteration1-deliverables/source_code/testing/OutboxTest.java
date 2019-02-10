package testing;

import control.Outbox;
import control.Reply;
import control.ReplyType;
import model.CardType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutboxTest {

    @Test
    void getReplyFromEmptyOutbox() {

        // Create Outbox
        Outbox testOutbox = new Outbox();

        // Get Reply from empty outbox (should be null)
        assertEquals(null, testOutbox.getReply());
    }

    @Test
    void sendReplyAndRetrieveIt() {

        // Create Outbox
        Outbox testOutbox = new Outbox();

        // Create Reply
        Reply testReply = new Reply(ReplyType.UPDATE, 1, CardType.BLUE, 1,7, true);

        // Send Reply to Outbox
        testOutbox.sendReply(testReply);

        // Check if Outbox received the Reply
        assertEquals(testReply, testOutbox.getReply());
    }
}