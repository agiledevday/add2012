package add.haslearntit.application.topten;

import java.util.Arrays;
import java.util.List;

import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;
import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryPopularity;


@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("rawtypes")
public class TopTenPanelTest extends HasLearntItBaseWicketIT {

	@Test
	public void shouldDisplayTopTenList() {
		List<EntryPopularity> entries = Arrays.asList(new EntryPopularity("name", 40l));
		Mockito.when(entryRepository.getTopTenList()).thenReturn(entries);
		
		tester.startComponentInPage(TopTenPanel.class);
		
		ListModel<Entry> model = getEntriesModel();
		Assertions.assertThat(model.getObject()).isEqualTo(entries); 
		tester.assertContains("name\\(40\\)");
	}

	@SuppressWarnings("unchecked")
	private ListModel<Entry> getEntriesModel() {
		ListView trendsListview =  (ListView) tester.getComponentFromLastRenderedPage("trendsList");
		return (ListModel<Entry>) trendsListview.getModel();
	}

	
	
	
}
