package wiz;

/** Interface for Cards, including attack, heal, and support */
public interface Card {
	/** uses the Card */
	public void use(Player self, Player enemy);
	/** discards the Card */
	public void discard();
	/** returns the Card's name */
	public String getName();
	/** returns the Card's minimum level */
	public int getLevel();
	/** returns the Card's School */
	public School getSchool();
	/** returns the Card's pip cost */
	public int getCost();
	/** checks if another Card is equal */
	@Override
	public boolean equals(Object other);
}
