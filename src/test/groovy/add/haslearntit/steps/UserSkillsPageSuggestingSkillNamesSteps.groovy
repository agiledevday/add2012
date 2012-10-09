package add.haslearntit.steps

import geb.Browser
import add.haslearntit.pages.*

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

Browser browser
def failures = 0;

Before {
	browser = new Browser()
}

After { scenario ->

	if (scenario.failed) {
		browser.report("failed" + failures++);
	}

}

Given(~'^users have already learned \'(.*)\'$') { String skillsAsList->
	List<String> skillsList = splitListPassedAsStringWithSemicolons(skillsAsList)
	print "skillsList: " + skillsList
	browser.to UserSkillsPage;
	//TODO: MZA: It should be put into DB directly (not using web interface)
	skillsList.each { skill ->
		browser.page.recordNewSkill(skill, "easy", "1");
	}
}

When(~'''^I am typing following skill details \'(.*)\'$''') { String typedSkillPart ->
	browser.at UserSkillsPage;
	browser.page.typeSkillPart(typedSkillPart)
}

Then(~'^I should see following skills suggestions \'(.*)\'$') { String suggestionsAsList ->
	suggestionsList = splitListPassedAsStringWithSemicolons(suggestionsAsList)
	assert browser.page.displayedSkillSuggestions().sort() == suggestionsList.sort()
}

//TODO: MZA: Can it be done directly in Cucumber? If not move to some util class
List<String> splitListPassedAsStringWithSemicolons(String listAsString) {
	(listAsString == null || listAsString.isEmpty()) ? [] : listAsString.split(";")
}
