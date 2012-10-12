package add.haslearntit.application;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import add.haslearntit.application.entry.DashboardPage;
import add.haslearntit.application.login.LoginPage;
import add.haslearntit.application.registration.RegistrationPage;

public class HasLearntItApplication extends WebApplication {

	@Override
	public Class<DashboardPage> getHomePage() {
		return DashboardPage.class;
	}

	private Class<LoginPage> getLoginPage() {
		return LoginPage.class;
	}

	@Override
	public void init() {

		super.init();
		mountPage("/home", getHomePage());
		mountPage("/login", getLoginPage());
		mountPage("/register", RegistrationPage.class);
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}

}
