package view;

import control.Inbox;
import control.Message;
import control.MessageType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.KeyCard;

/**
 * A container which has all the user control buttons for the program.
 * Listeners must be added through linking methods, default functionality won't
 * result in any change in the program.
 * 
 * @author David Boivin (absynth) ID = 40004941
 */
public class ControlBar extends HBox{
	
	private Inbox mInbox;
	private KeycardPopup mPopup;
	
	private ControlButton mNew;
	private ControlButton mNext;
	private ControlButton mUndo;
	private ControlButton mRedo;
	private ControlButton mKeycard;
	private ControlButton mQuit;
	
	/**
	 * Constructor which creates inner elements and set the styling for
	 * the control bar.
	 */
	public ControlBar(Inbox inbox, KeyCard k) {
		super();
		
		mInbox = inbox;
		mPopup = new KeycardPopup(k);
		
		//control bar styling
		setPadding(Style.CONTROL_PADDING);
		
		
		//creates inner elements (control buttons)
		mNew = new ControlButton("New Game");
		mNext = new ControlButton("Next Move");
		mUndo = new ControlButton ("Undo");
		mRedo = new ControlButton("Redo");
		mKeycard = new ControlButton("Keycard");
		mQuit = new ControlButton("Quit");
		
		//set button actions
		enableControl();
		
		//buffer setup
		Pane buff1 = new Pane();
		Pane buff2 = new Pane();
		Pane buff3 = new Pane();
		Pane buff4 = new Pane();
		Pane buff5 = new Pane();
		
		buff3.setPrefWidth(Style.CONTROL_SPACING);
		buff4.setPrefWidth(Style.CONTROL_SPACING);
		buff5.setPrefWidth(Style.CONTROL_SPACING);
		
		HBox.setHgrow(buff1, Priority.ALWAYS);
		HBox.setHgrow(buff2, Priority.ALWAYS);
		
		this.getChildren().addAll(mNext, buff3, mKeycard, buff1, mUndo, buff4,
				mRedo, buff2, mNew, buff5, mQuit);
		
	}
	
	public void enableControl() {
		mNew.setOnAction(new NewGameHandler());
		mNext.setOnAction(new NextHandler());
		mUndo.setOnAction(new UndoHandler());
		mRedo.setOnAction(new RedoHandler());
		mKeycard.setOnAction(new KeycardHandler());
		mQuit.setOnAction(new QuitHandler());
	}
	
	public void disableControl() {
		mNew.setOnAction(null);
		mNext.setOnAction(null);
		mUndo.setOnAction(null);
		mRedo.setOnAction(null);
		mKeycard.setOnAction(null);
		mQuit.setOnAction(null);
	}
	
	
	// ------------------- Handler Linking methods -----------------
	
	/**
	 * sends a new Game message through inbox
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 *
	 */
	private class NewGameHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			((BoardPane) getScene().getRoot()).setIsDisabled(true);
			NewGamePopup ngp = new NewGamePopup(mInbox);
			ngp.show((Stage) getScene().getWindow());
		}
	}
	
	
	/**
	 * sends an Undo message through inbox
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class UndoHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			mInbox.sendMessage(new Message(MessageType.UNDO, -1));
		}
	}
	
	
	/**
	 * sends a redo message through inbox
	 *
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class RedoHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			mInbox.sendMessage(new Message(MessageType.REDO, -1));
		}
	}
	
	/**
	 * sends a next message through inbox
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class NextHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			mInbox.sendMessage(new Message(MessageType.NEXT, -1));
		}
	}
	
	/**
	 * Handler which displays the keycard popup.
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class KeycardHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			mPopup.show((Stage) getScene().getWindow());
		}
	}
	
	/**
	 * Handler which exits the game.
	 * 
	 * @author David Boivin (absynth) ID = 40004941
	 */
	private class QuitHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}
	}
}
