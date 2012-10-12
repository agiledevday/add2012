package add.haslearntit.pages

import geb.Page
import add.haslearntit.pages.modules.LoggedInUserModule
import add.haslearntit.pages.modules.MessagesModule


class LoginPage extends Page {

    static url = "http://localhost:18081/login/";
    
    static at = { $("div#loginForm").displayed }
    
    static content = {
        loggedInAs { module LoggedInUserModule }
        messages {module MessagesModule}
    }
    
    def enterLoginAndPassword = {
        login, password ->
        
        $("form").login = login;
        $("form").password = password;
        
    }
    
    def submitLoginForm = {
        
        assert $("input", type: "submit").size() > 0;
        
        $("input", type: "submit").click(DashboardPage);
    }
    
    def loginWithLoginAndPassword = {
        login, password ->
        
        enterLoginAndPassword(login, password);
        submitLoginForm();
    }
    
    def checkError = {
        errorMessage ->
        
        assert messages() == [errorMessage];
    }

    def messages = {
        return $(".messages li")*.text();
    }
    
}