package add.haslearntit.domain.entry;

import static org.fest.assertions.Assertions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public abstract class EntryRepositoryContractTest {

    protected EntryRepository repository;

    @Test
    public void shouldStoreAndLoadSkill() throws Exception {

        // given:
        Entry entry = anEntry();
        // when:
        repository.store(entry);
        // then:
        assertThat(repository.loadAll(), hasItem(entry));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldReturnedSkillsBeImmutable() throws Exception {

        // given:
        // when:
        repository.loadAll().add(anEntry());
        // then:
    }

    @Test
    public void shouldGetNullAsMostCommonDifficulty() throws Exception {
        // given
        // nothing

        // when
        final Difficulty result = repository.getMostCommonDifficultyBySkillName("skill");

        // then
        assertThat(result, CoreMatchers.is((Difficulty) null));
    }

    @Test
    public void shouldGetMostCommonDifficulty() throws Exception {
        // given
        Entry entry = newEntry("scala", "HARD");
        repository.store(entry);

        // when
        final Difficulty result = repository.getMostCommonDifficultyBySkillName("scala");

        // then
        assertThat(result).isEqualTo(Difficulty.HARD);
    }

    @Test
    public void shouldGetMostCommonDifficulty2() throws Exception {
        // given
        Entry entry = newEntry("scala", "EASY");
        repository.store(entry);

        // when
        final Difficulty result = repository.getMostCommonDifficultyBySkillName("scala");

        // then
        assertThat(result).isEqualTo(Difficulty.EASY);
    }

    @Test
    public void shouldNotReturnAnyEntry() throws Exception {
        // given
        // nothing

        // when
        List<Entry> result = repository.loadByName("skill");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldReturnEntriesBySkillName() throws Exception {
        // given
        final Entry scalaEntry = newEntry("scala");
        repository.store(scalaEntry);
        repository.store(newEntry("java"));

        // when
        List<Entry> result = repository.loadByName("scala");

        // then
        assertThat(result).containsOnly(scalaEntry);
    }

    // --

    private Entry newEntry(String name) {
        return new Entry(name, "HARD", "5");
    }

    private Entry newEntry(String name, String difficulty) {
        return new Entry(name, difficulty, "5");
    }

    private Entry anEntry() {
        return new Entry("entry", "difficultyLevel", "timeConsumed");
    }

}