package add.haslearntit;

import add.haslearntit.application.HasLearntItApplication;
import add.haslearntit.domain.skills.SkillsRepository;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.mockito.Mock;

public class HasLearntItBaseWicketIT {

	protected ApplicationContextMock applicationContext;

	protected WicketTester tester;

	//remember to add any new mock to applicationContext
	@Mock
	protected SkillsRepository skillsRepository;


	@Before
	public final void setUpEnvironment() {
		createApplicationContext();
		createTestApplication();
	}

	private void createTestApplication() {
		HasLearntItTestApplication application = new HasLearntItTestApplication();
		tester = new WicketTester(application);
	}

	private void createApplicationContext() {
		applicationContext = new ApplicationContextMock();

		applicationContext.putBean("skillsRepository", skillsRepository);
	}

	protected class HasLearntItTestApplication extends HasLearntItApplication {
		@Override
		public RuntimeConfigurationType getConfigurationType() {
			return RuntimeConfigurationType.DEVELOPMENT;
		}

		@Override
		public void init() {
			getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext, true));
		}

		@Override
		protected void outputDevelopmentModeWarning() {
			// to make wicket silent about running in DEVELOPMENT mode
		}

	}
}
