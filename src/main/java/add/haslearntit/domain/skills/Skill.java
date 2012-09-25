package add.haslearntit.domain.skills;

import java.util.UUID;

public class Skill {

	private UUID id = UUID.randomUUID();

	private String name;
	private String difficultyLevel;
	private String timeSpent;

	private Skill() {
	}

	public Skill(String name, String difficulty, String timeSpent) {

		this();
		
		this.name = name;
		this.difficultyLevel = difficulty;
		this.timeSpent = timeSpent;
	}

	public String asMessage() {
		return String
				.format("A User has learnt %s, which was pretty %s, and it took him %s.",
						name, difficultyLevel, timeSpent);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(!(obj instanceof Skill)){
			return false;
		}
		
		Skill other = (Skill) obj;
		return id.equals(other.id);
	}
	
	@Override
	protected Skill clone() {
		Skill clone = new Skill();
		clone.id = id;
		clone.name = name;
		clone.timeSpent = timeSpent;
		clone.difficultyLevel = difficultyLevel;
		return clone;
	}
}
