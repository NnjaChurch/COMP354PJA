package JUnitTest;

import control.Outbox;
import control.Reply;
import control.ReplyType;
import model.CardType;
import org.junit.Test;

import static org.junit.Assert.*;

public class OutboxTest {

    @Test
    public void getReply() { //test the setter and getter of the Outbox
        Outbox test = new Outbox();
        test.updateHint("HelloWorld");
        assertEquals("HelloWorld",test.getHint());
    }

    @Test
    public void getHint() {
        Outbox test = new Outbox();
        Reply attribute = new Reply(ReplyType.END,21, CardType.BLUE,3,2,true);
        test.sendReply(attribute);
        assertEquals(attribute,test.getReply());
    }
}