package view;

import control.Inbox;
import control.Message;
import control.MessageType;
import control.Reply;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import model.CardType;

/**
 * A popup which signals the end of the game. This popup should
 * not be hideable other than quiting or starting a new game.
 * 
 * @author David Boivin (absynth) ID = 40004941
 */
public class EndPopup extends Popup{
	
	Inbox mInbox;
	
	public EndPopup(Reply reply, Inbox in) {
		super();
		
		mInbox = in;
		
		setAutoHide(false);
		
		//create root element
		RootPane root = new RootPane();
		
		//create internal element
		WinnerTitle title = new WinnerTitle(reply.getCurrentTurn());
		MethodDisplay display = new MethodDisplay(reply.getCardType());
		ControlButton newGame = new ControlButton("New Game");
		ControlButton quit = new ControlButton("Quit");
		
		//sets button actions
		newGame.setOnAction(new NewHandler());	//TODO update 
		quit.setOnAction(new QuitHandler());

		//buffer setup
		Pane buff = new Pane();
		HBox.setHgrow(buff, Priority.ALWAYS);
		
		HBox buttonContainer = new HBox();
		buttonContainer.getChildren().addAll(newGame,buff, quit);
		
		root.getChildren().addAll(title, display, buttonContainer);

		getContent().add(root);
	}
	
	private class WinnerTitle extends Label{
		
		public WinnerTitle(boolean isBlueWin) {
			super();
			
			setPadding(Style.POPUP_TITLE_PADDING);
			setBackground((isBlueWin) ? 
					Style.POPUP_START_BACKGROUND_BLUE : Style.POPUP_START_BACKGROUND_RED);
			VBox.setMargin(this, Style.POPUP_TITLE_MARGIN);
			setFont(Style.WINDOW_FONT_HEADER);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
			
			//styling dependent on starting team
			if(isBlueWin) {
				setBackground(Style.POPUP_START_BACKGROUND_BLUE);
				setText("Blue Wins!");
			}else {
				setBackground(Style.POPUP_START_BACKGROUND_RED);
				setText("Red Wins!");
			}
		}
	}
	
	private class MethodDisplay extends Label{
		
		public MethodDisplay(CardType type) {
			super();
			
			setBackground(Style.POPUP_TITLE_BACKGROUND);
			setPadding(Style.POPUP_START_PADDING);	
			VBox.setMargin(this, Style.POPUP_START_MARGIN);
			setFont(Style.WINDOW_FONT_DEFAULT);
			setTextFill(Style.WINDOW_COLOR_TEXT_DARK);
			
			//logic for figuring out who won
			switch(type) {
			case BLUE:
			case RED:
				setText("All field agents found.");
				break;

			case BLACK:
				setText("The assassin has been revealed");
				break;
			case YELLOW:
				//should never happen
				setText("A bystander has won somehow?");
			}
		}
	}
	
	/**
	 * Handler which sends a initiates a New Game popup for a user to
	 * choose AI protocol
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class NewHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			//creates new game
			NewGamePopup ngPopup = new NewGamePopup(mInbox);
			hide();
			ngPopup.show(getScene().getWindow());
		}
				
	}
	
	/**
	 * Handler which will shut the application down.
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class QuitHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			hide();
			System.exit(0);
		}
	}
}

