package add.haslearntit.application.common;

import add.haslearntit.HasLearntItBaseWicketIT;
import org.junit.Test;

public class AppInfoPanelTest extends HasLearntItBaseWicketIT {

    @Test
    public void shouldGetAppInfoFromSpringAndDisplayIt() {
        //given
        //when
        tester.startComponentInPage(new AppInfoPanel("appInfo"));
        //then
        tester.assertContains(APP_INFO_TEST_TEXT);
    }
}
