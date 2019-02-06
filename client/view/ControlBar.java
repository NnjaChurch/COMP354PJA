package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * A container which has all the user control buttons for the program.
 * Listeners must be added through linking methods, default functionality won't
 * result in any change in the program.
 * 
 * @author David Boivin (absynth) ID = 40004941
 */
public class ControlBar extends HBox{

	private final ControlButton mNew;
	private final ControlButton mNext;
	private final ControlButton mUndo;
	private final ControlButton mRedo;
	private final ControlButton mKeycard;
	
	/**
	 * Constructor which creates inner elements and set the styling for
	 * the control bar.
	 */
	public ControlBar() {
		super();
		
		//control bar styling
		setPrefHeight(Style.CONTROL_HEIGHT);
		setBackground(Style.CONTROL_BACKGROUND);
		setBorder(Style.CONTROL_BORDER);
		
		
		//creates inner elements (control buttons)
		mNew = new ControlButton("New Game");
		mNext = new ControlButton("Next Move");
		mUndo = new ControlButton ("Undo");
		mRedo = new ControlButton("Redo");
		mKeycard = new ControlButton("View Keycard");
		
		//buffer setup
		Pane buff1 = new Pane();
		Pane buff2 = new Pane();
		HBox.setHgrow(buff1, Priority.ALWAYS);
		HBox.setHgrow(buff2, Priority.ALWAYS);
		
		this.getChildren().addAll(mNext, buff1, mUndo, mRedo, buff2, mKeycard, mNew);
		
	}
	
	
	// ------------------- Handler Linking methods -----------------
	
	public void setNewGameHandler(EventHandler<ActionEvent> h) {
		mNew.setOnAction(h);
	}
	
	public void setNextHandler(EventHandler<ActionEvent> h) {
		mNext.setOnAction(h);
	}
	
	public void setUndoHandler(EventHandler<ActionEvent> h) {
		mUndo.setOnAction(h);
	}
	
	public void setRedoHandler(EventHandler<ActionEvent> h ) {
		mRedo.setOnAction(h);
	}	
	
	public void setKeycardHandler(EventHandler<ActionEvent> h) {
		mKeycard.setOnAction(h);
	}
	
	// ------------------ Private Inner Elements ---------------------
	
	/**
	 * Private inner class designed to allow for consistency across all buttons in control bar
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class ControlButton extends Button{
		
		public ControlButton(String text) {
			super(text);
			
			//control button styling
			setPrefHeight(Style.CONTROL_ELEMENT_HEIGHT);
			setPrefWidth(Style.CONTROL_ELEMENT_WIDTH);
			setMargin(this, Style.CONTROL_ELEMENT_MARGIN);
			setFont(Style.WINDOW_FONT_DEFAULT);
			defaultStyle();
			
			//default mouse event handler
			setOnMousePressed(new PressedHandler());
			setOnMouseReleased(new ReleasedHandler());
			setOnMouseEntered(new EnterHandler());
			setOnMouseExited(new ExitHandler());
			
		}
		
		// ---- style helper methods (for handlers)
		
		private void defaultStyle() {
			setBackground(Style.CONTROL_ELEMENT_BACKGROUND_DEFAULT);
			setBorder(Style.CONTROL_ELEMENT_BORDER_DEFAULT);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
		}
		
		private void pressedStyle() {
			setBackground(Style.CONTROL_ELEMENT_BACKGROUND_PRESSED);
			setBorder(Style.CONTROL_ELEMENT_BORDER_ENTERED);
			setTextFill(Style.WINDOW_COLOR_TEXT_DARK);
		}
		
		private void enteredStyle() {
			setBackground(Style.CONTROL_ELEMENT_BACKGROUND_DEFAULT);
			setBorder(Style.CONTROL_ELEMENT_BORDER_ENTERED);
			setTextFill(Style.WINDOW_COLOR_TEXT_LIGHT);
		}
		
		// ---- Button Handlers ----
		
		/**
		 * Handles when the mouse enters a buttons region
		 * 
		 * @author David Boivin (absynth) ID = 40004941
		 */
		private class EnterHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				enteredStyle();
			}
			
		}
		
		/**
		 * Handles when the mouse exits a buttons region
		 * (brings button back to default)
		 * 
		 * @author David Boivin (absynth) ID = 40004941
		 */
		private class ExitHandler implements EventHandler<MouseEvent> {

			@Override
			public void handle(MouseEvent event) {
				defaultStyle();
			}
			
		}
		
		/**
		 * Handles when the button is pressed.
		 * 
		 * @author David Boivin (absynth) ID = 40004941
		 */
		private class PressedHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				pressedStyle();
			}
			
		}
		
		/**
		 * Handles when the button is released
		 * (brings button back to default)
		 * 
		 * @author David Boivin (absynth) ID = 40004941
		 */
		private class ReleasedHandler implements EventHandler<MouseEvent>{

			@Override
			public void handle(MouseEvent event) {
				defaultStyle();
			}
			
		}
	}
}
