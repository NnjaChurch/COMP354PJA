import control.Controller;
import control.Inbox;
import control.Outbox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Card;
import model.KeyCard;
import view.BoardPane;
import view.Style;

import java.util.ArrayList;

public class GameDriver extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        startGame(primaryStage);
    }

    private void startGame(Stage mStage) {
        try {

            // Setup Control and Model Environment
            KeyCard[] keyCardCollection = generateKeyCards(10);
            Inbox inbox = new Inbox();
            Outbox outbox = new Outbox();
            Controller controller = new Controller(keyCardCollection, inbox, outbox);

            // Bind Controller to Inbox
            inbox.addObserver(controller);

            // Start New Game
            controller.startNewGame(true, true);

            // Set Stage and Show
            mStage.setScene(controller.getScene());
            mStage.setTitle("Codenames PJA");
            mStage.show();
        }
        catch(Exception e) {
            System.out.println("Could not initialize game state.");
            e.printStackTrace();
        }

    }

    private static KeyCard[] generateKeyCards(int keyCardCount) {
        KeyCard[] keyCardCollection = new KeyCard[keyCardCount];
        for(int i = 0; i < keyCardCount; i++) {
            keyCardCollection[i] = new KeyCard(i);
        }
        return keyCardCollection;
    }
}
