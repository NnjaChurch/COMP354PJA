/**
 * Card object that stores data for each Codename
 * Notifies GameObserver.java whenever a card is revealed / hidden via observers
 * Currently only uses the first constructor (second constructor will be for card creation with hints)
 * @author Kevin McAllister (40031326) - Iteration 1
 */
package model;

import java.util.Observable;

public class Card extends Observable {

    // Attributes
    private int mCardNumber;
    private String mCodeWord;
    private String[] mHints;
    private CardType mType;
    private boolean mRevealed;


    // Constructors
    public Card(int cardNumber, String codeWord, CardType type) {
        this.mCardNumber = cardNumber;
        this.mCodeWord = codeWord;
        this.mType = type;
        this.mRevealed = false;

    }

    public Card(int cardNumber, String codeWord, CardType type, String[] hints) {
        this.mCardNumber = cardNumber;
        this.mCodeWord = codeWord;
        this.mHints = new String[hints.length];
        for(int i = 0; i < mHints.length; i++) {
            mHints[i] = hints[i];
        }
        this.mType = type;
        this.mRevealed = false;
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
