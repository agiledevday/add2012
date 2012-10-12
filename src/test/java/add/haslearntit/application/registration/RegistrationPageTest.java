package add.haslearntit.application.registration;

import static org.junit.Assert.*;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;
import org.mockito.Mockito;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.application.entry.DashboardPage;
import add.haslearntit.domain.user.User;

public class RegistrationPageTest extends HasLearntItBaseWicketIT{
	
	private static final String REGISTER_FORM_ID = "register-form";
	private static final String PASSWORD_INPUT_ID = "password-input";
	private static final String LOGIN_INPUT_ID = "login-input";

	
	
	@Test
	public void shouldOpenRegistrationPage() throws Exception {
		tester.startPage(RegistrationPage.class);
		
		tester.assertRenderedPage(RegistrationPage.class);
	}
	
	@Test
	public void shouldHaveTitle() throws Exception {
		tester.startPage(RegistrationPage.class);
		
		tester.assertContains("Register");
	}
	
	@Test
	public void shouldHaveLoginField() throws Exception {
		tester.startPage(RegistrationPage.class);
		
		tester.assertComponent(REGISTER_FORM_ID+":"+LOGIN_INPUT_ID, TextField.class);
	}
	
	@Test
	public void shouldHavePasswordField() throws Exception {
		tester.startPage(RegistrationPage.class);
		
		tester.assertComponent(REGISTER_FORM_ID+":"+PASSWORD_INPUT_ID, PasswordTextField.class);
	}
	
	@Test
	public void shouldHaveSubmitButton() throws Exception {
		tester.startPage(RegistrationPage.class);
		
		tester.assertComponent(REGISTER_FORM_ID+":"+"register-button", Button.class);
	}
	
	@Test
	public void registeredUserShouldBeRedirectedToHomePage() throws Exception {
		tester.startPage(RegistrationPage.class);
		
		FormTester formTester = tester.newFormTester(REGISTER_FORM_ID);
		formTester.setValue(LOGIN_INPUT_ID, "bob");
		formTester.setValue(PASSWORD_INPUT_ID, "pass");
		formTester.submit();
		
		tester.assertRenderedPage(DashboardPage.class);
	}
	
	@Test
	public void shouldRegisterUser() throws Exception {
		tester.startPage(RegistrationPage.class);
		User user = new User("bob","pass");
		
		FormTester formTester = tester.newFormTester(REGISTER_FORM_ID);
		formTester.setValue(LOGIN_INPUT_ID, user.getName());
		formTester.setValue(PASSWORD_INPUT_ID, user.getPassword());
		formTester.submit();
		
		Mockito.verify(userRepository).store(user);
	}

}
