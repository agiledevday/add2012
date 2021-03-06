package add.haslearntit.domain.entry;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Date;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class EntryTest {

	@Test
	public void shouldConstructProperDisplayMessage() throws Exception {

		// given:
		String name = "writing tests in JUnit";
		String difficulty = "easy";
		String time = "15";

		Entry entry = aEntry(name, difficulty, time, new Date());

		// when:
		String message = entry.asMessage();

		// then:
		assertThat(
				message,
				equalTo("A User has learnt writing tests in JUnit, which was pretty easy, and it took him 15 hours."));
	}

	@Test
	public void shouldSkillsWithDifferentIdentitiesNotBeEqual()
			throws Exception {

		// given:
		Entry someEntry = aEntry();
		Entry otherEntry = aEntry();

		// when:
		boolean areEqual = someEntry.equals(otherEntry);

		// then:
		assertThat(areEqual, isFalse());
	}

	@Test
	public void shouldSkillsWithDifferentIdentitiesNotHaveSameHashCode()
			throws Exception {

		// given:
		Entry someEntry = aEntry();
		Entry otherEntry = aEntry();

		// when:
		boolean hashAreEqual = someEntry.hashCode() == otherEntry.hashCode();

		// then:
		assertThat(hashAreEqual, isFalse());
	}

	@Test
	public void shouldSkillsWithDifferentSameIdentitiesBeEqual()
			throws Exception {

		// given:
		Entry someEntry = aEntry();
		Entry clonedEntry = someEntry.clone();

		// when:
		boolean areEqual = someEntry.equals(clonedEntry);

		// then:
		assertThat(areEqual, isTrue());
	}

	@Test
	public void shouldSkillsWithSameIdentitiesHaveSameHashCode()
			throws Exception {

		// given:
		Entry someEntry = aEntry();
		Entry clonedEntry = someEntry.clone();

		// when:
		boolean hashAreEqual = someEntry.hashCode() == clonedEntry.hashCode();

		// then:
		assertThat(hashAreEqual, isTrue());
	}
	
	@Test
	public void shouldCreateDateNotEmpty() throws Exception {
		Entry entry = aEntry();
		assertNotNull(entry.getCreationDate());
	}

	@Test
	@Parameters(method="earnedPoints")
	public void shouldCountPointsForDifficultyAndTime(Difficulty difficulty, int hoursSpent, int expectedPoints) {
		// given:
		Entry someEntry = aEntry("someSkill", difficulty, hoursSpent);
		// when:
		int earnedPoints = someEntry.getEarnedPoints();
		// then:
		assertEquals(expectedPoints, earnedPoints);
	}
	
	@Test
	public void shouldThrow() {
		// given:
		Entry someEntry = aEntry("someSkill", "wrong_value", "1", new Date());
		// when:
		int earnedPoints = someEntry.getEarnedPoints();
		// then:
		assertEquals(Entry.POINTS_FOR_UNKNOWN_DIFFICULTY, earnedPoints);
	}
	
	@SuppressWarnings("unused")
	private Object[] earnedPoints() {
		return $(
				$(Difficulty.EASY, 1, 1),
				$(Difficulty.MEDIUM, 1, 2),
				$(Difficulty.HARD, 1, 5),
				$(Difficulty.MEDIUM, 10, 20)
				);
	}
	
	// --

	private Entry aEntry() {
		return aEntry("someSkill", "someDifficulty", "someTime", new Date());
	}

	private Entry aEntry(String name, String difficulty, String time, Date creationDate) {
		return new Entry(name, difficulty, time, creationDate);
	}

	private Matcher<Boolean> isFalse() {
		return equalTo(Boolean.FALSE);
	}

	private Matcher<Boolean> isTrue() {
		return equalTo(Boolean.TRUE);
	}


	private Entry aEntry(String name, Difficulty difficulty, int durationHours) {
		return new Entry(name, difficulty.name(), Integer.toString(durationHours), new Date());
	}
}
