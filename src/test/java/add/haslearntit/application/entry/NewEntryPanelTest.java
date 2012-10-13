package add.haslearntit.application.entry;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

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

import com.google.common.collect.Lists;

@RunWith(MockitoJUnitRunner.class)
public class NewEntryPanelTest extends HasLearntItBaseWicketIT {

	private FormTester formTester;
    private NewEntryPanel newEntryPanel;

	@Before
	public void setUp()
	{
        newEntryPanel = new NewEntryPanel("newSkillPanel");
        tester.startComponentInPage(newEntryPanel);
		formTester = tester.newFormTester("newSkillPanel:newSkillForm");
	}
	
	@Test
	public void shouldSaveNewSkill() throws Exception {
		
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
		verify(entryRepository).store(argThat(
				hasSameMessageAs(
						aEntry(name, difficulty, time))));
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
	public void shouldValidateSkillDifficultyIsRequired() throws Exception {
		
		// given:
		validForm();
		formTester.setValue("difficulty", "");
		
		// when:
		formTester.submit();
		
		// then:
		tester.assertErrorMessages("You have to say how difficult it was!");
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
		assertThat(formTester.getTextComponentValue("difficulty"), equalTo(""));
		assertThat(formTester.getTextComponentValue("time"), equalTo(""));
	}
	
	@Test
	public void shouldSuggestMostCommonDifficultyGivenSkillName() throws Exception {
        assertDifficultySuggestion(Difficulty.HARD, "hard");
	}


    @Test
    public void shouldSuggestNothing() throws Exception {
        assertDifficultySuggestion(null, "");
    }

    private void assertDifficultySuggestion(Difficulty myNull, String operand) {
        String name = "scala";
        when(entryRepository.getMostCommonDifficultyBySkillName(name)).thenReturn(myNull);

        // when
        formTester.setValue("name", name);
        tester.executeAjaxEvent(newEntryPanel.skillNameTextField, "onchange");

        // then
        assertThat(formTester.getTextComponentValue("difficulty"), equalTo(operand));
    }
	
	@Test
	public void learningTest() throws Exception {
		System.out.println(entryRepository.loadAll());
		when(entryRepository.loadAll()).thenReturn(Lists.<Entry>newArrayList(mock(Entry.class)));
		System.out.println(entryRepository.loadAll());
	}
	
	// --

	private void validForm() {
		String name = "testing components using WicketTester";
		String difficulty = "challenging";
		String time = "10 minutes";

		formTester.setValue("name", name);
		formTester.setValue("difficulty", difficulty);
		formTester.setValue("time", time);
	}
	
	private Entry aEntry(String name, String difficulty, String time) {
		return new Entry(name, difficulty, time);
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
}
