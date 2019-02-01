package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import control.Inbox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Card;
import model.CardType;
import model.KeyCard;

/**
 * Root of Graphical User Interface for CodeNames COMP 354 project. <br>
 * Handles the creation of the game board and its inner elements from a set of cards.<br>
 * Outputs Message Objects through the Inbox implementation of observables which delivers
 * them to the Controller object to process user action.<br>
 * Receives instruction through the Observer interface and changes the inner elements accordingly.
 * 
 * @author David Boivin (absynth)
 */
public class BoardPane extends VBox implements Observer{
	
	public static final int COLUMN_NUMBER = 5;
	
	private final ArrayList<CardView> mCVList = new ArrayList<CardView>();
	private final Inbox mInbox;		//variable used to send messages to the controller
	
	//inner components
	private final ControlBar mControl;
	private final HQPane mHQ;
	private final FieldPane mField;
	private final KeycardPopup mPopup;
	
	/**
	 * Testing Constructor 
	 * TODO remove before submission (if i remember)
	 */
	public BoardPane() {
		super();
		
		//expected external element standing
		KeyCard k = new KeyCard(1);
		String codeword = "";
		Inbox in = new Inbox();
		ArrayList<Card> cardList = new ArrayList<Card>();
		for(int i = 0; i < 25; i++) {
			cardList.add(new Card(i, codeword, k.getCardType(i)));
		}
		
		//normal operation
		mInbox = in;
		
		//create 1 CardHandler for all Cards (saves memory)
		CardHandler ch = new CardHandler();
		
		//creates CardView List from cards
		for(Card c : cardList) {
			CardView cv = new CardView(c.getCardNumber(), c.getCodeWord());
			cv.setOnAction(ch);
			mCVList.add(cv);
		}
	
		//Inner container elements
		mControl = new ControlBar();
		mHQ = new HQPane(k.getBlueFirst());
		mField = new FieldPane(mCVList);
		mPopup = new KeycardPopup(k);
		
		//control setup
		mControl.setNewGameHandler(new NewGameHandler());
		mControl.setUndoHandler(new UndoHandler());
		mControl.setRedoHandler(new RedoHandler());
		mControl.setKeycardHandler(new KeycardHandler());
		mControl.setNextHandler(new NextHandler());
		
		//adds containers to vertical stack
		getChildren().addAll(mHQ, mField, mControl);
		
	}
	
	/**
	 * Main Constructor 
	 * 
	 * @param cardList the list of Card being used in the model
	 * @param k keycard
	 * @param in inbox for sending messages
	 */
	public BoardPane(List<Card> cardList, KeyCard k, Inbox in) {
		super();
		
		mInbox = in;
		
		//create 1 CardHandler for all Cards (saves memory)
		CardHandler ch = new CardHandler();
		
		//creates CardView List from cards
		for(Card c : cardList) {
			CardView cv = new CardView(c.getCardNumber(), c.getCodeWord());
			cv.setOnAction(ch);
			mCVList.add(cv);
		}
		
		//Inner container elements
		mControl = new ControlBar();
		mHQ = new HQPane(k.getBlueFirst());
		mField = new FieldPane(mCVList);
		mPopup = new KeycardPopup(k);
		
		//control setup
		mControl.setNewGameHandler(new NewGameHandler());
		mControl.setUndoHandler(new UndoHandler());
		mControl.setRedoHandler(new RedoHandler());
		mControl.setKeycardHandler(new KeycardHandler());
		mControl.setNextHandler(new NextHandler());
		
		//adds containers to vertical stack
		this.getChildren().addAll(mHQ, mField, mControl);
	}


	/*
	 * TODO Receives input from outbox and dictate changes to view
	 */
	@Override
	public void update(Observable o, Object arg) {
		// handles each kind of request
		
	}
	
	
	
	// ----------------------- Card Handler -------------------------
	
	/**
	 * One Handler to rule them all, One Handler to find them. <br>
	 * One Handler to bring them all, and in the MVC bind them.
	 * 
	 * @author David Boivin (absynth)
	 */
	private class CardHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			CardView c = (CardView) event.getSource();
			// TODO sends proper message to inbox including card number and event (?? reviel or hide??)
		}
		
	}
	
	
	
	
	// ----------------------- Control Handlers ---------------------
	
	
	private class KeycardHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			mPopup.show((Stage) getScene().getWindow());
			
		}
	}
	
	/**
	 * TODO sends a new Game message through inbox
	 * 
	 * @author David Boivin (absynth)
	 *
	 */
	private class NewGameHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
		}
	}
	
	
	/**
	 * TODO sends an Undo message through inbox
	 * 
	 * @author David Boivin (absynth)
	 */
	private class UndoHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
		}
	}
	
	
	/**
	 * TODO sends a redo message through inbox
		
	 * @author David Boivin (absynth)
	 */
	private class RedoHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
		
		}
	}
	
	/**
	 * TODO sends a next message through inbox
	 * 
	 * @author David Boivin (absynth)
	 */
	private class NextHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
		}
	}
	
	
}
