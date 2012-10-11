package add.haslearntit.domain.entry;

import java.util.List;

public interface EntryRepository {

	public abstract void store(Entry entry);

	public abstract List<Entry> loadAll();

}