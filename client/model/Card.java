package model;

import java.util.Observable;

public class Card extends Observable {

    // Static variable for Card
    private static int CARD_COUNT = 0;

    // Attributes
    private int mCardNumber;
    private String mCodeWord;
    private boolean mRevealed;
    private CardType mType;

    // Constructor
    public Card(CardType mType, String mCodeWord) {
        this.mCardNumber = CARD_COUNT;
        CARD_COUNT++;
        this.mRevealed = false;
        this.mType = mType;
    }

    // Getters
    public int getCardNumber() {
        return this.mCardNumber;
    }

    public String getCodeWord() {
        return this.mCodeWord;
    }

    public boolean isRevealed() {
        return this.mRevealed;
    }

    public CardType getType() {
        return this.mType;
    }


    // Methods
    public void revealCard() {
        if(this.mRevealed != true) {
            this.mRevealed = true;
            setChanged();
            notifyObservers();
        }
    }

    public void hideCard() {
        if(this.mRevealed != false) {
            this.mRevealed = false;
            setChanged();
            notifyObservers();
        }
    }
}
