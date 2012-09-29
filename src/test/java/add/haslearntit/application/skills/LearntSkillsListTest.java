package add.haslearntit.application.skills;

import static java.util.Arrays.asList;

import java.util.List;

import add.haslearntit.HasLearntItBaseTest;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import add.haslearntit.application.HasLearntItApplication;
import add.haslearntit.domain.skills.Skill;

public class LearntSkillsListTest extends HasLearntItBaseTest {

	@Test
	public void shouldDisplayAllLearntSkills() throws Exception {
		
		// given:
		Skill someSkill = aSkill("java programming");
		Skill otherSkill = aSkill("sky diving");
		
		// when:
		tester.startComponentInPage(new LearntSkillsList("learntSkillsList", modelContainingSkills(someSkill, otherSkill)));

		// then:
		tester.assertContains(someSkill.asMessage());
		tester.assertContains(otherSkill.asMessage());
	}

	@Test
	public void shouldDisplayEncouragementMessageIfUserHasNoSkills() throws Exception {

		// given:

		// when:
		tester.startComponentInPage(new LearntSkillsList("learntSkillsList", modelContainingSkills()));

		// then:
		tester.assertContains("You haven't recorded any skills. For sure there is something you have learnt lately!");
	}

	@Test
	public void shouldHideEncouragementMessageIfUserHasAlLeastOneSkill() throws Exception {

		// given:

		// when:
		tester.startComponentInPage(new LearntSkillsList("learntSkillsList", modelContainingSkills(aSkill("skuba diving"))));

		// then:
		tester.assertContainsNot("You haven't recorded any skills. For sure there is something you have learnt lately!");
	}
	
	@Test
	public void shouldDisplayLearnPoints(){
		//given
		Skill someSkill = new Skill("java programming", "EASY", "1");
		
		//when
		tester.startComponentInPage(new LearntSkillsList("learntSkillsList", modelContainingSkills(someSkill)));
		
		//then
		tester.assertLabel("learntSkillsList:list:0:skillPoints",Integer.toString(someSkill.getEarnedPoints()));
	}
	
	// --

	private SkillsListModel modelContainingSkills(Skill... someSkills) {
		return new StaticSkillsListModel(asList(someSkills));
	}
	
	private Skill aSkill(String name) {
		return new Skill(name, "easy", "1 minute");
	}

	public class StaticSkillsListModel extends ListModel<Skill> implements SkillsListModel {
		
		public StaticSkillsListModel(List<Skill> skills) {
			super(skills);
		}
		
	}
}
