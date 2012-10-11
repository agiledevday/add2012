package add.haslearntit.application.entry;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.application.entry.GlobalTimelineModel;
import add.haslearntit.application.entry.TimelineModel;
import add.haslearntit.domain.entry.Entry;

@RunWith(MockitoJUnitRunner.class)
public class GlobalTimelineModelTest extends HasLearntItBaseWicketIT {

	private TimelineModel model;

	@Before
	public void setUp() {

		model = new GlobalTimelineModel(entryRepository);
	}
	
	@Test
	public void shouldListAllLearntSkills() throws Exception {
		
		// given:
		Entry someEntry = aEntry("cow milking");
		Entry otherEntry = aEntry("scuba diving");
		
		repositoryContainsSkills(someEntry, otherEntry);
		
		// when:
		List<Entry> actualEntries = model.getObject();
		
		// then:
		assertThat(actualEntries, hasItems(someEntry, otherEntry));
	}
	
	@Test
	public void shouldCacheResultList() throws Exception {
		
		// given:
		
		// when:
		model.getObject();
		model.getObject();
		
		// then:
		verify(entryRepository, times(1)).loadAll();
	}
	
	// --
	
	private void repositoryContainsSkills(Entry... someSkills) {
		when(entryRepository.loadAll()).thenReturn(asList(someSkills));
	}
	
	private Entry aEntry(String skill) {
		return new Entry(skill, "easy", "1 day");
	}
}
