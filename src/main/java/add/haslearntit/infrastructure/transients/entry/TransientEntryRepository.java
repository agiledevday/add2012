package add.haslearntit.infrastructure.transients.entry;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.text.StringStartsWith.startsWith;

import java.util.*;

import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryRepository;
import ch.lambdaj.function.convert.PropertyExtractor;

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
	
    @SuppressWarnings("unchecked")
    @Override
    public List<String> loadSkillNameByNamePrefix(String namePrefix) {
        final List<Entry> entries = filter(having(on(Entry.class).getName(), startsWith(namePrefix)), storage);
        final List<Entry> sortedEntries = sort(entries, on(Entry.class).getName());
        List<String> names = convert(sortedEntries, new PropertyExtractor("name"));
        Set<String> uniqueNames = new TreeSet<String>(names);
        List<String> uniqueNamesList = new ArrayList<String>(uniqueNames);
        final int toIndex = uniqueNamesList.size() < EntryRepository.MAX_SUGGESTIONS_RESULTS ?
                uniqueNamesList.size() : EntryRepository.MAX_SUGGESTIONS_RESULTS;
        return Collections.unmodifiableList(uniqueNamesList.subList(0, toIndex));
    }

}
