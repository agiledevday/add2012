package add.haslearntit.pages.modules

import geb.Module

class LoggedInUserModule extends Module{

	static base = {
		$("div.loggedIn")
	}
	
	static content = {
		user { $().text() }
	}
}
