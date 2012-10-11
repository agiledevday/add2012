package add.haslearntit.application.skills;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.wicket.model.util.ListModel;
import org.junit.Test;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.domain.skills.Skill;

public class LearntSkillsListTest extends HasLearntItBaseWicketIT {

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
        private static final long serialVersionUID = 8429868990688738518L;

        public StaticSkillsListModel(List<Skill> skills) {
			super(skills);
		}
		
	}
}
