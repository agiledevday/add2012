package add.haslearntit;

import add.haslearntit.application.HasLearntItApplication;
import add.haslearntit.domain.entry.EntryRepository;

import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class HasLearntItBaseWicketIT {

    protected static final String APP_INFO_TEST_TEXT = "Test revision";

	protected ApplicationContextMock applicationContext;

	protected WicketTester tester;

	@Mock
	protected EntryRepository entryRepository;


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
		applicationContext.putBean("entryRepository", entryRepository);
        applicationContext.putBean("appInfoText", APP_INFO_TEST_TEXT);
		
		setupApplicationContext();
		
	}

	protected void setupApplicationContext() {
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
