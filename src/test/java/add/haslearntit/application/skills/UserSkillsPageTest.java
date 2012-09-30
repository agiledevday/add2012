package add.haslearntit.application.skills;

import add.haslearntit.HasLearntItBaseTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserSkillsPageTest extends HasLearntItBaseTest {

	@Before
	public void setUp() {
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
		tester.assertComponent("newSkillForm", NewSkillPanel.class);
	}
	
	@Test
	public void shouldContainLearntSkillList() throws Exception {
		
		// given:
		// when:
		// then:
		tester.assertComponent("learntSkillsList", LearntSkillsList.class);
	}
}
