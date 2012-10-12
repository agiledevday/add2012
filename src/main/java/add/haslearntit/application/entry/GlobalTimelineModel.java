package add.haslearntit.application.entry;

import java.util.List;

import org.apache.wicket.model.LoadableDetachableModel;

import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryRepository;

public class GlobalTimelineModel extends LoadableDetachableModel<List<Entry>> implements TimelineModel {
    private static final long serialVersionUID = 2762850508755624887L;

    private final EntryRepository repository;

	public GlobalTimelineModel(EntryRepository repository) {
		this.repository = repository;
	}

	@Override
	protected List<Entry> load() {
		return repository.loadAll();
	}

}
