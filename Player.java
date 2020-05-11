package wiz;

import java.util.ArrayList;

/** An object that represents a Player in game */
public class Player {
	/** the Player's level */
	private int level;
	/** the Player's first name */
	private String firstName;
	/** the Player's last name */
	private String lastName;
	/** the Player's school */
	private School school;
	/** the Player's maximum health points */
	private int maxHP;
	/** the Player's current health points */
	private int curHP;
	/** the Player's maximum mana */
	private int maxMana;
	/** the Player's current mana */
	private int curMana;
	/** true if player is male, false if female */
	private boolean isBoy;
	/** the Player's total experience points */
	private int exp;
	/** the Player's deck */
	private Deck deck;
	/** the Player's dot effects */
	private ArrayList<Integer> dots = new ArrayList<Integer>();
	/** the Player's pip amount */
	private int[] pips = new int[7];
	
	/** creates a new Player Object with all attributes and stats */
	public Player(String fName, String lName, School school, boolean isBoy) {
		this.level = 1;
		this.exp = 0;
		this.firstName = fName;
		this.lastName = lName;
		this.school = school;
		this.isBoy = isBoy;
		this.maxMana = School.MANA[level];
		this.maxHP = this.school.getHP(level);
		this.curMana = this.maxMana;
		this.curHP = this.maxHP;
		this.deck = new Deck(school, level);
	}
	
	/** creates a new Player Object with all attributes and a randomized School */
	public Player(String fName, String lName, boolean isBoy) {
		this.level = 1;
		this.exp = 0;
		this.firstName = fName;
		this.lastName = lName;
		this.school = School.ALL_SCHOOLS[(int) Math.round(Math.random()*7)];
		this.isBoy = isBoy;
		this.maxMana = School.MANA[level];
		this.maxHP = this.school.getHP(level);
		this.curMana = this.maxMana;
		this.curHP = this.maxHP;
		this.deck = new Deck(school, level);
	}
	
	/** creates a new Player Object with all attributes at a given level */
	public Player(String fName, String lName, School school, boolean isBoy, int level) {
		this.level = level;
		this.exp = School.EXP[level-1];
		this.firstName = fName;
		this.lastName = lName;
		this.school = school;
		this.isBoy = isBoy;
		this.maxMana = School.MANA[level];
		this.maxHP = this.school.getHP(level);
		this.curMana = this.maxMana;
		this.curHP = this.maxHP;
		this.deck = new Deck(school, level);
	}
	
	/** creates a new Player Object with all attributes and a randomized School at a given level */
	public Player(String fName, String lName, boolean isBoy, int level) {
		this.level = level;
		this.exp = School.EXP[level-1];
		this.firstName = fName;
		this.lastName = lName;
		this.school = School.ALL_SCHOOLS[(int) Math.round(Math.random()*6)];
		this.isBoy = isBoy;
		this.maxMana = School.MANA[level];
		this.maxHP = this.school.getHP(level);
		this.curMana = this.maxMana;
		this.curHP = this.maxHP;
		this.deck = new Deck(school, level);
	}
	
	
	/** returns the Player's current HP */
	public int getHP() {
		return this.curHP;
	}
	
	/** returns the Player's maximum HP */
	public int getMaxHP() {
		return this.maxHP;
	}
	
	/** returns the Player's school */
	public School getSchool() {
		return this.school;
	}
	
	/** returns the Player's first and last name */
	public String getName() {
		return this.firstName + " " + this.lastName;
	}
	
	/** returns the Player's Deck */
	public Deck getDeck() {
		return this.deck;
	}
	
	/** returns the Player's Cards from their Deck */
	public ArrayList<Card> getDeckCards() {
		return this.deck.getCards();
	}
	
	/** returns the Player's DOTs */
	public ArrayList<Integer> getDOTs() {
		return this.dots;
	}
	
	/** adds a DOT to the Player's DOTs */
	public void addDOT(int damage, int rounds) {
		dots.add(damage);
		dots.add(rounds);
	}
	
	/** lowers the Player's HP by the parameter. Returns true if Player faints 
	 * @param damage the amount of damage to take
	 */
	public boolean hurt(int damage) {
		this.curHP -= damage;
		if (this.curHP < 0) {
			this.curHP = 0;
			System.out.println(this.getName() + " fainted!");
			return true;
		}
		return false;
	}
	
	/** lowers the Player's HP by their DOTs */
	public void applyDOTs() {
		for (int i=0; i<dots.size(); i+=2) {
			System.out.println(this.getName() + " took " + (this.dots.get(i)/3) + " damage! ("
					+ (this.dots.get(i+1)-1) + 
					((this.dots.get(i+1)-1 == 1) ? " round left)\n" : " rounds left)\n"));
			
			this.hurt(this.dots.get(i) / 3);
			this.dots.set(i+1, dots.get(i+1)-1);
		}
		for (int i=1; i<dots.size()-1; i+=2) {
			if (this.dots.get(i) == 0) {
				dots.remove(i);
				dots.remove(i-1);
			}
		}
	}
	
	/** returns the Player's number of pips */
	public int getPips() {
		int ret = 0;
		for (int x : this.pips) {
			ret += x;
		}
		return ret;
	}
	
	/** returns the Player's number of power pips */
	public int getPowerPips() {
		int ret = 0;
		for (int x : this.pips) {
			if (x == 2) {
				ret++;
			}
		}
		return ret;
	}
	
	/** returns the Player's number of normal pips */
	public int getNormalPips() {
		int ret = 0;
		for (int x : this.pips) {
			if (x == 1) {
				ret++;
			}
		}
		return ret;
	}
	
	/** adds a pip (either normal or power) */
	public void addPips() {
		int where = findPip(this.pips);
		if (where != -1) {
			this.pips[where] = (Math.random()*100 < School.PP_CHANCE[this.level]) ? 2 : 1;
		}
	}
	
	/** removes pips */
	public void removePips(int amount) {
		ArrayList<Integer> normalPips = findNormalPipLocations(this.pips);
		if (amount < normalPips.size()) {
			int i=0;
			while (amount > 0) {
				this.pips[normalPips.get(i)] = 0;
				i++;
				amount--;
			}
		}
		else {
			for (int x : normalPips) {
				this.pips[x] = 0;
				amount--;
			}
			
		}
	}
	
	/** returns an ArrayList of the locations of normal pips in an array */
	private ArrayList<Integer> findNormalPipLocations(int[] pips) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i=0; i<pips.length; i++) {
			if (pips[i] == 1) {
				ret.add(i);
			}
		}
		return ret;
	}
	
	/** finds where to add a pip in the Player's pips */
	private int findPip(int[] pips) {
		for (int i=0; i<pips.length; i++) {
			if (pips[i] == 0) {
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName + ((isBoy) ? ", Male " : ", Female ") +  
				"(School: " + this.school + 
				", Level: " + this.level + 
				" ) HP: " + this.curHP + "/" + this.maxHP + 
				" Mana: " + this.curMana + "/" + this.maxMana +
				" EXP: " + this.exp; 
	}
}
