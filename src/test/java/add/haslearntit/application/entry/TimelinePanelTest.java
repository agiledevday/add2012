package add.haslearntit.application.entry;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.wicket.model.util.ListModel;
import org.junit.Test;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.domain.entry.Entry;

public class TimelinePanelTest extends HasLearntItBaseWicketIT {

    private static final String LEARNT_SKILL_LIST_ID = "learntSkillList";
	private static final String FIRST_SKILL_PREFIX = LEARNT_SKILL_LIST_ID + ":list:0:";

	@Test
    public void shouldDisplayAllLearntSkills() throws Exception {

        // given:
        Entry someEntry = aEntry("java programming");
        Entry otherEntry = aEntry("sky diving");

        // when:
        tester.startComponentInPage(new TimelinePanel("learntSkillsList", modelContainingSkills(someEntry, otherEntry)));

        // then:
        tester.assertContains(someEntry.asMessage());
        tester.assertContains(otherEntry.asMessage());
    }

    @Test
    public void shouldDisplayEncouragementMessageIfUserHasNoSkills() throws Exception {

        // given:

        // when:
        tester.startComponentInPage(new TimelinePanel("learntSkillsList", modelContainingSkills()));

        // then:
        tester.assertContains("You haven't recorded any skills. For sure there is something you have learnt lately!");
    }

    @Test
    public void shouldHideEncouragementMessageIfUserHasAlLeastOneSkill() throws Exception {

        // given:

        // when:
        tester.startComponentInPage(new TimelinePanel("learntSkillsList", modelContainingSkills(aEntry("skuba diving"))));

        // then:
        tester.assertContainsNot("You haven't recorded any skills. For sure there is something you have learnt lately!");
    }

    @Test
    public void shouldDisplayLearnPoints() {
        // given
        Entry someEntry = new Entry("java programming", "EASY", "1", new Date());

        // when
        tester.startComponentInPage(new TimelinePanel(LEARNT_SKILL_LIST_ID, modelContainingSkills(someEntry)));

        // then
        tester.assertLabel(FIRST_SKILL_PREFIX + "skillPoints", Integer.toString(someEntry.getEarnedPoints()));
    }
    
    @Test
	public void shouldDisplayCreationDate() throws Exception {
    	// given
    	String dateString = "2012-10-13 13:00:01";
		Entry entry = new Entry("java", "EASY", "1", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString));
    	
		// when
    	tester.startComponentInPage(new TimelinePanel(LEARNT_SKILL_LIST_ID, modelContainingSkills(entry)));
    	
    	// then
    	tester.assertLabel(FIRST_SKILL_PREFIX + "creationDate", dateString);
	}

    // --

    private TimelineModel modelContainingSkills(Entry... entries) {
        return new StaticTimelineModel(asList(entries));
    }

    private Entry aEntry(String name) {
        return new Entry(name, "easy", "1", new Date());
    }

    public class StaticTimelineModel extends ListModel<Entry> implements TimelineModel {
        private static final long serialVersionUID = 2455476166327418908L;

        public StaticTimelineModel(List<Entry> entries) {
            super(entries);
        }

    }
}
