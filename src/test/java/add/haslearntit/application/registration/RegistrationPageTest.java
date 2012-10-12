package add.haslearntit.application.registration;

import static org.junit.Assert.*;

import org.junit.Test;

import add.haslearntit.HasLearntItBaseWicketIT;

public class RegistrationPageTest extends HasLearntItBaseWicketIT{
	
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
	

}
