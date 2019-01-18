package model;

public class Card {

    // Global Variable for Card
    private static int CARD_COUNT = 1;

    // Attributes
    private int cardNumber;
    private String codeWord;
    private boolean revealed;
    private CardType type;

    // Constructor
    public Card(CardType type) {
        this.cardNumber = CARD_COUNT;
        CARD_COUNT++;
        this.codeWord = generateCodeWord();
        this.revealed = false;
        this.type = type;
    }

    // Getters
    public int getCardNumber() {
        return this.cardNumber;
    }

    public String getCodeWord() {
        return this.codeWord;
    }

    public boolean isRevealed() {
        return this.revealed;
    }

    public CardType getType() {
        return this.type;
    }

    // Methods
    private static String generateCodeWord() {
        String codeWord = "Temp Word";
        // TODO: INSERT CODE TO GET CODEWORD FROM WORD DATABASE
        return codeWord;
    }

    private void revealCard() {
        if(this.revealed == false) {
            this.revealed = true;
        }
    }
}
