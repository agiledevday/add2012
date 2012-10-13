package add.haslearntit.infrastructure.transients.entry;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.text.StringStartsWith.startsWith;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryRepository;

public class TransientEntryRepository implements EntryRepository{

	private List<Entry> storage;
	
	public TransientEntryRepository() {
		storage = new ArrayList<Entry>();
	}

	public void store(Entry entry) {
		storage.add(entry);
	}

	public List<Entry> loadAll() {
		return Collections.unmodifiableList(storage);
	}

	public void clear(){
		storage.clear();
	}
	
    @Override
    public List<Entry> loadByNamePrefix(String namePrefix) {
        final List<Entry> entries = filter(having(on(Entry.class).getName(), startsWith(namePrefix)), storage);
        final List<Entry> sortedEntries = sort(entries, on(Entry.class).getName());
        return Collections.unmodifiableList(sortedEntries);
    }

}
