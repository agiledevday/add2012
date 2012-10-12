package add.haslearntit.application.entry;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.wicket.model.util.ListModel;
import org.junit.Test;

import add.haslearntit.HasLearntItBaseWicketIT;
import add.haslearntit.domain.entry.Entry;

public class TimelinePanelTest extends HasLearntItBaseWicketIT {

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
        Entry someEntry = new Entry("java programming", "EASY", "1");

        // when
        tester.startComponentInPage(new TimelinePanel("learntSkillsList", modelContainingSkills(someEntry)));

        // then
        tester.assertLabel("learntSkillsList:list:0:skillPoints", Integer.toString(someEntry.getEarnedPoints()));
    }

    // --

    private TimelineModel modelContainingSkills(Entry... entries) {
        return new StaticTimelineModel(asList(entries));
    }

    private Entry aEntry(String name) {
        return new Entry(name, "easy", "1 minute");
    }

    public class StaticTimelineModel extends ListModel<Entry> implements TimelineModel {
        private static final long serialVersionUID = 2455476166327418908L;

        public StaticTimelineModel(List<Entry> entries) {
            super(entries);
        }

    }
}
