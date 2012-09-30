package add.haslearntit.steps

import geb.Browser
import add.haslearntit.login.LoginPage
import add.haslearntit.pages.UserSkillsPage
import cucumber.runtime.PendingException
this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

def browser
def failures = 0;

Before { browser = new Browser() }

After{ scenario ->

	if(scenario.failed){
		browser.report("failed" + failures++);
	}
}

Given(~'^I enter login screen$') {
	->

	browser.to LoginPage
	browser.at LoginPage
}

When(~'^I enter valid login and password$') {
	->

	browser.at LoginPage
	browser.page.enterLoginAndPassword("tomek", "abc123");
	browser.page.submitLoginForm();
}

Then(~'^I should see my user skills page$') {
	->

	browser.at UserSkillsPage
}

When(~'^I enter valid login and no password$') {
	->

	browser.at LoginPage
	browser.page.enterLoginAndPassword("tomek", "");
	browser.page.submitLoginForm();
}

Then(~'^I should see error with no password$') {
	->
	browser.at LoginPage
	browser.page.checkError("Invalid password");
}

When(~'^I enter no login and password$') {
	->

	browser.at LoginPage
	browser.page.enterLoginAndPassword("", "abc123");
	browser.page.submitLoginForm();
}

Then(~'^I should see error with no login$') {
	->
	browser.at LoginPage
	browser.page.checkError("Invalid login");
}
