package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import control.Controller;
import control.Strategy;
import model.Card;
import model.CardType;
import model.DatabaseExtractor;
import model.SimpleCard;

/**
 * Test Suite for the hints
 * 
 * Tests:
 * -hint retrieval
 * -hint population
 * -hint relation
 * -spymaster hint use
 * -AI smart guessing
 * 
 * @author David Boivin (absynth) 40004941
 */
public class HintTest {

	@Test
	/**
	 * makes sure that hints can be accesses from card and 
	 * that each word has at least 3 hints, which ensures variability
	 */
	public void hintRetrievalTest() {
		ArrayList<Card> cards = getCards();
		boolean hasSufficientHints = true;
		
		for(Card c : cards) {
			if(c.getHints().length < 3) {
				hasSufficientHints = false;
				break;
			}
		}
		
		assertEquals(true, hasSufficientHints);
	}
	
	@Test
	/**
	 * Makes sure that hints are associated with at least 2 words, which ensures that spymaster can relate 2+ words per hint
	 */
	public void hintRelationTest() {
		HashMap<String, ArrayList<String>> hints = new HashMap<String, ArrayList<String>>();
		ArrayList<Card> cards = getCards();
		boolean related = true;
		
		//populates hints map
		for(Card c: cards) {
			for(String hint : c.getHints()) {
				if(hints.containsKey(hint)) {
					ArrayList<String> words = hints.get(hint);
					words.add(c.getCodeWord());
				}else {
					ArrayList<String> words = new ArrayList<String>();
					words.add(c.getCodeWord());
					hints.put(hint, words);
				}
			}
		}
		
		//checks if all hints are related to 2 or more words
		for(String hint : hints.keySet()) {
			if(hints.get(hint).size() < 2) {
				related = false;
				break;
			}
		}
		
		assertEquals(true, related);
		
	}
	
	@Test
	/**
	 * Makes sure that there are over 400 hints in the database
	 */
	public void hintPopulationTest() {
		ArrayList<Card> cardList = getCards();
		int count = 0;
		
		for(Card c: cardList) {
			for(String s : c.getHints()) {
				count++;
			}
		}
		
		assertEquals(true, count >= 400);
	}
	
	@Test
	/**
	 * Makes sure that there are over 100 words in the database
	 */
	public void wordPopulationTest() {
		ArrayList<Card> cardList = getCards();
		
		assertEquals(true, cardList.size() >= 100);
	}
	
	
	@Test
	/**
	 * Makes sure that spymaster uses correct hint for given card set.
	 */
	public void SpymasterHintTest() {	
		boolean test = "2".equals(Strategy.generateHint(getTestBoard(), CardType.BLUE));
		assertEquals(true, test);
	}
	
	@Test
	/**
	 * Makes sure Smart Strategy uses hint to guess the correct word
	 */
	public void AIHintTest() {
		Strategy.generateHint(getTestBoard(), CardType.BLUE);
		Strategy.getHintedCards(getTestBoard());
		boolean test = "1".equals(Strategy.pickNextCard(Strategy.storedHintedCards));
		assertEquals(true, test);
	}
	
	//helper method to get all the cards from the database
	private ArrayList<Card> getCards(){
		ArrayList<Card> cardList = new ArrayList<Card>();
		DatabaseExtractor extractor = new DatabaseExtractor();
		extractor.openDatabase();
		extractor.importTheDatabase();
		ArrayList<SimpleCard> db = extractor.getDatabase(); 
		int i = 0;
		while(i < db.size()) {
			cardList.add(new Card(i, db.get(i).getWord(), CardType.YELLOW, db.get(i).getHints()));
			
			i++;
		}
		
		return cardList;
	}
	
	//helper method to create a list of cards representing the gameboard, used for testing Spymaster and AI
	private ArrayList<Card> getTestBoard(){
		ArrayList<Card> cardList = new ArrayList<Card>();
		String[] hint0 = {"1"};
		String[] hint1 = {"1", "2"};
		String[] hint2 = {"1", "3"};
		String[] hint3 = {"1", "2", "3"};
		String[] hint4 = {"2", "4"};
		String[] hint5 = {"3", "2"};
		//most common is 1 and 2, but hint0 is RED so 2 is the final answer
		
		cardList.add(new Card(0, "0", CardType.RED, hint0));		//wrong color (shouldn't be considered)
		cardList.add(new Card(1, "1", CardType.BLUE, hint1));
		cardList.add(new Card(2, "2", CardType.BLUE, hint2));
		cardList.add(new Card(3, "3", CardType.BLUE, hint3));
		cardList.add(new Card(4, "4", CardType.BLUE, hint4));
		cardList.add(new Card(5, "5", CardType.BLUE, hint5));
		
		return cardList;
	}
	
}
