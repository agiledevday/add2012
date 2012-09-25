package add.haslearntit.application.skills;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import add.haslearntit.application.HasLearntItApplication;


public class UserSkillsPageTest {

	private WicketTester tester;

	@Before
	public void setUp() {
		tester = new WicketTester(new HasLearntItApplication());
		tester.startPage(UserSkillsPage.class);
	}

	@Test
	public void shouldPageHasTitle() throws Exception {

		// given:
		// when:
		// then:
		tester.assertContains("<title>Has Learnt It</title>");
	}
	
	@Test
	public void shouldContainNewSkillForm() throws Exception {
		
		// given:
		// when:
		// then:
		tester.assertComponent("newSkillForm", NewSkillForm.class);
	}
	
	@Test
	public void shouldContainLearntSkillList() throws Exception {
		
		// given:
		// when:
		// then:
		tester.assertComponent("learntSkillsList", LearntSkillsList.class);
	}
}
