package add.haslearntit.domain.skills;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import org.junit.Test;


public class SkillTest {

	@Test
	public void shouldConstructProperDisplayMessage() throws Exception {
		
		// given:
		String name = "writing tests in JUnit";
		String difficulty = "easy";
		String time = "15 minutes";
		
		Skill skill = aSkill(name, difficulty, time);
		
		// when:
		String message = skill.asMessage();
		
		// then:
		assertThat(message, equalTo("A User has learnt writing tests in JUnit, which was pretty easy, and it took him 15 minutes."));
	}

	@Test
	public void shouldSkillsWithDifferentIdentitiesNotBeEqual() throws Exception {
		
		// given:
		Skill someSkill = aSkill();
		Skill otherSkill = aSkill();
		
		// when:
		boolean areEqual = someSkill.equals(otherSkill);
		
		// then:
		assertThat(areEqual, isFalse());
	}

	@Test
	public void shouldSkillsWithDifferentIdentitiesNotHaveSameHashCode() throws Exception {
		
		// given:
		Skill someSkill = aSkill();
		Skill otherSkill = aSkill();
		
		// when:
		boolean hashAreEqual = someSkill.hashCode() == otherSkill.hashCode();
		
		// then:
		assertThat(hashAreEqual, isFalse());
	}
	
	@Test
	public void shouldSkillsWithDifferentSameIdentitiesBeEqual() throws Exception {
		
		// given:
		Skill someSkill = aSkill();
		Skill clonedSkill = someSkill.clone();
		
		// when:
		boolean areEqual = someSkill.equals(clonedSkill);
		
		// then:
		assertThat(areEqual, isTrue());
	}
	
	@Test
	public void shouldSkillsWithSameIdentitiesHaveSameHashCode() throws Exception {
		
		// given:
		Skill someSkill = aSkill();
		Skill clonedSkill = someSkill.clone();
		
		// when:
		boolean hashAreEqual = someSkill.hashCode() == clonedSkill.hashCode();
		
		// then:
		assertThat(hashAreEqual, isTrue());
	}
	

	// --
	
	private Skill aSkill() {
		return aSkill("someSkill", "someDifficulty", "someTime");
	}
	
	private Skill aSkill(String name, String difficulty, String time) {
		return new Skill(name, difficulty, time);
	}

	private Matcher<Boolean> isFalse() {
		return equalTo(Boolean.FALSE);
	}

	private Matcher<Boolean> isTrue() {
		return equalTo(Boolean.TRUE);
	}

}
