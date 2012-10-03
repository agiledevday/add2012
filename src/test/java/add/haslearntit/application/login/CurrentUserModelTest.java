package add.haslearntit.application.login;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;

import org.apache.wicket.Application;
import org.apache.wicket.request.Request;
import org.apache.wicket.session.ISessionStore;
import org.apache.wicket.util.ValueProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.domain.user.User;

@RunWith(MockitoJUnitRunner.class)
public class CurrentUserModelTest {

	@Mock
	private Application application;
	@Mock
	private ISessionStore session;
	
	private CurrentUserModel currentUserModel;

	@Before
	public void setUp() throws Exception {

		currentUserModel = new SessionCurrentUserModel(application);
		
		application.setSessionStoreProvider(new ValueProvider<ISessionStore>(session));
	}
	
	@Test
	public void shouldGetCurrentUserFromSession() throws Exception {
		
		// given:
		sessionContainEntry("currentUser", new User("John Signedin"));
		
		// when:
		User user = currentUserModel.getObject();
		
		// then:
		assertThat(user.getName(), equalTo("John Signedin"));
	}

	@Test
	public void shouldGetAnonymousIfCurrentUserAbsent() throws Exception {
		
		// given:
		// when:
		User user = currentUserModel.getObject();
		
		// then:
		assertThat(user, equalTo(User.ANONYMOUS));
	}

	@Test
	public void shouldSetCurrentUser() throws Exception {
		
		// given:
		User user = new User("John Signedin");

		// when:
		currentUserModel.setObject(user);
		
		// then:
		verifySessionContainEntry("currentUser", user);
	}
	
	// --
	
	private void verifySessionContainEntry(String key, User object) {
		verify(session).setAttribute(Mockito.any(Request.class), Mockito.eq(key), Mockito.same(object));
	}

	private void sessionContainEntry(String key, Serializable object) {
		when(session.getAttribute(Mockito.any(Request.class), Mockito.eq(key))).thenReturn(object);
	}
}
