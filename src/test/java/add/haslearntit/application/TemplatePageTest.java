package add.haslearntit.application;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.junit.Test;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.application.login.LoginStatusPanel;

public class TemplatePageTest extends HasLearntItBaseWicketIT{

	@Test
	public void shouldContainFeedbackMessagesIfError() throws Exception {
		
		// given:
		TemplatePage page = new TemplatePage();
		page.error("some error");
		// when:
		tester.startPage(page);
		// then:
		tester.assertComponent("messages", FeedbackPanel.class);
	}
	
	@Test
	public void shouldNotContainFeedbackMessagesIfNoError() throws Exception {
		
		// given:
		TemplatePage page = new TemplatePage();
		// when:
		tester.startPage(page);
		// then:
		tester.assertInvisible("messages");
	}
	
	@Test
	public void shouldContainLoginStatusPanel() throws Exception {
		
		// given:
		TemplatePage page = new TemplatePage();
		// when:
		tester.startPage(page);
		// then:
		tester.assertComponent("loginStatusPanel", LoginStatusPanel.class);
	}
}
