package JUnitTest;

import model.CardType;
import model.KeyCard;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class KeyCardTest {@Test
void getBlueFirst() {
}



    @Test
    void getKeyContent() {
    }
    @Test
    public void rightNumberOfBlueCard() {
        KeyCard test = new KeyCard(123);
        CardType[] arr= test.getKeyContent();
        int countOfBlue=0;

        for(CardType x: arr){
            if (x==CardType.BLUE)
                countOfBlue++;

        }

        boolean goodNumber= countOfBlue==9;
        assertEquals(countOfBlue,8);

    }

    public void rightNumberOfRedCard() {
        KeyCard test = new KeyCard(123);
        CardType[] arr= test.getKeyContent();
        int countOfRed=0;

        for(CardType x: arr){
            if (x==CardType.RED)
                countOfRed++;

        }

        boolean goodNumber= countOfRed==8;
        assertEquals(true,goodNumber);

    }
    public void rightNumberOfBlackCard() {
        KeyCard test = new KeyCard(123);
        CardType[] arr= test.getKeyContent();
        int countOfBlack=0;

        for(CardType x: arr){
            if (x==CardType.BLACK)
                countOfBlack++;

        }

        boolean goodNumber= countOfBlack==1;
        assertEquals(true,goodNumber);

    }
    public void rightNumberOfYellowCard() {
        KeyCard test = new KeyCard(123);
        CardType[] arr= test.getKeyContent();
        int countOfYellow=0;

        for(CardType x: arr){
            if (x==CardType.YELLOW)
                countOfYellow++;

        }

        boolean goodNumber= countOfYellow==7;
        assertEquals(true,goodNumber);

    }

}