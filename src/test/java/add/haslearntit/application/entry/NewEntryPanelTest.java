package add.haslearntit.application.entry;

import static org.fest.assertions.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

import java.util.Date;
import java.util.List;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.util.tester.FormTester;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.domain.entry.Difficulty;
import add.haslearntit.domain.entry.Entry;

@RunWith(MockitoJUnitRunner.class)
public class NewEntryPanelTest extends HasLearntItBaseWicketIT {

    private FormTester formTester;

    @Before
    public void setUp()
    {
        tester.startComponentInPage(new NewEntryPanel("newSkillPanel"));
        formTester = tester.newFormTester("newSkillPanel:newSkillForm");
    }

    @Test
    public void shouldReturnErrorMessageWhenInvalidTimeField() throws Exception {
        // given:
        String name = "testing components using WicketTester";
        String difficulty = "challenging";
        String time = "10 minutes";

        formTester.setValue("name", name);
        formTester.setValue("difficulty", difficulty);
        formTester.setValue("time", time);

        // when:
        formTester.submit();

        // then:
        tester.assertErrorMessages("Please enter correct time in hours!");
    }

    @Test
    public void shouldReturnErrorMessageWhenTimeFieldIsOutOfRange() throws Exception {
        // given:
        String name = "testing components using WicketTester";
        String difficulty = "challenging";
        String time = "0";

        formTester.setValue("name", name);
        formTester.setValue("difficulty", difficulty);
        formTester.setValue("time", time);

        // when:
        formTester.submit();

        // then:
        tester.assertErrorMessages("Provided time is out of range 1-8760!");
    }

    @Test
    public void shouldSaveNewSkill() throws Exception {

        // given:
        String name = "testing components using WicketTester";
        String difficulty = difficultyChoiceValue(Difficulty.MEDIUM);
        String time = "10";

        formTester.setValue("name", name);
        formTester.setValue("difficulty", difficulty);
        formTester.setValue("time", time);

        // when:
        formTester.submit();

        // then:
        verify(entryRepository).store(
                argThat(hasSameMessageAs(aEntry(name, Difficulty.MEDIUM.name(), time))));
    }

    @Test
    public void shouldValidateSkillNameIsRequired() throws Exception {

        // given:
        validForm();
        formTester.setValue("name", "");

        // when:
        formTester.submit();

        // then:
        tester.assertErrorMessages("You have to provide skill description!");
    }

    @Test
    public void shouldValidateSkillTimeIsRequired() throws Exception {

        // given:
        validForm();
        formTester.setValue("time", "");

        // when:
        formTester.submit();

        // then:
        tester.assertErrorMessages("You have provide info about how difficult it was!");
    }

    @Test
    public void shouldClearFormAfterSubmit() throws Exception {
        // given:
        validForm();

        // when:
        formTester.submit();

        // then:
        assertThat(formTester.getTextComponentValue("name"), equalTo(""));
        assertThat(getDifficultyDropDown().getValue(), equalTo(String.valueOf(getDifficultyChoices().indexOf(Difficulty.MEDIUM.name()))));
        assertThat(formTester.getTextComponentValue("time"), equalTo(""));
    }

    @Test
    public void shouldListDifficultiesLevels() {
        // given:

        // when:

        // then:
        assertThat(getDifficultyChoices()).containsExactly("EASY", "MEDIUM", "HARD");
    }

	private Entry aEntry(String name, String difficulty, String time) {
		return new Entry(name, difficulty, time, new Date());
	}
	
    @SuppressWarnings("unchecked")
    private DropDownChoice<String> getDifficultyDropDown() {
        return (DropDownChoice<String>) tester.getComponentFromLastRenderedPage("newSkillPanel:newSkillForm:difficulty");
    }

    @SuppressWarnings("unchecked")
    private List<String> getDifficultyChoices() {
        return (List<String>) getDifficultyDropDown().getChoices();
    }

    // --

    private void validForm() {
        String name = "testing components using WicketTester";
        String difficulty = "challenging";
        String time = "10";

        formTester.setValue("name", name);
        formTester.setValue("difficulty", difficulty);
        formTester.setValue("time", time);
    }

    protected Matcher<Entry> hasSameMessageAs(final Entry expectedEntry) {
        return new BaseMatcher<Entry>() {

            public boolean matches(Object arg0) {

                Entry entry = (Entry) arg0;
                return entry.asMessage().equals(expectedEntry.asMessage());
            }

            public void describeTo(Description arg0) {
            }
        };
    }

    private String difficultyChoiceValue(Difficulty difficulty) {
        return String.valueOf(getDifficultyChoices().indexOf(difficulty.name()));
    }

}
