package add.haslearntit.application.skills;

import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AbstractAutoCompleteBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AutoCompleteSettings;
import org.apache.wicket.util.tester.WicketTesterHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.application.entry.DashboardPage;
import add.haslearntit.domain.entry.Entry;

@RunWith(MockitoJUnitRunner.class)
public class SkillNameSuggestionTest extends HasLearntItBaseWicketIT {

	@Before
	public void skillsInRepository() {
		when(entryRepository.loadByNamePrefix("ja")).thenReturn(Arrays.asList(new Entry("Jacoco", null, null)));
		tester.startPage(DashboardPage.class);
	}
	
	@Test
	public void shouldShowSuggestionPopup() throws InterruptedException {
	    //given
		userEntersSkillNamPart("ja");
		//when
		autocompleteIsTriggeredOn("newSkillForm:newSkillForm:name");
		//then
		tester.assertContains("Jacoco");
	}

	@Test
	public void shouldNotShowPopupWhenNoSuggestionAvailable() {
		//given
		userEntersSkillNamPart("cz");
		//when
		autocompleteIsTriggeredOn("newSkillForm:newSkillForm:name");
		//then
		tester.assertContainsNot("Jacoco");
	}

	private void autocompleteIsTriggeredOn(String inputPath) {
		AbstractAutoCompleteBehavior behavior = (AbstractAutoCompleteBehavior) WicketTesterHelper.
				findBehavior(tester.getComponentFromLastRenderedPage(inputPath),
						AbstractAutoCompleteBehavior.class);
		tester.executeBehavior(behavior);
	}
	
	private void userEntersSkillNamPart(String skillNamePart) {
		tester.getRequest().setParameter(getAutocompleteParameterName(), skillNamePart);
	}

	private String getAutocompleteParameterName() {
		return new AutoCompleteSettings().getParameterName();
	}

}
