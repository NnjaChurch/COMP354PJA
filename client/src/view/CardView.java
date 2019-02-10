package view;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.CardType;


/**
 * UI element which represents the cards of CodeNames. <br>
 * Only stores card ID (for easy referencing) and the "field agent's" code name
 * for display.<br>
 * Provides method to change it's color.
 * 
 * 
 * @author David Boivin (absynth) ID = 40004941
 */
public class CardView extends Button {
	
	private final int mID;
	
	public CardView(int id, String word) {
		super(word);
		mID = id;
		
		//setting card style
		setMinSize(Style.CARD_WIDTH, Style.CARD_HEIGHT);
		setPrefSize(Style.CARD_WIDTH, Style.CARD_HEIGHT);
		setBorder(Style.CARD_BORDER_DEFAULT);
		setFont(Style.WINDOW_FONT_DEFAULT);
		
		//event handlers
		setOnMouseEntered(new EnterHandler());
		setOnMouseExited(new ExitHandler());
		
		//starting hidden
		changeColor(null);
	}
	
	public int getCardID() {
		return mID;
	}
	
	/**
	 * Method designed to easily change the color of the card based on the
	 * predefined typed of cards.
	 * 
	 * @param type Changes the color of based on predefined CardType. 
	 * 			if <code>null</code>, then the card will be "hidden".
	 */
	public void changeColor(CardType type) {
		//null handling
		if(type == null) {
			setBackground(Style.CARD_BACKGROUND_HIDDEN);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
			return;
		}
		
		switch(type) {
		
		case YELLOW :
			setBackground(Style.CARD_BACKGROUND_YELLOW);
			setTextFill(Style.WINDOW_COLOR_TEXT_DARK);
			break;
			
		case BLACK:
			setBackground(Style.CARD_BACKGROUND_BLACK);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
			break;
			
		case BLUE:
			setBackground(Style.CARD_BACKGROUND_BLUE);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
			break;
			
		case RED:
			setBackground(Style.CARD_BACKGROUND_RED);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
			break;
		}
	}	
	
	// Mouse action handlers ---------------------
	
	/**
	 * Handles when the mouse enters into the card
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class EnterHandler implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent event) {
			setBorder(Style.CARD_BORDER_ENTERED);
		}
		
	} 
	
	/**
	 * Handles when the mouse leaves the card
	 * (returns to default)
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class ExitHandler implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {
			setBorder(Style.CARD_BORDER_DEFAULT);
		}
	}
}
