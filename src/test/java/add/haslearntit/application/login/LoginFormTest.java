package add.haslearntit.application.login;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Test;

import add.haslearntit.HasLearntItBaseTest;
import add.haslearntit.domain.user.User;

public class LoginFormTest extends HasLearntItBaseTest{

	private FormTester formTester;

	@Test
	public void shouldValidateLoginField() throws Exception {
		
		// given:
		tester.startComponentInPage(new LoginForm());
		formTester = tester.newFormTester("loginForm:form");
		
		// when:
        formTester.setValue("login", "");
        formTester.setValue("password", "notempty");
		formTester.submit();
		
		// then:
        tester.assertErrorMessages("Please enter your login!");
	}
	
	@Test
	public void shouldValidatePasswordField() throws Exception {
		
		// given:
		tester.startComponentInPage(new LoginForm());
		formTester = tester.newFormTester("loginForm:form");
		
		// when:
        formTester.setValue("login", "notempty");
        formTester.setValue("password", "");
		formTester.submit();
		
		// then:
        tester.assertErrorMessages("Please enter your password!");
	}
	
    @Test
    public void shouldSetCurrentUserAfterSuccessfullLogin() throws Exception {

        // given:
        tester.startComponentInPage(new LoginForm());
        formTester = tester.newFormTester("loginForm:form");

        // when:
        formTester.setValue("login", "notempty");
        formTester.setValue("password", "notempty");
        formTester.submit();

        // then:
        User user = (User) tester.getApplication().getSessionStore().getAttribute(RequestCycle.get().getRequest(), "currentUser");
        assertThat(user.getName(), equalTo("notempty"));
    }
	
}
