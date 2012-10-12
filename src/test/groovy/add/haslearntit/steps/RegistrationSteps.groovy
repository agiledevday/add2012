package add.haslearntit.steps

import geb.Browser
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