package add.haslearntit.application.entry;

import org.apache.wicket.spring.injection.annot.SpringBean;

import add.haslearntit.application.TemplatePage;
import add.haslearntit.domain.entry.EntryRepository;

public class DashboardPage extends TemplatePage {
    private static final long serialVersionUID = 6274703983167941772L;

    @SpringBean
	private EntryRepository entryRepository;

	public DashboardPage() {

		super();

		initializeComponents();
	}

	private void initializeComponents() {
		
		add(new NewEntryPanel("newSkillForm"));
		add(new TimelinePanel("learntSkillsList", new GlobalTimelineModel(entryRepository)));

	}
}
