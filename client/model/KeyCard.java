package model;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

public class KeyCard {

    // Attributes
    private int mKeyCardNumber;
    private CardType[] mKeyContent;
    private boolean mBlueFirst;

    // Constructor
    public KeyCard() {
        this.mKeyCardNumber = 0;
        this.mKeyContent = null;
        this.mBlueFirst = false;
    }

    public KeyCard(int keyCardNumber) {
        this.mKeyCardNumber = keyCardNumber;
        this.mBlueFirst = pickFirstTeam();
        this.mKeyContent = generateKeyCard();
        System.out.println(this.toString() + this.mBlueFirst);
    }

    // Getters
    public boolean getBlueFirst() {
        return this.mBlueFirst;
    }

    public CardType getCardType(int n) {
        return mKeyContent[n];
    }

    public CardType[] getKeyContent() {
        return this.mKeyContent.clone();
    }

    // Setters
    public void setKeyCardNumber(int keyCardNumber) {
        this.mKeyCardNumber = keyCardNumber;
    }

    public void setBlueFirst(boolean blueFirst) {
        this.mBlueFirst = blueFirst;
    }

    public void setKeyContent(CardType[] keyContent) {
        this.mKeyContent = keyContent.clone();
    }

    // Methods
    public KeyCard clone() {
        KeyCard newCard = new KeyCard();
        newCard.setBlueFirst(this.getBlueFirst());
        newCard.setKeyContent(this.getKeyContent());
        return newCard;
    }

    public String toString() {
        return Arrays.deepToString(this.mKeyContent);
    }

    // Methods to Generate KeyCards (Can be moved if needed)
    private boolean pickFirstTeam() {
        Random r = new Random();
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
                        if(temp == CardType.BLACK && assassinLeft != 0) {
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
                            keyCardContent[n] = CardType.YELLOW;
                            placed = true;
                            //System.out.println("Bystander placed in position: [" + row + "][" + col + "]");
                        }
                    }
                }
            }

        }
        return keyCardContent;
    }

    private CardType pickCardType(@NotNull Random r) {
        return CardType.values()[r.nextInt(CardType.values().length)];
    }
}
