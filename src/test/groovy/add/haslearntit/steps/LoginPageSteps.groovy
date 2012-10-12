package add.haslearntit.steps

import add.haslearntit.domain.UserDomain
import add.haslearntit.pages.LoginPage
import add.haslearntit.ui.DashboardUi
import add.haslearntit.ui.LoginUi
this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    UserDomain userDomain;
    LoginUi loginUi;
    DashboardUi dashboardUi;

    Before() {
        userDomain = world.asType(UserDomain);
        loginUi = world.asType(LoginUi);
        dashboardUi = world.asType(DashboardUi);
    }    

    Given(~'^I enter login screen$') { ->
        
        loginUi.enterPage();
    }
    
    When(~'^I enter valid login and password$') { ->
        
        userDomain.createValidUser();
        loginUi.loginWithValidCredentials();
    }
    
    Then(~'^I should see my user skills page$') { ->
        
        dashboardUi.assertIsOnPage();
    }
    
    When(~'^I enter any page$') { -> 
        
        dashboardUi.enter();
    }
    
    Given(~'^I am not logged in$') {
        ->
    }
    
    Given(~'^I have been logged in successfully as \'(.*)\'$') { String username ->
    
        userDomain.createUser(username, "validPassword");
        loginUi.loginWithCredentials(username, "validPassword");
       
    }
    
    Then(~'^I should see that I am logged in as \'(.*)\'$') { String username ->
        
        loginUi.assertIsLoggedInAs(username);
    }
    
    When(~'^I sign in with following credentials (.*)/(.*)$') { String login, String password ->
        
        loginUi.loginWithCredentials(login, password);    
    }
    
    Then(~'^login form should contain error \'(.*)\'$') { String error ->
        
        loginUi.assertFailedToLoginDueTo(error);
    }
    

