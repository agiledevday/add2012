package add.haslearntit.application;

import add.haslearntit.application.common.AppInfoPanel;
import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

import add.haslearntit.application.login.LoginStatusPanel;
import add.haslearntit.application.login.SessionCurrentUserModel;

public class TemplatePage extends WebPage {
    private static final long serialVersionUID = -943763663071297928L;

    public TemplatePage() {
		add(new FeedbackPanel("messages") {
            private static final long serialVersionUID = -7117768392120078835L;

            @Override
			public boolean isVisible() {
				return anyMessage();
			}
		});
		
		add(new LoginStatusPanel(new SessionCurrentUserModel(Application.get())));
        add(new AppInfoPanel("appInfo"));
	}

}
