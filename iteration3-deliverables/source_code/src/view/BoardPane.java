package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import control.*;
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
 * @author David Boivin (absynth) ID = 40004941
 */
public class BoardPane extends RootPane implements Observer{
	
	public static final int COLUMN_NUMBER = 5;
	
	private final ArrayList<CardView> mCVList = new ArrayList<CardView>();
	private final Inbox mInbox;		//variable used to send messages to the controller
	
	//inner components
	private final ControlBar mControl;
	private final HQPane mHQ;
	private final FieldPane mField;
	
	private boolean mIsDisabled;
	
	/**
	 * Constructor
	 * 
	 * @param cardList the list of Card being used in the model
	 * @param k keycard
	 * @param in inbox for sending messages
	 */
	public BoardPane(List<Card> cardList, KeyCard k, Inbox in) {
		super();
		
		mInbox = in;
		mIsDisabled = false;
		
		//create 1 CardHandler for all Cards (saves memory)
		CardHandler ch = new CardHandler();
		
		//creates CardView List from cards
		for(Card c : cardList) {
			CardView cv = new CardView(c.getCardNumber(), c.getCodeWord());
			cv.setOnAction(ch);
			mCVList.add(cv);
		}
		
		//Inner container elements
		mControl = new ControlBar(mInbox, k);
		mHQ = new HQPane(k.getBlueFirst());
		mField = new FieldPane(mCVList);
		
		//adds containers to vertical stack
		this.getChildren().addAll(mHQ, mField, mControl);
	}
	
	public void setIsDisabled(boolean disabled) {
		mIsDisabled = disabled;
		if(mIsDisabled) {
			mControl.disableControl();
		}else {
			mControl.enableControl();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		Reply r = ((Reply) arg);
		
		// handles each kind of request
		switch(r.getReplyType()) {
			case HINT:
				mHQ.setClue(r.getCurrentTurn(), r.getHint());
				break;
			case UPDATE:
				mHQ.setTurn(r.getCurrentTurn());
				mHQ.setScore(true, r.getBlueScore());
				mHQ.setScore(false, r.getRedScore());
				mHQ.setClue(r.getCurrentTurn(), r.getHint());
				mHQ.setClue(!r.getCurrentTurn(), "");

				mField.changeCardColor(r.getCardAffected(), r.getCardType());

				// Disable 'NextMove' button if current turn is blue
				mControl.getNextButton().setDisable(r.getCurrentTurn());
				break;

			case END:
				mHQ.setTurn(r.getCurrentTurn());
				mHQ.setScore(true, r.getBlueScore());
				mHQ.setScore(false, r.getRedScore());

				mField.changeCardColor(r.getCardAffected(), r.getCardType());

				setIsDisabled(true);

				EndPopup endPopup = new EndPopup(r, mInbox);
				endPopup.show(getScene().getWindow());
				break;
			default:
				break;
			}
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
			if(!mIsDisabled) {
				CardView c = (CardView) event.getSource();
				mInbox.sendMessage(new Message(MessageType.SELECT, c.getCardID()));
			}
		}
	}
}
