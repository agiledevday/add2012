package add.haslearntit.ui

import add.haslearntit.domain.UserDomain
import add.haslearntit.pages.LoginPage


class LoginUi {

    public void loginWithValidCredentials(){
        
        loginWithCredentials(UserDomain.VALID_LOGIN, UserDomain.VALID_PASSWORD);
    }
    
    public loginWithCredentials(String login, String password){
        
        enterPage();
        browser.page.loginWithLoginAndPassword(login, password);
    }
    
    public enterPage(){
        
        browser.to LoginPage
        browser.at LoginPage;
    }

    public assertIsLoggedInAs(String username){
        
        assert browser.page.loggedInAs
        assert browser.page.loggedInAs.user == username
    }    

    public assertFailedToLoginDueTo(String error){
        
        assert browser.page.messages.entries.contains(error);
    }
    
}
