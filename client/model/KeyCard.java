package model;

import java.util.Arrays;
import java.util.Random;

public class KeyCard {

    // Global Variables for KeyCard
    private static int KEYCARD_COUNT = 1;

    // Attributes
    private int mKeyCardNumber;
    private CardType[] mKeyContent;
    private boolean mBlueFirst;

    // Constructor

    public KeyCard() {
        this.mKeyCardNumber = KEYCARD_COUNT;
        KEYCARD_COUNT++;
        this.mBlueFirst = pickFirstTeam();
        this.mKeyContent = generateKeyCard();
    }

    // Getters
    public boolean getBlueFirst() {
        return this.mBlueFirst;
    }

    public CardType getCardType(int n) {
        return mKeyContent[n];
    }

    // Methods

    private boolean pickFirstTeam() {
        Random r = new Random(this.mKeyCardNumber);
        return r.nextBoolean();
    }

    private CardType[] generateKeyCard() {
        CardType[] keyCardContent = new CardType[25];
        Random r = new Random();
        boolean placed;
        CardType temp;


        // Counters
        int n;
        int redLeft;
        int blueLeft;
        int assassinLeft = 1;

        if(this.mBlueFirst) {
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
                n = r.nextInt(25);
                if (keyCardContent[n] == null) {
                    // Loop to try different CardTypes
                    while(!placed) {
                        temp = pickCardType(r);
                        if(temp == CardType.ASSASSIN && assassinLeft != 0) {
                            keyCardContent[n] = temp;
                            assassinLeft--;
                            placed = true;
                            //System.out.println(temp.toString() + " placed in position: [" + row + "][" + col + "]");
                        }
                        if(temp == CardType.BLUE && blueLeft != 0) {
                            keyCardContent[n] = temp;
                            blueLeft--;
                            placed = true;
                            //System.out.println(temp.toString() + " placed in position: [" + row + "][" + col + "]");
                        }
                        if(temp == CardType.RED && redLeft != 0) {
                            keyCardContent[n] = temp;
                            redLeft--;
                            placed = true;
                            //System.out.println(temp.toString() + " placed in position: [" + row + "][" + col + "]");
                        }
                        if(assassinLeft == 0 && blueLeft == 0 && redLeft == 0) {
                            keyCardContent[n] = CardType.BYSTANDER;
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
