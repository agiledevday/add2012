package add.haslearntit.application.login;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.domain.user.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class LoginPageTest extends HasLearntItBaseWicketIT{

    @Mock
    private UserRepository userRepository;
    
    @Override
    protected void setupApplicationContext() {
        applicationContext.putBean("userRepository", userRepository);
    }
    
	@Test
	public void shouldContainLoginForm() throws Exception {
		
		// given:
		// when:
		tester.startPage(LoginPage.class);
		// then:
		tester.assertComponent("loginForm", LoginForm.class);
	}
	
}
