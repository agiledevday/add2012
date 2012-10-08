package add.haslearntit.application.skills;

import java.util.Arrays;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.domain.skills.Skill;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AbstractAutoCompleteBehavior;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTesterHelper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SkillNameSuggestionTest extends HasLearntItBaseWicketIT {

	@Before
	public void skillsInRepository() {
		when(skillsRepository.loadByNamePrefix("ja")).thenReturn(Arrays.asList(new Skill("Jacoco", null, null)));
		tester.startPage(UserSkillsPage.class);
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

	private void userEntersSkillNamPart(String skillNamePart) {
		tester.getRequest().setParameter("q", skillNamePart);
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
}
