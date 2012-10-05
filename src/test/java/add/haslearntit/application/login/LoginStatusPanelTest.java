package add.haslearntit.application.login;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.HasLearntItBaseTest;
import add.haslearntit.domain.user.User;

@RunWith(MockitoJUnitRunner.class)
public class LoginStatusPanelTest extends HasLearntItBaseTest{

	@Mock
	private CurrentUserModel currentUserModel;

	@Before
	public void setUp() throws Exception {

		tester.startComponentInPage(new LoginStatusPanel(currentUserModel));
	}
	
	@Test
	public void shouldDisplayNameOfCurrentUser() throws Exception {
		
		// given:
		currentUserModelContain(new User("fancyPants"));
		
		// when:
		// then:
		tester.assertLabel("loginStatusPanel:currentUser", "fancyPants");
	}

	// --
	
	private void currentUserModelContain(User user) {
		when(currentUserModel.getObject()).thenReturn(user);
	}
}
