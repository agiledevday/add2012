package add.haslearntit.domain.entry;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.Date;

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
        Entry[] skills = new Entry[]{anEntry("Java"), anEntry("Jacoco"), anEntry("Jabber")};
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
        Entry[] skills = new Entry[]{anEntry("Java"), anEntry("Jacoco"), anEntry("Jabber"), anEntry("Java-GWT"),
                anEntry("Java-AppEngine"),anEntry("Java-Python")};
        storeSkills(skills);
        //when
        final List<Entry> result = repository.loadByNamePrefix("Ja");
        //then
        Arrays.sort(skills, createBySkillNameComparator());
        assertThat(result).containsExactly(Arrays.copyOfRange(skills, 0, EntryRepository.MAX_SUGGESTIONS_RESULTS));
    }

    @Test
    public void shouldNotFailWhenThereIsLessResultsThatLimit() {
        //given
        Entry[] skills = new Entry[]{};
        storeSkills(skills);
        //when
        final List<Entry> result = repository.loadByNamePrefix("Ja");
        //then
        assertThat(result).isNotNull().isEmpty();
    }

    // --           

    private Entry anEntry() {
        return anEntry("entry");
    }

    private Entry anEntry(String skill) {
        return new Entry(skill, "difficultyLevel", "timeConsumed", new Date());
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
