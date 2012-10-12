package add.haslearntit;

import org.eclipse.jetty.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;
import cucumber.junit.Cucumber.Options;

@RunWith(Cucumber.class)
@Options(
        features = "src/test/resources/features",
        tags = "~@pending",
        format = { "pretty", "html:target/cucumber-html-report" }
        )
public class CucumberAcceptanceIT {

    public static Server server;

    @BeforeClass
    public static void startServer() throws Exception{
        
        server = EmbeddedServer.createServer();
        server.start();
        
        System.err.println("--------------------------- Server for acceptance tests started --");
    }
    
    @AfterClass
    public static void shutdownServer() throws Exception{
        
        server.stop();
        server.join();
        
        System.err.println("--------------------------- Server for acceptance tests stopped --");
    }
}
