package view;

import java.util.List;
import java.util.TreeMap;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.CardType;

/**
 * Inner container of main BoardPane container. Designed to 
 * contain  the card elements of the game in a grid layout.<br>
 * Provides method to change the state of individual cards. <br>
 * NOTE: uses BoardPane.COLUMN_NUMBER to gereate the proper grid.
 * 
 * @author David Boivin (absynth) ID = 40004941
 */
public class FieldPane extends GridPane {
	
	/*
	 * I thought this was most efficient even though the initial insertion time
	 * will be O(ln n) and not O(1), as the LinkedHashMap class would allow, I
	 * thought this would be less important and minor with 25 iterations, whereas
	 * the lookup time during gameplay would be O(ln n) again for TreeMap,
	 * LinkedHashMap would only offer O(n). I figured gameplay speed to be more
	 * important than loadup speed.
	 * 
	 * @absynth
	 */
	private final TreeMap<Integer, CardView> mCardElements = new TreeMap<Integer, CardView>();
	
	public FieldPane(List<CardView> cardList) {
		super();
		
		//field styling
		setHgap(Style.FIELD_GAP_H);
		setVgap(Style.FIELD_GAP_V);
		setAlignment(Style.FIELD_ALIGNMENT);
		setPadding(Style.FIELD_PADDING);
		
		
		//Stores CardViews into appropriate data structures
		for(int i = 0; i < cardList.size(); i++) {
			CardView c = cardList.get(i);
			
			//Stores CardView for efficient future access 
			mCardElements.put(c.getCardID(), c);
			
			//adds CardViews to the layout
			GridPane.setConstraints(c, i % BoardPane.COLUMN_NUMBER, i / BoardPane.COLUMN_NUMBER);
			getChildren().add(c);
		}
	}
	
	/**
	 * Allows for easy changes to the card color by doing the lookup and color
	 * based on CardType.
	 * 
	 * @param cardID Card identification number
	 * @param type The color the card should turn, or <code>null</code> for "hidden" state
	 */
	public void changeCardColor(int cardID, CardType type) {
		try {
			mCardElements.get(cardID).changeColor(type);
			
		}catch(NullPointerException e) {
			System.out.println("No Card ID found matching: " + cardID);
		}
	}
}
