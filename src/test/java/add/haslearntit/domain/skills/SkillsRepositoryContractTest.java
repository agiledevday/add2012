package add.haslearntit.domain.skills;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import org.junit.Test;

public abstract class SkillsRepositoryContractTest {

	protected SkillsRepository repository;

	@Test
	public void shouldStoreAndLoadSkill() throws Exception {
		
		// given:
		Skill skill = aSkill();
		// when:
		repository.store(skill);
		// then:
		assertThat(repository.loadAll(), hasItem(skill));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldReturnedSkillsBeImmutable() throws Exception {
		
		// given:
		// when:
		repository.loadAll().add(aSkill());
		// then:
	}

	// --
	
	private Skill aSkill() {
		return new Skill("skill", "difficultyLevel", "timeConsumed");
	}

}