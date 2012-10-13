package add.haslearntit.domain.entry;

import java.util.List;

public interface EntryRepository {

    int MAX_SUGGESTIONS_RESULTS = 5;

    void store(Entry entry);

    List<Entry> loadAll();

    List<String> loadSkillNameByNamePrefix(String namePrefix);
}