package add.haslearntit.application.skills;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.verify;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import add.haslearntit.application.HasLearntItApplication;
import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepository;


@RunWith(MockitoJUnitRunner.class)
public class NewSkillPanelTest {

	@Mock
	private SkillsRepository skillsRepository;

	private WicketTester tester;
	private FormTester formTester;
	

	@Before
	public void setUp()
	{
		tester = new WicketTester(new HasLearntItApplication());
		tester.startComponentInPage(new NewSkillPanel("newSkillPanel",skillsRepository));
		
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
		verify(skillsRepository).store(argThat(
				hasSameMessageAs(
						aSkill(name, difficulty, time))));
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
	// --

	private void validForm() {
		String name = "testing components using WicketTester";
		String difficulty = "challenging";
		String time = "10 minutes";

		formTester.setValue("name", name);
		formTester.setValue("difficulty", difficulty);
		formTester.setValue("time", time);
	}
	
	private Skill aSkill(String name, String difficulty, String time) {
		return new Skill(name, difficulty, time);
	}

	protected Matcher<Skill> hasSameMessageAs(final Skill expectedSkill) {
		return new BaseMatcher<Skill>() {

			public boolean matches(Object arg0) {
				
				Skill skill = (Skill) arg0;
				return skill.asMessage().equals(expectedSkill.asMessage());
			}

			public void describeTo(Description arg0) {
			}
		};
	}
}
