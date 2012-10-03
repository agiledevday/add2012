package add.haslearntit.application;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import add.haslearntit.application.login.LoginStatusPanel;
import add.haslearntit.application.login.SessionCurrentUserModel;

public class TemplatePage extends WebPage {

	public TemplatePage() {
		add(new FeedbackPanel("messages") {
			@Override
			public boolean isVisible() {
				return anyMessage();
			}
		});
		
		add(new LoginStatusPanel(new SessionCurrentUserModel(Application.get())));
	}

}
