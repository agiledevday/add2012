package add.haslearntit.application.topten;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.util.ListModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import add.haslearntit.domain.entry.EntryPopularity;
import add.haslearntit.domain.entry.EntryRepository;

@SuppressWarnings("serial")
public class TopTenPanel extends Panel {

	@SpringBean
	EntryRepository entryRepository;

	public TopTenPanel(String id) {
		super(id);
		add(new ListView<EntryPopularity>("trendsList",
				new ListModel<EntryPopularity>(entryRepository.getTopTenList())) {

			@Override
			protected void populateItem(ListItem<EntryPopularity> listItem) {
				EntryPopularity entryPopularity = listItem.getModelObject();
				listItem.add(new Label("listElement", entryPopularity.toString()));
			}

		});
	}

}
