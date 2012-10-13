package add.haslearntit.infrastructure.transients.entry;

import static ch.lambdaj.Lambda.filter;
import static org.hamcrest.text.StringStartsWith.startsWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryPopularity;
import add.haslearntit.domain.entry.EntryRepository;

public class TransientEntryRepository implements EntryRepository {

	private List<Entry> storage;
	private List<EntryPopularity> entryPopularities = new LinkedList<EntryPopularity>();

	public TransientEntryRepository() {
		storage = new ArrayList<Entry>();
	}

	public void store(Entry entry) {
		storage.add(entry);
		getOrCreatePopularityFor(entry).incrementCount();
	}

	private EntryPopularity getOrCreatePopularityFor(Entry entry) {
		for (EntryPopularity entryPopularity : entryPopularities) {
			if (entryPopularity.isFor(entry)) {
				return entryPopularity;
			}
		}
		EntryPopularity entryPopularity = new EntryPopularity(entry.getName(), 0);
		entryPopularities.add(entryPopularity);
		return entryPopularity;
	}

	public List<Entry> loadAll() {
		return Collections.unmodifiableList(storage);
	}

	public void clear() {
		storage.clear();
		entryPopularities.clear();
	}

	@Override
	public List<Entry> loadByNamePrefix(String namePrefix) {
		return Collections.unmodifiableList(filter(startsWith(namePrefix),
				storage));
	}

	@Override
	public List<EntryPopularity> getTopTenList() {
		Collections.sort(entryPopularities);
		if (entryPopularities.size() < 10)
			return entryPopularities;
		return entryPopularities.subList(0, 10);
	}

}
