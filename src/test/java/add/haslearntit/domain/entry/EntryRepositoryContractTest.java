package add.haslearntit.domain.entry;

import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

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

    // --

    private Entry anEntry() {
        return new Entry("entry", "difficultyLevel", "timeConsumed");
    }

}