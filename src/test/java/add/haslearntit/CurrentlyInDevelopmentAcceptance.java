package add.haslearntit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;
import cucumber.junit.Cucumber.Options;

@RunWith(Cucumber.class)
@Options(features = {"src/test/resources/features", "src/test/resources/currentlyInDevelopment"},
        monochrome = true,
        tags = { "@currentlyInDevelopment" },
        format = { "pretty", "html:target/cucumber-html-report" })
public class CurrentlyInDevelopmentAcceptance {

    @BeforeClass
    public static void startServer() throws Exception {
        CucumberAcceptanceIT.startServer();
    }

    @AfterClass
    public static void shutdownServer() throws Exception {
        CucumberAcceptanceIT.shutdownServer();
    }
}
