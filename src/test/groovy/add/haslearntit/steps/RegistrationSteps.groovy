package add.haslearntit.steps

import geb.Browser
import add.haslearntit.pages.DashboardPage
import add.haslearntit.pages.RegistrationPage
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

Given(~'^I enter registration page$') { ->
	browser.to RegistrationPage
	browser.at RegistrationPage
}

When(~'^I submit registration data with login (.*)$') {login ->
	// Express the Regexp above with the code you wish you had
	browser.page.login(login)
	browser.page.password("pass")
	browser.page.submitRegister()
}

Then(~'^I am logged in as (.*)$') {login ->
	browser.at DashboardPage
	assert browser.page.loggedInAs.user == login
}

