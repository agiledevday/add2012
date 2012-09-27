package add.haslearntit.application.skills;

import static org.mockito.Mockito.when;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import add.haslearntit.application.HasLearntItApplication;
import add.haslearntit.domain.skills.SkillsRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserSkillsPageTest {

	@Mock
	private ApplicationContext applicationContext;
	@Mock
	private SkillsRepository skillsRepository;
	
	private WicketTester tester;

	@Before
	public void setUp() {
		
		HasLearntItApplication application = new HasLearntItApplication();
		application.setApplicationContext(applicationContext);
		when(applicationContext.getBean("skillsRepository")).thenReturn(skillsRepository);
		
		tester = new WicketTester(application);
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
