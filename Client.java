package wiz;

public class Client {
	public static void main(String[] args) {
		Player player = new Player("Matthew", "Firewalker", School.FIRE, true, 10);
		Player enemy = new Player("Isabella", "Flamewyrn", School.FIRE, false, 10);
		
		System.out.println("Winner: " + Fighter.fightPvP(player, enemy));
	}
}
