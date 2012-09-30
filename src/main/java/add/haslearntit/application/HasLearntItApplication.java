package add.haslearntit.application;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import add.haslearntit.application.login.LoginPage;
import add.haslearntit.application.skills.UserSkillsPage;

public class HasLearntItApplication extends WebApplication {

	@Override
	public Class<UserSkillsPage> getHomePage() {
		return UserSkillsPage.class;
	}

	private Class<LoginPage> getLoginPage() {
		return LoginPage.class;
	}

	@Override
	public void init() {

		super.init();
		mountPage("/user", getHomePage());
		mountPage("/login", getLoginPage());
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}

}
