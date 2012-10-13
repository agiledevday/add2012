package add.haslearntit.domain.entry;

import java.util.List;

public interface EntryRepository {

	int MAX_SUGGESTIONS_RESULTS = 5;

	public void store(Entry entry);

	public List<Entry> loadByNamePrefix(String namePrefix);

	public List<EntryPopularity> getTopTenList();

	public List<Entry> loadAll();

	public List<String> loadSkillNameByNamePrefix(String namePrefix);

}