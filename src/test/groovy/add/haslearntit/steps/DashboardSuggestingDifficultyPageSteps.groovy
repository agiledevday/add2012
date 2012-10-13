package add.haslearntit.steps

import geb.Browser
import add.haslearntit.pages.*
import cucumber.runtime.PendingException
import cucumber.table.DataTable

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)


Given(~'^users have already learned following skills$') { DataTable data ->
    browser.to DashboardPage
	data.asMaps().each { 
       browser.page.recordNewSkill(it["skill"], it["difficulty"], "2h");
	}
}

When(~'^I typed following skill name "([^"]*)"$') { String skillName ->
	browser.page.typeSkillPart(skillName)
}

Then(~'^I should see following difficulty suggestion "([^"]*)"$') { String expectedSuggestedDifficulty ->
	
	assert expectedSuggestedDifficulty == browser.page.difficulty();	
}