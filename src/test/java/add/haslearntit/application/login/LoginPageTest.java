package add.haslearntit.application.login;

import org.junit.Test;

import add.haslearntit.HasLearntItBaseTest;


public class LoginPageTest extends HasLearntItBaseTest{

	
	@Test
	public void shouldContainLoginForm() throws Exception {
		
		// given:
		// when:
		tester.startPage(LoginPage.class);
		// then:
		tester.assertComponent("loginForm", LoginForm.class);
	}
	
}
