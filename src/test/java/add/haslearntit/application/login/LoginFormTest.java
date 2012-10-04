package add.haslearntit.application.login;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.util.tester.FormTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.HasLearntItBaseTest;
import add.haslearntit.domain.user.User;
import add.haslearntit.domain.user.UserRepository;
import add.haslearntit.domain.user.UserWithLoginNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class LoginFormTest extends HasLearntItBaseTest{

    @Mock
    private UserRepository userRepository;
    
	private FormTester formTester;

	@Before
    public void setUp() throws Exception {

        tester.startComponentInPage(new LoginForm(userRepository));
        formTester = tester.newFormTester("loginForm:form");
    }
	
	@Test
	public void shouldValidateLoginField() throws Exception {
		
		// given:
		
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
        userWithLoginHasPassword("login", "validpassword");

        // when:
        formTester.setValue("login", "login");
        formTester.setValue("password", "validpassword");
        formTester.submit();

        // then:
        User user = (User) tester.getApplication().getSessionStore().getAttribute(RequestCycle.get().getRequest(), "currentUser");
        assertThat(user.getName(), equalTo("login"));
    }

    @Test
    public void shouldReturnAuthenticationErrorIfUserWithGivenLoginDoesNotExist() throws Exception {

        // given:
        userWithLoginDoesNotExist("missinglogin");
        
        // when:
        formTester.setValue("login", "missinglogin");
        formTester.setValue("password", "somepassword");
        formTester.submit();

        // then:
        tester.assertErrorMessages("Invalid username or password!");
    }

    @Test
    public void shouldReturnAuthenticationErrorIfInvalidPassword() throws Exception {

        // given:
        userWithLoginHasPassword("login", "validpassword");
        
        // when:
        formTester.setValue("login", "login");
        formTester.setValue("password", "invalidpassword");
        formTester.submit();

        // then:
        tester.assertErrorMessages("Invalid username or password!");
    }
    
    // --

    private void userWithLoginDoesNotExist(String login) {
        when(userRepository.loadByLogin(login)).thenThrow(new UserWithLoginNotFoundException(""));
    }

    private void userWithLoginHasPassword(String login, String password) {
        when(userRepository.loadByLogin(login)).thenReturn(new User(login, password));
    }
    
}
