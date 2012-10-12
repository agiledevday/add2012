package add.haslearntit.pages

import geb.Page
import add.haslearntit.pages.modules.LoggedInUserModule
import add.haslearntit.pages.modules.MessagesModule


class RegistrationPage extends Page {

	static url = "http://localhost:18081/register/";
	
	static at = { title == "Register" }
	
    static content = {
    }
	
	
}