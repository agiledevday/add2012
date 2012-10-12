package add.haslearntit.application.entry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.HasLearntItBaseWicketIT;

@RunWith(MockitoJUnitRunner.class)
public class DashboardPageTest extends HasLearntItBaseWicketIT {

	@Before
	public void setUp() {
		tester.startPage(DashboardPage.class);
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
		tester.assertComponent("newSkillForm", NewEntryPanel.class);
	}
	
	@Test
	public void shouldContainLearntSkillList() throws Exception {
		
		// given:
		// when:
		// then:
		tester.assertComponent("learntSkillsList", TimelinePanel.class);
	}
}
