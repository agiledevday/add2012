package add.haslearntit.domain.skills;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import org.junit.Test;

import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepository;

public abstract class SkillsRepositoryContractTest {

	protected SkillsRepository repository;
	private Skill skill = aSkill();

	@Test
	public void shouldStoreAndLoadSkill() throws Exception {
		
		// given:
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
	
	@Test
	public void shouldAddCommentToSkill() {
		Comment comment = new Comment("comment1");
		Comment commentForOtherSkill = new Comment("comment2");
		
		repository.addComment(skill, comment );
		repository.addComment(aSkill(), commentForOtherSkill );

		assertThat(repository.fetchAllComments(skill)).containsOnly(comment);
	}
	
	
	

	// --
	
	private Skill aSkill() {
		return new Skill("skill", "difficultyLevel", "timeConsumed");
	}

}