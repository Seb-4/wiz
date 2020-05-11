package wiz;

public class AttackCard implements Card {
	
	/** just some stupid card for memes */
	public static final AttackCard DUMB = new AttackCard("Socialize", 42069, 42069, 1, 1, School.FIRE, 100);
	// listing all the AttackCards
	public static final AttackCard FIRECAT = new AttackCard("firecat", 80, 120, 1, 1, School.FIRE, 75);
	public static final AttackCard FIRE_ELF = new AttackCard("fire elf", 100, 100, 2, 5, School.FIRE, 75, 210, 3);
	
	public static final AttackCard[] ALL_ATTACK_CARDS = {FIRECAT, FIRE_ELF};
	
	/** the minimum damage for an AttackCard */
	private int minDamage;
	/** the maximum damage for an AttackCard */
	private int maxDamage;
	/** the pip cost of an AttackCard */
	private int cost;
	/** the minimum level for an AttackCard */
	private int level;
	/** the name of an AttackCard */
	private String name;
	/** the School of an AttackCard */
	private School type;
	/** the accuracy of an AttackCard */
	private int accuracy;
	/** the damage per round of an AttackCard */
	private int dot;
	/** the rounds left for a dot */
	private int dotR;
	
	
	public AttackCard(String name, int min, int max, int cost, int level, School type, int accuracy, int dot, int dotR) {
		this.minDamage = min;
		this.maxDamage = max;
		this.name = name;
		this.cost = cost;
		this.level = level;
		this.type = type;
		this.accuracy = accuracy;
		this.dot = dot;
		this.dotR = dotR;
	}
	
	public AttackCard(String name, int min, int max, int cost, int level, School type, int accuracy) {
		this.minDamage = min;
		this.maxDamage = max;
		this.name = name;
		this.cost = cost;
		this.level = level;
		this.type = type;
		this.accuracy = accuracy;
		this.dot = 0;
		this.dotR = 0;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public int getLevel() {
		return this.level;
	}
	
	@Override
	public School getSchool() {
		return this.type;
	}
	
	@Override
	public int getCost() {
		return this.cost;
	}
	
	@Override
	public void use(Player self, Player enemy) {
		if (Math.random()*100 < this.accuracy) {
			int damage = (int) Math.round(Math.random()*(maxDamage-minDamage)+minDamage);
			System.out.println("\n" + self.getName() + " used " + this.name + " dealing " + damage +  " damage!\n");
			enemy.hurt(damage);
			if (this.dot > 0) {
				enemy.addDOT(this.dot, this.dotR);
			}
		}
		else {
			System.out.println("\n" + self.getName() + " fizzled!\n");
		}
	}

	@Override
	public void discard() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof AttackCard) {
			return this.name.equals(((AttackCard)other).getName());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
 }
