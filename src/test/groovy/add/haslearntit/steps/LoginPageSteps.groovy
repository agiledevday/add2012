package add.haslearntit.steps

import geb.Browser
import add.haslearntit.pages.LoginPage
import add.haslearntit.pages.UserSkillsPage
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

Given(~'^I have been logged in successfully$') {
    ->

    browser.at LoginPage
    browser.page.enterLoginAndPassword("tomek", "abc123");
    browser.page.submitLoginForm();
}

When(~'^I enter any page$') { -> browser.to UserSkillsPage }

Given(~'^I am not logged in$') {
    ->
}

Given(~'^I have been logged in successfully as \'(.*)\'$') { String username ->

    browser.at LoginPage
    browser.page.enterLoginAndPassword(username, "abc123");
    browser.page.submitLoginForm();
}

Then(~'^I should see that I am logged in as \'(.*)\'$') { String username ->
    assert browser.page.loggedInAs
    assert browser.page.loggedInAs.user == username
}

When(~'^I sign in with following credentials (.*)/(.*)$') { String login, String password ->

    browser.at LoginPage
    browser.page.enterLoginAndPassword(login, password);
    browser.page.submitLoginForm();
}

Then(~'^login form should contain error \'(.*)\'$') { String error ->
    assert browser.page.messages.entries.contains(error);
}


