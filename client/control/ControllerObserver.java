package control;

import model.Card;
import model.GameBoard;
import model.KeyCard;

import java.util.Observable;
import java.util.Observer;

public class ControllerObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        // Card will be sent if Message is of type: Select, Next, Undo, Redo
        if(arg instanceof Card) {
            // TODO: CODE TO HANDLE CARD UPDATES ON VIEW
            if(((Card) arg).isRevealed() == true) {
                System.out.println("Card " + ((Card) arg).getCardNumber() + " revealed. It is of type: " + ((Card) arg).getType());
            }
            else {
                System.out.println("Card: " + ((Card) arg).getCardNumber() + " hidden.");
            }
        }
        // GameBoard will be sent if Message is of type: New Game
        if(arg instanceof GameBoard) {
            // TODO: CODE TO HANDLE GAME BOARD UPDATES ON VIEW
            Card[] board = ((GameBoard) arg).getBoard();
            int blueScore = ((GameBoard) arg).getBlueScore();
            int redScore = ((GameBoard) arg).getRedScore();
            boolean blueTurn = ((GameBoard) arg).getCurrentTurn();
            if(blueTurn) {
                System.out.println("Current Turn: Blue");

            }
            else {
                System.out.println("Current Turn: Red");
            }
            System.out.println("Blue Score: " + blueScore + " | Red Score: " + redScore);
            System.out.println("Board State:");
            for(Card card : board) {
                System.out.println(card.toString());
            }
        }
        // KeyCard will be sent if Message is of type: KeyCard
        if(arg instanceof KeyCard) {
            //TODO: CODE TO HANDLE VIEWING KEYCARD
            System.out.println("Keycard Contents: " + arg.toString());
        }
    }
}
