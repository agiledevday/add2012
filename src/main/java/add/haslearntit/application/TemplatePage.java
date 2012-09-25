package add.haslearntit.application;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class TemplatePage extends WebPage{

	public TemplatePage() {
		add(new FeedbackPanel("messages"){
			@Override
			public boolean isVisible() {
				return anyMessage();
			}
		});
	}
	
}
