
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver


	reportsDir = "target/geb-reports"
	
	driver = {
		def driver = new HtmlUnitDriver()
		driver.javascriptEnabled = true
		driver
	}
	
	environments {
	
		// run as “mvn -Dgeb.env=chrome integration-test”
		// See: http://code.google.com/p/selenium/wiki/ChromeDriver
		chrome {
			driver = { new ChromeDriver() }
		}
	
		// run as “mvn -Dgeb.env=firefox integration-test”
		// See: http://code.google.com/p/selenium/wiki/FirefoxDriver
		firefox {
			driver = { new FirefoxDriver() }
		}
	
	}
