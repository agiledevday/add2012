package add.haslearntit.domain.entry;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

import java.util.List;

import org.fest.assertions.Assertions;
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

    
    @Test
    public void shouldReturnEmptyListOfEntryPopularityWhenNoEntries() {
    	Assertions.assertThat(repository.getTopTenList()).isEmpty();
    }
    
    
    @Test
    public void shouldReturnProperTopTenList() {
    	//given:
        storeEntry("java");
        storeEntry("skala");
        storeEntry("skala");
        storeEntry("java");
        storeEntry("java");
        
        // when:
        List<EntryPopularity> topTenList = repository.getTopTenList();
        
        //then:
    	assertThat(topTenList).containsSequence(entryPopularity("java" , 3l), entryPopularity("skala", 2l));
    }

	private void storeEntry(String name) {
		repository.store(new Entry(name, "foo", "40"));
	}

	private EntryPopularity entryPopularity(String name, Long count) {
		return new EntryPopularity(name, count);
	}
	
    
    @Test
    public void shouldReturnListOfEntryPopularityNotGreaterThan10() {
    	
    	for (int i =0; i < 11; i++) {
    		storeEntry("java" + i);
    	}
    	
    	Assertions.assertThat(repository.getTopTenList()).hasSize(10);
    }
}