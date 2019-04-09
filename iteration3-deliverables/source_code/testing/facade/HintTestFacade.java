package facade;

import java.util.ArrayList;

import control.Strategy;
import model.Card;
import model.CardType;


public class HintTestFacade {	
	public void AIHintTest() {
		Strategy.generateHint(getTestBoard(), CardType.BLUE);
		Strategy.getHintedCards(getTestBoard());		
		System.out.print("Picking hint...	");
		System.out.println("AI picks hint: "+Strategy.generateHint(getTestBoard(), CardType.BLUE));
		System.out.print("Picking word...	");
		System.out.println("AI picks word: "+Strategy.pickNextCard(Strategy.storedHintedCards));
	}

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
	
	public static class HintFacadeDriver{
		public static void main(String[] args) {
			System.out.println("AI hint testing...");
			System.out.println("Expected hint:2, expected word:1\n");
			HintTestFacade facade = new HintTestFacade();
			facade.AIHintTest();
		}
	}
}
