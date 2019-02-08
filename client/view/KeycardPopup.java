package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import model.CardType;
import model.KeyCard;

/**
 * Popup which translates and displays the keycard for the
 * user. <br><br>
 * 
 * NOTE: Uses the same colors as the cards (for consistency)<br>
 * NOTE: uses BoardPane.COLUMN_NUMBER to gereate the proper grid.
 *  
 * @author David Boivin (absynth) ID = 40004941
 */
public class KeycardPopup extends Popup{
	
	public KeycardPopup(KeyCard k) {
		super();
		
		//popup styling (and settings)
		setAutoHide(true);
		
		//create root element
		VBox root = new VBox();
		
		//root styling
		root.setBackground(Style.POPUP_BACKGROUND);
		root.setPadding(Style.POPUP_PADDING);
		root.setBorder(Style.POPUP_BORDER);
		
		//create internal elements
		PopupTitle title = new PopupTitle();
		KeycardPane grid = new KeycardPane(k.getKeyContent());
		StartLabel start = new StartLabel(k.getBlueFirst());
		ControlButton close = new ControlButton("Close");
		
		//sets button action
		close.setOnAction(new CloseHandler());
		
		
		//sets all the components of the root 
		root.getChildren().addAll(title, start, grid, close);
		
		//sets the root of the popup to the populated and stylized root element
		getContent().add(root);
		
	}
	
	// ------------------- Inner elements -------------------------
	
	/**
	 * Styling class for title element
	 * 
	 * @author David Boivin (absynth)
	 *
	 */
	private class PopupTitle extends Label{
		
		private PopupTitle() {
			super("KeyCard");
			
			setPadding(Style.POPUP_TITLE_PADDING);
			setBackground(Style.POPUP_TITLE_BACKGROUND);
			VBox.setMargin(this, Style.POPUP_TITLE_MARGIN);
			
			
			setFont(Style.WINDOW_FONT_HEADER);
			setTextFill(Style.WINDOW_COLOR_TEXT_DARK);
		}
	}
	

	/**
	 * Grid which displays the keycard cardtypes.
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	public class KeycardPane extends GridPane {
		
		public KeycardPane(CardType[] ct) {
			super();
			
			//grid styling
			setPadding(Style.POPUP_GRID_PADDING);
			setBorder(Style.POPUP_GRID_BORDER);
			setHgap(Style.POPUP_GRID_GAP_H);
			setVgap(Style.POPUP_GRID_GAP_V);
			
			VBox.setMargin(this, Style.POPUP_GRID_MARGIN);
			
			
			//create colored regions and add them to the pane
			for(int i = 0; i < ct.length; i++) {
				KeycardElement e = new KeycardElement(getCardColor(ct[i]));
				GridPane.setConstraints(e , i % BoardPane.COLUMN_NUMBER, i / BoardPane.COLUMN_NUMBER);
				getChildren().add(e);
			}
		}
		
		/**
		 * private helper method to translate the card type to colors correctly
		 * 
		 */
		private Color getCardColor(CardType ct) {
			if(ct == null) {
				return Style.WINDOW_COLOR_CARD_HIDDEN;	//should never happen but just in case
			}
			
			switch(ct) {
			case BLACK:
				return Style.WINDOW_COLOR_CARD_BLACK;
				
			case YELLOW:
				return Style.WINDOW_COLOR_CARD_YELLOW;
				
			case RED:
				return Style.WINDOW_COLOR_CARD_RED;
				
			case BLUE:
				return Style.WINDOW_COLOR_CARD_BLUE;
				
			default:
				return Style.WINDOW_COLOR_CARD_HIDDEN;	//again should theoretically never happen
			}
		}
		
		// ----- Keycard Squares --
		
		private class KeycardElement extends Rectangle {
			
			public KeycardElement(Color fill) {
				super();
				
				setHeight(Style.POPUP_GRID_ELEMENT_HEIGHT);
				setWidth(Style.POPUP_GRID_ELEMENT_WIDTH);
				setArcWidth(Style.POPUP_GRID_ELEMENT_CORNER);
				setArcHeight(Style.POPUP_GRID_ELEMENT_CORNER);
				setFill(fill);
			}
		}
	}
	
	/**
	 * Label to show the score
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 *
	 */
	private class StartLabel extends Label {
		
		private StartLabel(boolean isBlue) {
			super();
			
			setPadding(Style.POPUP_START_PADDING);	
			VBox.setMargin(this, Style.POPUP_START_MARGIN);
			setFont(Style.WINDOW_FONT_DEFAULT);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
			
			//styling dependent on starting team
			if(isBlue) {
				setBackground(Style.POPUP_START_BACKGROUND_BLUE);
				setText("Blue Starts");
			}else {
				setBackground(Style.POPUP_START_BACKGROUND_RED);
				setText("Red Starts");
			}
		}
	}
	
	
	/**
	 * Handler which hides the keycard popup, but does not destroy it
	 * (for further use).
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 *
	 */
	private class CloseHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			hide();
		}
		
	}
}
