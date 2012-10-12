package add.haslearntit.steps

import geb.Browser
import add.haslearntit.pages.*

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    Given(~'^users have already learned \'(.*)\'$') { String skillsAsList ->
    
        browser.to DashboardPage;
        List<String> skillsList = splitListPassedAsStringWithSemicolons(skillsAsList)
    
        skillsList.each { skill ->
            createEntry(skill, "easy", "1");
        }
    }
    
    When(~'''^I am typing following skill details \'(.*)\'$''') { String typedSkillPart ->
        browser.at DashboardPage;
        browser.page.typeSkillPart(typedSkillPart)
    }
    
    Then(~'^I should see following skills suggestions \'(.*)\'$') { String suggestionsAsList ->
        suggestionsList = splitListPassedAsStringWithSemicolons(suggestionsAsList)
        assert browser.page.displayedSkillSuggestions().sort() == suggestionsList.sort()
    }
    
    List<String> splitListPassedAsStringWithSemicolons(String listAsString) {
        (listAsString == null || listAsString.isEmpty()) ? [] : listAsString.split(";")
    }
