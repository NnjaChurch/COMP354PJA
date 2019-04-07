package JUnitTest;

import control.LogMessage;
import control.MessageType;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogMessageTest {

    @Test
    public void getType() { //test that the getter works properly
        LogMessage test= new LogMessage(MessageType.NEW_GAME_B_NEXT_R_NEXT,21,"HiYouBeautiful");
        assertEquals(test.getType(),MessageType.NEW_GAME_B_NEXT_R_NEXT);
    }

    @Test
    public void getCardAffected() {
        LogMessage test= new LogMessage(MessageType.NEW_GAME_B_NEXT_R_NEXT,21,"HiYouBeautiful");
        assertEquals(test.getCardAffected(),21);

    }

    @Test
    public void getHint() {
        LogMessage test= new LogMessage(MessageType.NEW_GAME_B_NEXT_R_NEXT,21,"HiYouBeautiful");
        assertEquals(test.getHint(),"HiYouBeautiful");
    }
}