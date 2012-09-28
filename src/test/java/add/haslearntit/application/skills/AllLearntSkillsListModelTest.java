package add.haslearntit.application.skills;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import add.haslearntit.HasLearntItBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.domain.skills.Skill;

@RunWith(MockitoJUnitRunner.class)
public class AllLearntSkillsListModelTest extends HasLearntItBaseTest{

	private SkillsListModel model;

	@Before
	public void setUp() throws Exception {

		model = new AllLearntSkillsListModel(skillsRepository);
	}
	
	@Test
	public void shouldListAllLearntSkills() throws Exception {
		
		// given:
		Skill someSkill = aSkill("cow milking");
		Skill otherSkill = aSkill("scuba diving");
		
		repositoryContainsSkills(someSkill, otherSkill);
		
		// when:
		List<Skill> actualSkills = model.getObject();
		
		// then:
		assertThat(actualSkills, hasItems(someSkill, otherSkill));
	}
	
	@Test
	public void shouldCacheResultList() throws Exception {
		
		// given:
		
		// when:
		model.getObject();
		model.getObject();
		
		// then:
		verify(skillsRepository, times(1)).loadAll();
	}
	
	// --
	
	private void repositoryContainsSkills(Skill... someSkills) {
		when(skillsRepository.loadAll()).thenReturn(asList(someSkills));
	}
	
	private Skill aSkill(String skill) {
		return new Skill(skill, "easy", "1 day");
	}
}
