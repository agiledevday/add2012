package add.haslearntit.domain.skills;

public enum Difficutly {
	EASY(1), MODERATE(2), HARD(5);

	private final int level;

	private Difficutly(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

}
