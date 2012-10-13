package add.haslearntit.application;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.junit.Test;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.application.login.LoginStatusPanel;
import add.haslearntit.application.topten.TopTenPanel;

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
		
		startTemplatePage();
		// then:
		tester.assertInvisible("messages");
	}

	
	@Test
	public void shouldContainLoginStatusPanel() throws Exception {
		
		startTemplatePage();
		// then:
		tester.assertComponent("loginStatusPanel", LoginStatusPanel.class);
	}
	
	
	@Test
	public void shouldContainTopTenList() throws Exception {
		startTemplatePage();
		// then:
		tester.assertComponent("topTen", TopTenPanel.class);
	}
	

	private void startTemplatePage() {
		TemplatePage page = new TemplatePage();
		tester.startPage(page);
	}
}
