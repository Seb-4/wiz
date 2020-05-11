package wiz;

import java.util.ArrayList;

public class Deck {
	private ArrayList<Card> cards;
	
	public Deck(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	public Deck(School school, int level) {
		cards = new ArrayList<Card>();
		for (AttackCard x : AttackCard.ALL_ATTACK_CARDS) {
			if (x.getLevel() <= level && x.getSchool().equals(school)) {
				cards.add(x);
			}
		}
	}
	
	public ArrayList<Card> getCards() {
		return this.cards;
	}
	
	@Override
	public String toString() {
		String ret = "\n";
		for (Card x : cards) {
			ret += x.getName() + " (Cost: " + x.getCost() + ")" + ((cards.get(cards.size()-1).equals(x)) ? "" : ",\n");
		}
		return ret;
	}
}
