package add.haslearntit.steps

import geb.Browser
import add.haslearntit.domain.user.User
import add.haslearntit.domain.user.UserRepository
import add.haslearntit.hooks.Context
import add.haslearntit.pages.LoginPage
import add.haslearntit.pages.DashboardPage
this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    Given(~'^I enter login screen$') {
        ->
    
        browser.to LoginPage
        browser.at LoginPage
    }
    
    When(~'^I enter valid login and password$') {
        ->
    
        createUser("validUser", "validPassword");
        
        browser.at LoginPage
        browser.page.loginWithLoginAndPassword("validUser", "validPassword");
    }
    
    Then(~'^I should see my user skills page$') {
        ->
    
        browser.at DashboardPage
    }
    
    When(~'^I enter any page$') { -> 
        browser.to DashboardPage 
    }
    
    Given(~'^I am not logged in$') {
        ->
    }
    
    Given(~'^I have been logged in successfully as \'(.*)\'$') { String username ->
    
        createUser(username, "validPassword");
    
        browser.to LoginPage
        browser.page.loginWithLoginAndPassword(username, "validPassword");
        
        
    }
    
    Then(~'^I should see that I am logged in as \'(.*)\'$') { String username ->
        
        assert browser.page.loggedInAs
        assert browser.page.loggedInAs.user == username
    }
    
    When(~'^I sign in with following credentials (.*)/(.*)$') { String login, String password ->
    
        browser.at LoginPage
        browser.page.loginWithLoginAndPassword(login, password);
    }
    
    Then(~'^login form should contain error \'(.*)\'$') { String error ->
        assert browser.page.messages.entries.contains(error);
    }
    

