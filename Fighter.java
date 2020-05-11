package wiz;
import java.util.Scanner;

public class Fighter {
	
	public static Player fightPvP(Player person, Player enemy) {
		Scanner s = new Scanner(System.in);
		while (person.getHP() > 0 && enemy.getHP() > 0) {
			person.addPips();
			enemy.addPips();
			doTurn(person, enemy, s);
			doTurn(enemy, person, s);
			person.applyDOTs();
			enemy.applyDOTs();
		}
		if (person.getHP() <= 0) {
			s.close();
			return enemy;
		}
		else {
			s.close();
			return person;
		}
	}
	
	private static void doTurn(Player person, Player enemy, Scanner s) {
		System.out.println(person + "\n" + enemy);
		
		Card using = null;
		if (person.getDeckCards().size()>0) {
			System.out.println(person.getName() + ", you have " + person.getPips() + 
					((person.getPips() != 1) ? " pips." : " pip.") + "(" + person.getPowerPips() +
					" power, " + person.getNormalPips() + " normal) Select card to use: " 
					+ person.getDeck());
			String toUse = s.nextLine();
			
			// Check if chosen card is valid
			boolean valid = false;
			for (Card x : person.getDeckCards()) {
				if (x.getName().equalsIgnoreCase(toUse)) {
					using = x;
					valid = true;
				}
			}
			while (!valid && person.getDeckCards().size()>0) {
				System.out.println("Invalid selection.\nSelect a card to use: " + person.getDeck());
				toUse = s.nextLine();
				for (Card x : person.getDeckCards()) {
					if (x.getName().equalsIgnoreCase(toUse)) {
						using = x;
						valid = true;
					}
				}
			}		
		}
		else {
			System.out.println(person.getName() + " is out of cards! (Press Enter)");
			s.nextLine();
		}
		if (using != null) {
			using.use(person, enemy);
		}
	}
}
