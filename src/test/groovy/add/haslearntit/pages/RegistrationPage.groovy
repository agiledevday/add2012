package add.haslearntit.pages

import geb.Page
import add.haslearntit.pages.modules.LoggedInUserModule
import add.haslearntit.pages.modules.MessagesModule


class RegistrationPage extends Page {

	static url = "http://localhost:18081/register/";

	static at = { title == "Register" }

	static content = {
		loginField { $("input#login-input")}
		passwordField { $("input#password-input")}
		registerButton {$("input[type=submit]") }
	}

	void login(login) {
		loginField = login
	}
	void password(password) {
		passwordField = password
	}
	void submitRegister() {
		registerButton.click()
	}
}