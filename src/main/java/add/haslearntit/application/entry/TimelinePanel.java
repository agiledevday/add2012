package add.haslearntit.application.entry;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import add.haslearntit.domain.entry.Entry;

public class TimelinePanel extends Panel{

	private static final long serialVersionUID = -9134642394089794933L;
	
	private final TimelineModel model;

	public TimelinePanel(String id, TimelineModel model) {
	
		super(id);
		this.model = model;
		
		initializeComponents();
	}

	private void initializeComponents() {

		initializeEncouragement();
		initializeSkillsList();
	}

	private void initializeSkillsList() {
		
		add(new ListView<Entry>("list", model){

			@Override
			protected void populateItem(ListItem<Entry> item) {
				item.add(new Label("skill", item.getModelObject().asMessage()));
				item.add(new Label("skillPoints", String.valueOf(item.getModelObject().getEarnedPoints())));
			}
		});
	}

	private void initializeEncouragement() {
		
		add(new WebMarkupContainer("encouragement"){
			
			@Override
			public boolean isVisible() {
				return model.getObject().size() == 0;
			}
		});
	}

}
