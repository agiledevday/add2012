package add.haslearntit.domain.entry;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public abstract class EntryRepositoryContractTest {

    protected EntryRepository repository;

    @Test
    public void shouldStoreAndLoadSkill() throws Exception {

        // given:
        Entry entry = anEntry();
        // when:
        repository.store(entry);
        // then:
        MatcherAssert.assertThat(repository.loadAll(), hasItem(entry));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldReturnedSkillsBeImmutable() throws Exception {

        // given:
        // when:
        repository.loadAll().add(anEntry());
        // then:
    }

    @Test
    public void shouldReturnsSkillsOrderedAlphabetically() {

        //given
        Entry[] skills = generateEntriesWithPrefix("Ja", 3);
        storeSkills(skills);
        //when
        final List<Entry> result = repository.loadByNamePrefix("Ja");
        //then
        Arrays.sort(skills, createBySkillNameComparator());
        assertThat(result).containsExactly(skills);
    }

    @Test
    public void shouldLimitSkillResults() {
        //given
        Entry[] skills = generateEntriesWithPrefix("Ja", 6);
        storeSkills(skills);
        //when
        final List<Entry> result = repository.loadByNamePrefix("Ja");
        //then
        Arrays.sort(skills, createBySkillNameComparator());
        assertThat(result).containsExactly(Arrays.copyOfRange(skills, 0, EntryRepository.MAX_SUGGESTIONS_RESULTS));
    }

    @Test
    public void shouldNotFailWhenThereIsNoElements() {
        //given
        Entry[] skills = generateEntriesWithPrefix("Any", 0);
        storeSkills(skills);
        //when
        final List<Entry> result = repository.loadByNamePrefix("Ja");
        //then
        assertThat(result).isNotNull().isEmpty();
    }


    @Test
    public void shouldNotFailWhenThereIsLessResultsThatLimit() {
        //given
        Entry[] skills = generateEntriesWithPrefix("Ja", 1);
        storeSkills(skills);
        //when
        final List<Entry> result = repository.loadByNamePrefix("Ja");
        //then
        assertThat(result).containsExactly(skills);
    }

    // --

    private Entry[] generateEntriesWithPrefix(String prefix, int numOfElems) {
        Entry[] generatedElements = new Entry[numOfElems];
        for (int i = 0; i < numOfElems; i++) {
            generatedElements[i] = anEntry(prefix + i);
        }
        return generatedElements;
    }

    private Entry anEntry() {
        return new Entry("entry", "difficultyLevel", "timeConsumed");
    }

    private Entry anEntry(String skill) {
        return new Entry(skill, "difficultyLevel", "timeConsumed");
    }

    private void storeSkills(Entry[] skills) {
        for (Entry s : skills) {
            repository.store(s);
        }
    }

    private Comparator<Entry> createBySkillNameComparator() {
        return new Comparator<Entry>() {
            @Override
            public int compare(Entry o1, Entry o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
    }
}