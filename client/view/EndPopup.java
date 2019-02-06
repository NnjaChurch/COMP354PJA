package view;

import control.Inbox;
import control.Message;
import control.MessageType;
import control.Reply;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
		VBox root = new VBox();
		
		//root styling
		root.setBackground(Style.POPUP_BACKGROUND);
		root.setPadding(Style.POPUP_PADDING);
		root.setBorder(Style.POPUP_BORDER);
		
		//create internal element
		WinnerTitle title = new WinnerTitle(reply.getCurrentTurn());
		MethodDisplay display = new MethodDisplay(reply.getCardType());
		NewGameButton button = new NewGameButton();
		QuitButton quit = new QuitButton();
		
		HBox buttonContainer = new HBox();
		buttonContainer.getChildren().addAll(button, quit);
		
		root.getChildren().addAll(title, display, buttonContainer);
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
				setText("The assasin has found been revealed");
			case YELLOW:
				//should never happen
				setText("A bystander has won somehow?");
			}
		}
	}
	
	private class NewGameButton extends Button{
		
		public NewGameButton() {
			super("New Game");
			
			//button styling
			setPrefHeight(Style.POPUP_CLOSE_HEIGHT);
			setMinWidth(Style.POPUP_CLOSE_WIDTH);
			setBorder(Style.POPUP_CLOSE_BORDER_DEFAULT);
			setFont(Style.WINDOW_FONT_DEFAULT);
			setBackground(Style.POPUP_CLOSE_BACKGROUND_DEFAULT);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
			
			VBox.setMargin(this, Style.POPUP_CLOSE_MARGIN);
			
			//setting event handlers
			setOnAction(new CloseHandler());
			setOnMouseEntered(new EnterHandler());
			setOnMouseExited(new ExitHandler());
			setOnMousePressed(new PressHandler());
			setOnMouseReleased(new ReleaseHandler());
		}
		
		// ---- style helper methods (for handlers)
		
		private void defaultStyle() {
			setBorder(Style.POPUP_CLOSE_BORDER_DEFAULT);
			setBackground(Style.POPUP_CLOSE_BACKGROUND_DEFAULT);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
		}
		
		private void pressedStyle() {
			setBackground(Style.POPUP_CLOSE_BACKGROUND_PRESSED);
			setBorder(Style.POPUP_CLOSE_BORDER_ENTERED);
			setTextFill(Style.WINDOW_COLOR_TEXT_DARK);
		}
		
		private void enteredStyle() {
			setBackground(Style.POPUP_CLOSE_BACKGROUND_DEFAULT);
			setBorder(Style.POPUP_CLOSE_BORDER_ENTERED);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
		}
		
		
		//closes the popup and sends a New_GAME type Message
		private class CloseHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent event) {
				mInbox.sendMessage(new Message(MessageType.NEW_GAME, -1));
				hide();
			}
			
		}
		
		//handles mouse entered event
		private class EnterHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				enteredStyle();
			}
			
		}
		
		//handles mouse exit event (back to default)
		private class ExitHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				defaultStyle();
			}
			
		}
		
		//handles mouse press event
		private class PressHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				pressedStyle();
				
			}
			
		}
		
		//handles mouse release event (back to default)
		private class ReleaseHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				defaultStyle();
			}	
		}
	}
	
	private class QuitButton extends Button{
		
		public QuitButton() {
			super("Quit");
			
			//button styling
			setPrefHeight(Style.POPUP_CLOSE_HEIGHT);
			setMinWidth(Style.POPUP_CLOSE_WIDTH);
			setBorder(Style.POPUP_CLOSE_BORDER_DEFAULT);
			setFont(Style.WINDOW_FONT_DEFAULT);
			setBackground(Style.POPUP_CLOSE_BACKGROUND_DEFAULT);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
			
			VBox.setMargin(this, Style.POPUP_CLOSE_MARGIN);
			
			//setting event handlers
			setOnAction(new CloseHandler());
			setOnMouseEntered(new EnterHandler());
			setOnMouseExited(new ExitHandler());
			setOnMousePressed(new PressHandler());
			setOnMouseReleased(new ReleaseHandler());
		}
		
		// ---- style helper methods (for handlers)
		
		private void defaultStyle() {
			setBorder(Style.POPUP_CLOSE_BORDER_DEFAULT);
			setBackground(Style.POPUP_CLOSE_BACKGROUND_DEFAULT);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
		}
		
		private void pressedStyle() {
			setBackground(Style.POPUP_CLOSE_BACKGROUND_PRESSED);
			setBorder(Style.POPUP_CLOSE_BORDER_ENTERED);
			setTextFill(Style.WINDOW_COLOR_TEXT_DARK);
		}
		
		private void enteredStyle() {
			setBackground(Style.POPUP_CLOSE_BACKGROUND_DEFAULT);
			setBorder(Style.POPUP_CLOSE_BORDER_ENTERED);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
		}
		
		
		//closes the popup and shuts the program down
		private class CloseHandler implements EventHandler<ActionEvent>{

			@Override
			public void handle(ActionEvent event) {
				hide();
				System.exit(0);
			}
			
		}
		
		//handles mouse entered event
		private class EnterHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				enteredStyle();
			}
			
		}
		
		//handles mouse exit event (back to default)
		private class ExitHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				defaultStyle();
			}
			
		}
		
		//handles mouse press event
		private class PressHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				pressedStyle();
				
			}
			
		}
		
		//handles mouse release event (back to default)
		private class ReleaseHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				defaultStyle();
			}	
		}
	}
}
