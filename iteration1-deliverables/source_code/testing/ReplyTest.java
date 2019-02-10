package testing;

import control.Reply;
import control.ReplyType;
import model.CardType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplyTest {

    @Test
    void createCustomReplyAndGetInformation() {

        // Create Custom Reply
        Reply testReply = new Reply(ReplyType.UPDATE, 1, CardType.YELLOW, 8, 9, false);

        // Get Custom Information
        assertEquals(ReplyType.UPDATE, testReply.getReplyType());
        assertEquals(1, testReply.getCardAffected());
        assertEquals(CardType.YELLOW, testReply.getCardType());
        assertEquals(8, testReply.getBlueScore());
        assertEquals(9, testReply.getRedScore());
        assertEquals(false, testReply.getCurrentTurn());
    }
}