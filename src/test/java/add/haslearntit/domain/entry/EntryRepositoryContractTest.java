package add.haslearntit.domain.entry;

import static ch.lambdaj.Lambda.convert;
import static org.fest.assertions.Assertions.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import ch.lambdaj.function.convert.PropertyExtractor;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

import java.util.*;

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
        List<Entry> skills = generateEntriesWithPrefix("Ja", 3);
        storeSkills(skills);
        //when
        final List<String> result = repository.loadSkillNameByNamePrefix("Ja");
        //then
        List<String> skillsNames = convertSkillsToSkillNames(skills);
        Collections.sort(skillsNames);
        assertThat(result).containsExactly(skillsNames.toArray());
    }

    @Test
    public void shouldReturnDistinctElements() {
        //given
        List<Entry> skills = generateTheSameEntriesWithPrefix("Ja", 2);
        skills.add(anEntry("Jacoco"));
        storeSkills(skills);
        //when
        final List<String> result = repository.loadSkillNameByNamePrefix("Ja");
        //then
        assertThat(result).containsExactly("Ja", "Jacoco");
    }

    @Test
    public void shouldReturnDistinctElementsEvenWhenNumberGreaterThanLimit() {
        //given
        List<Entry> jaSkills = generateTheSameEntriesWithPrefix("Ja", EntryRepository.MAX_SUGGESTIONS_RESULTS);
        List<Entry> jacocoSkills = generateTheSameEntriesWithPrefix("JaCoco", 2);
        storeSkills(jaSkills);
        storeSkills(jacocoSkills);
        //when
        final List<String> result = repository.loadSkillNameByNamePrefix("Ja");
        //then
        assertThat(result).containsExactly("Ja", "Jacoco");
    }

    @Test
    public void shouldLimitSkillResults() {
        //given
        List<Entry> skills = generateEntriesWithPrefix("Ja", 6);
        storeSkills(skills);
        //when
        final List<String> result = repository.loadSkillNameByNamePrefix("Ja");
        //then
        Collections.sort(skills, createBySkillNameComparator());
        final List<Entry> limitedSkills = skills.subList(0, EntryRepository.MAX_SUGGESTIONS_RESULTS);
        assertThat(result).containsExactly(
                convertSkillsToSkillNames(limitedSkills).toArray(new String[limitedSkills.size()]));
    }

    @Test
    public void shouldNotFailWhenThereIsNoElements() {
        //given
        List<Entry> skills = generateEntriesWithPrefix("Any", 0);
        storeSkills(skills);
        //when
        final List<String> result = repository.loadSkillNameByNamePrefix("Ja");
        //then
        assertThat(result).isNotNull().isEmpty();
    }

    @Test
    public void shouldNotFailWhenThereIsLessResultsThatLimit() {
        //given
        List<Entry> skills = generateEntriesWithPrefix("Ja", 1);
        storeSkills(skills);
        //when
        final List<String> result = repository.loadSkillNameByNamePrefix("Ja");
        //then
        assertThat(result).containsExactly(convertSkillsToSkillNames(skills).toArray());
    }

    // --

    @SuppressWarnings("unchecked")
    private List<String> convertSkillsToSkillNames(List<Entry> skills) {
        return convert(skills, new PropertyExtractor("name"));
    }

    private List<Entry> generateEntriesWithPrefix(String prefix, int numOfElements) {
        List<Entry> generatedElements = new ArrayList<Entry>();
        for (int i = 0; i < numOfElements; i++) {
            generatedElements.add(anEntry(prefix + i));
        }
        return generatedElements;
    }

    //TODO: Remove duplication
    private List<Entry> generateTheSameEntriesWithPrefix(String prefix, int numOfElements) {
        List<Entry> generatedElements = new ArrayList<Entry>();
        for (int i = 0; i < numOfElements; i++) {
            generatedElements.add(anEntry(prefix));
        }
        return generatedElements;
    }


    private Entry anEntry() {
        return new Entry("entry", "difficultyLevel", "timeConsumed");
    }

    private Entry anEntry(String skill) {
        return new Entry(skill, "difficultyLevel", "timeConsumed");
    }

    private void storeSkills(List<Entry> skills) {
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