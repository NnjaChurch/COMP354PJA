package model;

import java.util.Observable;

public class Card extends Observable {

    // Attributes
    private int mCardNumber;
    private String mCodeWord;
    private CardType mType;
    private boolean mRevealed;

    // Constructors
    public Card(int cardNumber, String codeWord, CardType type) {
        this.mCardNumber = cardNumber;
        this.mCodeWord = codeWord;
        this.mType = type;
        this.mRevealed = false;

    }

    public Card(int cardNumber, CardType type, String codeWord, String[] hints) {
        this.mCardNumber = cardNumber;
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

            // Notify Observer
            setChanged();
            notifyObservers(this);
        }
    }

    public void hideCard() {
        if(this.mRevealed != false) {
            this.mRevealed = false;

            // Notify Observer
            setChanged();
            notifyObservers(this);
        }
    }
}
