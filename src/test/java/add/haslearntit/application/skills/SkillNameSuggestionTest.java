package add.haslearntit.application.skills;

import add.haslearntit.HasLearntItBaseWicketIT;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.behavior.AbstractAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.AbstractAutoCompleteBehavior;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTesterHelper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SkillNameSuggestionTest extends HasLearntItBaseWicketIT {

	@Test
	@Ignore //doesn't work - http://apache-wicket.1842946.n4.nabble.com/Problem-with-testing-text-field-auto-completion-with-WicketTester-td4652722.html
	public void shouldShowSuggestionPopup() throws InterruptedException {
	    //given
		tester.startPage(UserSkillsPage.class);
		//when

//		TextField textField = (TextField) tester.getComponentFromLastRenderedPage("newSkillForm:newSkillForm:name");
//		System.out.println(textField.getValue());
//		textField.getModel().setObject("ja");
//		tester.executeAjaxEvent(textField, "onchange");

		FormTester formTester = tester.newFormTester("newSkillForm:newSkillForm");
		formTester.setValue("name", "ja");
//		tester.executeAjaxEvent("newSkillForm:newSkillForm:name", "onkeyup");
//		System.out.println("value: " + formTester.getTextComponentValue("name"));

		AbstractAutoCompleteBehavior behavior = (AbstractAutoCompleteBehavior) WicketTesterHelper.
				findBehavior(tester.getComponentFromLastRenderedPage("newSkillForm:newSkillForm:name"),
						AbstractAutoCompleteBehavior.class);
		tester.executeBehavior(behavior);
		//then
		tester.assertContains("Jacoco");
	}

	@Test
	@Ignore //it passes, but doesn't work properly
	public void shouldNotShowPopupWhenNoSuggestionAvailable() {
		//given
		tester.startPage(UserSkillsPage.class);
		//when
		FormTester formTester = tester.newFormTester("newSkillForm:newSkillForm");
		formTester.setValue("name", "cz");
		AbstractAutoCompleteBehavior behavior = (AbstractAutoCompleteBehavior) WicketTesterHelper.
				findBehavior(tester.getComponentFromLastRenderedPage("newSkillForm:newSkillForm:name"),
						AbstractAutoCompleteBehavior.class);
		tester.executeBehavior(behavior);
		//then
		tester.assertContainsNot("Jacoco");
	}
}
