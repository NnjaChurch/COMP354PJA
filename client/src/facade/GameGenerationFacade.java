package facade;

import control.Outbox;
import model.Card;
import model.CardType;
import model.GameBoard;
import model.KeyCard;

import java.util.ArrayList;

public class GameGenerationFacade {

    // Generation Attributes
    private final int KEYCARD_NUMBER = 1;
    private KeyCard keyCard;
    private GameBoard gameBoard;
    private Outbox outbox;

    // Runtime Attributes
    private ArrayList<Card> cards;
    private Card card;
    private boolean startingTurn;
    private final int CARD_TO_GET = 15;

    public GameGenerationFacade() {
        this.keyCard = new KeyCard(KEYCARD_NUMBER);
        this.outbox = new Outbox();
        this.gameBoard = new GameBoard(keyCard, outbox);
    }

    public void start() {
        cards = gameBoard.getCards();
        card = gameBoard.getCard(CARD_TO_GET);
        startingTurn = gameBoard.getCurrentTurn();

        System.out.println("Testing Game Generation...");
        System.out.println("\nCard List:");
        printCards(cards);
        System.out.println("\nGetting Card 15 information:");
        printCard(card);
        System.out.println("\nStarting Turn is Blue: " + startingTurn);
    }

    private void printCards(ArrayList<Card> cards) {
        for(Card card : cards) {
            printCard(card);
        }
    }
    private void printCard(Card card) {
        int cardNumber;
        String cardWord;
        CardType cardType;
        boolean cardState;

        cardNumber = card.getCardNumber();
        cardWord = card.getCodeWord();
        cardType = card.getType();
        cardState = card.isRevealed();
        System.out.println("Card[" + cardNumber + "]: " + cardType + " - Word: " + cardWord + " - Revealed: " + cardState);
    }

    public static class CardFacadeDriver {
        public static void main(String[] args) {
            GameGenerationFacade facade = new GameGenerationFacade();
            facade.start();
        }
    }


}
