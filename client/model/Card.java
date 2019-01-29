package model;

public class Card {

    // Attributes
    private int mCardNumber;
    private String mCodeWord;
    private boolean mRevealed;
    private CardType mType;

    // Constructor
    public Card(int cardNumber, CardType mType, String mCodeWord) {
        this.mCardNumber = cardNumber;
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

    // Setters
    public void setCardNumber(int cardNumber) {
        this.mCardNumber = cardNumber;
    }

    public void setRevealed(boolean revealed) {
        this.mRevealed = revealed;
    }

    // Methods
    public Card clone() {
        Card newCard = new Card(this.mCardNumber, this.mType, this.mCodeWord);
        newCard.setRevealed(this.mRevealed);
        return newCard;
    }

    public String toString() {
        return(this.getCardNumber() + " | " + this.getType().toString() + " | Revealed: " + this.isRevealed());
    }

    public void revealCard() {
        if(this.mRevealed != true) {
            this.mRevealed = true;
        }
    }

    public void hideCard() {
        if(this.mRevealed != false) {
            this.mRevealed = false;
        }
    }
}
