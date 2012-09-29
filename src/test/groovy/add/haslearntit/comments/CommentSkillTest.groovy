package add.haslearntit.comments;

import static org.junit.Assert.*;
import cucumber.runtime.PendingException
import add.haslearntit.pages.*
import geb.Browser

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

// Scenario Add a not empty comment to skill
Given(~'^skill "([^"]*)"$') { String skillName ->
	browser.to UserSkillsPage
	browser.at UserSkillsPage
	browser.page.recordNewSkill(skillName, "easy", "2 hours");
}

When(~'^user adds comment "([^"]*)" to skill "([^"]*)"$') { String comment, String skill ->
}

Then(~'^the comment "([^"]*)" is displayed associated to the skill "([^"]*)"$') { String comment, String skillName ->
	assert browser.page.commentExistsForSkill(comment, skillName)
}



Given(~'^comment "([^"]*)"$') { String arg1 ->
	// Express the Regexp above with the code you wish you had
	throw new PendingException()
}

Then(~'^below the comment "([^"]*)"$') { String arg1 ->
	// Express the Regexp above with the code you wish you had
	throw new PendingException()
}

When(~'^user adds an empty comment to skill "([^"]*)"$') { String arg1 ->
	// Express the Regexp above with the code you wish you had
	throw new PendingException()
}

Then(~'^the comment is not displayed$') {
	->
	// Express the Regexp above with the code you wish you had
	throw new PendingException()
}