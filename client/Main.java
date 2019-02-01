import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.BoardPane;
import view.Style;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		
		try {
			//creates the board
			BoardPane viewRoot = new BoardPane(); //TODO swap test constructor with actual constructor 
			
			//links the board to the window
			Scene scene = new Scene(viewRoot, Style.WINDOW_SIZE_WIDTH , Style.WINDOW_SIZE_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("CodeNames");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
