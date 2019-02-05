import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.BoardPane;
import view.Style;

public class Main extends Application {
	
	private Scene mScene;
	
	@Override
	public void start(Stage primaryStage) {
		
		
		try {
			
			Inbox inbox = new Inbox();
			KeyCard keycard = new KeyCard();
			
			//TODO generate card list
			ArrayList<Card> cardList = new ArrayList<Card>();
			for(int i = 0; i < 25; i++) {
				cardList.add(new Card(i, codeword, k.getCardType(i)));
			}
			
			//creates the board
			BoardPane viewRoot = new BoardPane(cardList, keycard, inbox);
			
			//links the board to the window
			mScene scene = new Scene(viewRoot, Style.WINDOW_SIZE_WIDTH , Style.WINDOW_SIZE_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			mStage.setScene(scene);
			mStage.setTitle("CodeNames");
			mStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void changeBoard(BoardPane root) {
		mScene.setRoot(root);
	}
}
