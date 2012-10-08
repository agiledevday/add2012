package add.haslearntit.domain.skills;

public enum Difficulty {
	EASY(1), MODERATE(2), HARD(5);

	private final int level;

	private Difficulty(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

}
