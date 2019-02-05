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
            Controller controller = new Controller(keyCardCollection, outbox);

            // Grab KeyCard and CardList from Model
            KeyCard keyCard = controller.getKeyCard();
            System.out.println(keyCard.toString());
            ArrayList<Card> cards = controller.getCardList();

            // Benchmark
            System.out.println("Control and Model OK!");

            // Setup View Environment
            BoardPane viewRoot = new BoardPane(cards, keyCard, inbox);

            // Link the board to the window
            Scene mScene = new Scene(viewRoot, Style.WINDOW_SIZE_WIDTH, Style.WINDOW_SIZE_HEIGHT);
            // mScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            // Set Stage and Show
            mStage.setScene(mScene);
            mStage.setTitle("Codenames PJA");
            mStage.show();

            // Benchmark
            System.out.println("View OK!");

            // Link Observers

            // Controller to Inbox
            inbox.addObserver(controller);

            // BoardPane to Outbox
            outbox.addObserver(viewRoot);

            //Benchmark
            System.out.println("Observers OK!");
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
