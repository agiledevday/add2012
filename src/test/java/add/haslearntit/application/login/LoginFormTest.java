package add.haslearntit.application.login;

import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;

import add.haslearntit.HasLearntItBaseTest;

public class LoginFormTest extends HasLearntItBaseTest{

	private FormTester formTester;

	@Test
	public void shouldValidateLoginField() throws Exception {
		
		// given:
		tester.startComponentInPage(new LoginForm());
		formTester = tester.newFormTester("loginForm:form");
		
		// when:
		formTester.setValue("loginField", "");
		formTester.setValue("passwordField", "notempty");
		formTester.submit();
		
		// then:
		tester.assertErrorMessages("Invalid login");
	}
	
	@Test
	public void shouldValidatePasswordField() throws Exception {
		
		// given:
		tester.startComponentInPage(new LoginForm());
		formTester = tester.newFormTester("loginForm:form");
		
		// when:
		formTester.setValue("loginField", "notempty");
		formTester.setValue("passwordField", "");
		formTester.submit();
		
		// then:
		tester.assertErrorMessages("Invalid password");
	}
	
	
}
