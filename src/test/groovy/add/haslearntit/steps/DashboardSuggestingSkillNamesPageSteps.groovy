package add.haslearntit.steps

import add.haslearntit.domain.EntryDomain
import add.haslearntit.pages.*
import add.haslearntit.ui.DashboardUi
import add.haslearntit.ui.LoginUi

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    EntryDomain entryDomain;
    DashboardUi dashboardUi;

    Before() {
        entryDomain = world.asType(EntryDomain);
        dashboardUi = world.asType(DashboardUi);
    }

    Given(~'^users have already learned \'(.*)\'$') { String skillsAsList ->
    
        List<String> skillsList = splitListPassedAsStringWithSemicolons(skillsAsList)
    
        skillsList.each { skill ->
            entryDomain.createEntry(skill, "easy", "1");
        }
        
    }
    
    When(~'^I am typing following skill details \'(.*)\'$') { String typedSkillPart ->

        dashboardUi.startTypingEntryName(typedSkillPart);
    }
    
    Then(~'^I should see following skills suggestions \'(.*)\'$') { String suggestionsAsList ->
        
        suggestionsList = splitListPassedAsStringWithSemicolons(suggestionsAsList)
        assert dashboardUi.displayedSkillSuggestions() == suggestionsList.sort()
    }
    
    List<String> splitListPassedAsStringWithSemicolons(String listAsString) {
        (listAsString == null || listAsString.isEmpty()) ? [] : listAsString.split(";")
    }
