package add.haslearntit.domain.entry;

import java.util.List;

public interface EntryRepository {

	public void store(Entry entry);

	public List<Entry> loadAll();

	public List<Entry> loadByNamePrefix(String namePrefix);
}