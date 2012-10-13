package add.haslearntit.infrastructure.transients.entry;

import static ch.lambdaj.Lambda.*;
import static org.hamcrest.text.StringStartsWith.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import add.haslearntit.domain.entry.Difficulty;
import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryRepository;
import add.haslearntit.infrastructure.hibernate.entry.MostCommonDifficulty;

public class TransientEntryRepository implements EntryRepository {

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

    public void clear() {
        storage.clear();
    }

    @Override
    public List<Entry> loadByNamePrefix(String namePrefix) {
        return Collections.unmodifiableList(filter(startsWith(namePrefix), storage));
    }

    @Override
    public Difficulty getMostCommonDifficultyBySkillName(String skillName) {
        List<Entry> loaded = loadByName(skillName);
        return new MostCommonDifficulty().selectMostCommonDifficultyFrom(loaded);
    }

    @Override
    public List<Entry> loadByName(String skillName) {
        final List<Entry> result = new ArrayList<Entry>();
        final List<Entry> data = loadAll();
        for (final Entry entry : data) {
            if (entry.getName().equals(skillName)) {
                result.add(entry);
            }
        }

        return result;
    }
}
