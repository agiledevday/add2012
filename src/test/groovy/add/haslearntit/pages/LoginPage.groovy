package add.haslearntit.pages

import geb.Page


class LoginPage extends Page {

	static url = "http://localhost:18081/login/";
	
	static at = { $("div#loginForm").displayed }
	
	
	def enterLoginAndPassword = {
		login, password ->
		
		$("form").loginField = login;
		$("form").passwordField = password;
		
	}
	
	def submitLoginForm = {
		
		assert $("input", type: "submit").size() > 0;
		
		$("input", type: "submit").click(UserSkillsPage);
	}
	
	def checkError = {
		errorMessage ->
		
		assert messages() == [errorMessage];
	}

	def messages = {
		return $(".messages li")*.text();
	}
	
}