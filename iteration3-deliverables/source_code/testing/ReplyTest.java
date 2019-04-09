package JUnitTest;

import control.Reply;
import control.ReplyType;
import model.CardType;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReplyTest {

    Reply test = new Reply(ReplyType.END, 21, CardType.BLUE, 4, 3, true);


    @Test
    public void getReplyType() {
        assertEquals(ReplyType.END,test.getReplyType());
    }

    @Test
    public void getCardAffected() {
        assertEquals(21, test.getCardAffected());
    }

    @Test
    public void getCardType() {
        assertEquals(CardType.BLUE, test.getCardType());

    }

    @Test
    public void getBlueScore() {
        assertEquals(4, test.getBlueScore());
    }

    @Test
    public void getRedScore() {
        assertEquals(3,test.getRedScore());
    }

    @Test
    public void getCurrentTurn() {
        assertEquals(true,test.getCurrentTurn());
    }

    @Test
    public void getHint() {
        test.setHint("helloWorld");
        assertEquals("helloWorld",test.getHint());
    }
}