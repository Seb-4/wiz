package wiz;

public class Client {
	public static void main(String[] args) {
		Player player = new Player("Matthew", "Firewalker", School.FIRE, true, 1);
		Player enemy = new Player("Isabella", "Flamewyrn", School.FIRE, false, 1);
		
		System.out.println("Winner: " + Fighter.fightPvP(player, enemy));
	}
}
