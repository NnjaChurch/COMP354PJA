package model;

import java.util.Arrays;
import java.util.Random;

public class KeyCard {

    // Global Variables for KeyCard
    private static int KEYCARD_COUNT = 1;

    // Attributes
    private int keyCardNumber;
    private CardType[][] keyContent;
    private boolean blueFirst;

    // Constructor

    public KeyCard() {
        this.keyCardNumber = KEYCARD_COUNT;
        KEYCARD_COUNT++;
        this.blueFirst = pickFirstTeam();
        this.keyContent = generateKeyCard();
    }

    // Methods

    private boolean pickFirstTeam() {
        Random r = new Random(this.keyCardNumber);
        return r.nextBoolean();
    }

    private CardType[][] generateKeyCard() {
        CardType[][] keyCardContent = new CardType[5][5];
        Random r = new Random();
        boolean placed;
        CardType temp;


        // Counters
        int row;
        int col;
        int redLeft;
        int blueLeft;
        int assassinLeft = 1;

        if(this.blueFirst) {
            redLeft = 8;
            blueLeft = 9;
        }
        else {
            redLeft = 9;
            blueLeft = 8;
        }

        for(int i = 0; i < 25; i++) {
            placed = false;
            // Loop to fill random positions
            while(!placed) {
                row = r.nextInt(5);
                col = r.nextInt(5);
                if (keyCardContent[row][col] == null) {
                    // Loop to try different CardTypes
                    while(!placed) {
                        temp = pickCardType(r);
                        if(temp == CardType.ASSASSIN && assassinLeft != 0) {
                            keyCardContent[row][col] = temp;
                            assassinLeft--;
                            placed = true;
                            //System.out.println(temp.toString() + " placed in position: [" + row + "][" + col + "]");
                        }
                        if(temp == CardType.BLUE && blueLeft != 0) {
                            keyCardContent[row][col] = temp;
                            blueLeft--;
                            placed = true;
                            //System.out.println(temp.toString() + " placed in position: [" + row + "][" + col + "]");
                        }
                        if(temp == CardType.RED && redLeft != 0) {
                            keyCardContent[row][col] = temp;
                            redLeft--;
                            placed = true;
                            //System.out.println(temp.toString() + " placed in position: [" + row + "][" + col + "]");
                        }
                        if(assassinLeft == 0 && blueLeft == 0 && redLeft == 0) {
                            keyCardContent[row][col] = CardType.BYSTANDER;
                            placed = true;
                            //System.out.println("Bystander placed in position: [" + row + "][" + col + "]");
                        }
                    }
                }
            }

        }
        System.out.println(Arrays.deepToString(keyCardContent));
        return keyCardContent;
    }

    private CardType pickCardType(Random r) {
        return CardType.values()[r.nextInt(CardType.values().length)];
    }
}
