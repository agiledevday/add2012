package add.haslearntit.steps

import geb.Browser
import add.haslearntit.infrastructure.transients.entry.TransientEntryRepository;
import add.haslearntit.pages.*
import cucumber.table.DataTable

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    def browser
    def failures = 0;
    
    Before {
        browser = new Browser()
    }
    
    After{ scenario ->
        
        if(scenario.failed){
            browser.report("failed" + failures++);
        }
        
    }
    
    Given(~'^I have learnt new skill lately$') {
        ->
        
        browser.to DashboardPage
        browser.page.recordNewSkill("how to bind cucumber features to groovy steps", "easy", "2 hours");
    }
    
    When(~'^I enter my home page$') {
        ->
        
        browser.to DashboardPage
        browser.at DashboardPage
    }
    
    Then(~'^I should see skill I have learnt$') {
        ->
        
        browser.at DashboardPage
        assert browser.page.recentlyLearntSkill()  == 
            "A User has learnt how to bind cucumber features to groovy steps, which was pretty easy, and it took him 2 hours."
    }
    
    Given(~'^I have learnt following skills$') { 
        DataTable table ->
        
        browser.to DashboardPage;
        
        table.asMaps().each { 
            
            browser.page.recordNewSkill(it["what"], it["difficult"], it["time"]);
        }
                
    }
    
    Then(~'^I should see following skills$') { 
        DataTable expected ->
        
        browser.at DashboardPage;
        
        List<List<String>> actual = new ArrayList<List<String>>();
        browser.page.learntSkills().each { row ->
            actual.add([row]);
        }
        
        expected.diff(actual);
    }
    
    Then(~'^I should see points for each skill$') { 
        DataTable expected ->
        
        browser.at DashboardPage;
        
        List<List<String>> actual = new ArrayList<List<String>>();
        browser.page.learntSkillsPoints().each{row -> 
            actual.add([row]);
        }
        
        expected.diff(actual);
    }
    
    Given(~'^I haven\'t learnt anything$') { 
        ->
        
    }
    
    Then(~'^I should no longer see encouragement message to record my skills$') {
        ->
        
        browser.at DashboardPage;
        assert !browser.page.encouragementIsPresent();
    }
        
    Then(~'^I should see encouragement message to record my skills$') {
        ->
        
        browser.at DashboardPage;
        assert browser.page.encouragementIsPresent();
    }
    
    When(~'^I enter following skill details$') { DataTable skills ->
        
        browser.at DashboardPage;
        data = skills.asMaps().get(0);
        browser.page.fillNewSkillForm(data["what"], data["difficult"], data["time"]);
    }
    
    When(~'^I try to submit my skill$') { ->

        browser.at DashboardPage;
        assert browser.page.submitNewSkillForm();
    }
    
    Then(~'^new skill form should contain error \'(.*)\'$') { String error ->
        assert browser.page.messages.entries.contains(error);
    }
    
    Given(~'^users have already learned \'(.*)\'$') { String skillsAsList->
        
        browser.to DashboardPage;
        List<String> skillsList = splitListPassedAsStringWithSemicolons(skillsAsList)

        skillsList.each { skill ->
            browser.page.recordNewSkill(skill, "easy", "1");
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
