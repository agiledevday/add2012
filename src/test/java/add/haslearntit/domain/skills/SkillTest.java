package add.haslearntit.domain.skills;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
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
		assertThat(
				message,
				equalTo("A User has learnt writing tests in JUnit, which was pretty easy, and it took him 15 minutes."));
	}

	@Test
	public void shouldSkillsWithDifferentIdentitiesNotBeEqual()
			throws Exception {

		// given:
		Skill someSkill = aSkill();
		Skill otherSkill = aSkill();

		// when:
		boolean areEqual = someSkill.equals(otherSkill);

		// then:
		assertThat(areEqual, isFalse());
	}

	@Test
	public void shouldSkillsWithDifferentIdentitiesNotHaveSameHashCode()
			throws Exception {

		// given:
		Skill someSkill = aSkill();
		Skill otherSkill = aSkill();

		// when:
		boolean hashAreEqual = someSkill.hashCode() == otherSkill.hashCode();

		// then:
		assertThat(hashAreEqual, isFalse());
	}

	@Test
	public void shouldSkillsWithDifferentSameIdentitiesBeEqual()
			throws Exception {

		// given:
		Skill someSkill = aSkill();
		Skill clonedSkill = someSkill.clone();

		// when:
		boolean areEqual = someSkill.equals(clonedSkill);

		// then:
		assertThat(areEqual, isTrue());
	}

	@Test
	public void shouldSkillsWithSameIdentitiesHaveSameHashCode()
			throws Exception {

		// given:
		Skill someSkill = aSkill();
		Skill clonedSkill = someSkill.clone();

		// when:
		boolean hashAreEqual = someSkill.hashCode() == clonedSkill.hashCode();

		// then:
		assertThat(hashAreEqual, isTrue());
	}

	@Test
	@Parameters(method="earnedPoints")
	public void shouldCountPointsForDifficultyAndTime(Difficulty difficulty, int hoursSpent, int expectedPoints) {
		// given:
		Skill someSkill = aSkill("someSkill", difficulty, hoursSpent);
		// when:
		int earnedPoints = someSkill.getEarnedPoints();
		// then:
		assertEquals(expectedPoints, earnedPoints);
	}
	
	@Test
	public void shouldThrow() {
		// given:
		Skill someSkill = aSkill("someSkill", "wrong_value", "1");
		// when:
		int earnedPoints = someSkill.getEarnedPoints();
		// then:
		assertEquals(Skill.POINTS_FOR_UNKNOWN_DIFFICULTY, earnedPoints);
	}
	
	@SuppressWarnings("unused")
	private Object[] earnedPoints() {
		return $(
				$(Difficulty.EASY, 1, 1),
				$(Difficulty.MODERATE, 1, 2),
				$(Difficulty.HARD, 1, 5),
				$(Difficulty.MODERATE, 10, 20)
				);
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


	private Skill aSkill(String name, Difficulty difficulty, int durationHours) {
		return new Skill(name, difficulty.name(), Integer.toString(durationHours));
	}
}
