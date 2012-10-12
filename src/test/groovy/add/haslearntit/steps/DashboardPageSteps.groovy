package add.haslearntit.steps

import add.haslearntit.domain.UserDomain
import add.haslearntit.pages.*
import add.haslearntit.ui.DashboardUi
import add.haslearntit.ui.LoginUi
import cucumber.table.DataTable

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    UserDomain userDomain;
    LoginUi loginUi;
    DashboardUi dashboardUi;
    
    Before() {
        userDomain = world.asType(UserDomain);
        loginUi = world.asType(LoginUi);
        dashboardUi = world.asType(DashboardUi);
    }

    Given(~'^I have learnt new skill lately$') {
        ->
        
        dashboardUi.recordNewSkill();
    }
    
    When(~'^I enter my home page$') { ->
        
        dashboardUi.enter();
    }
    
    Then(~'^I should see skill I have learnt$') { ->
        
        dashboardUi.assertNewSkillIsDisplayed();
    }
    
    Given(~'^I have learnt following skills$') { 
        DataTable table ->
        
        table.asMaps().each { 
            
            dashboardUi.recordSkill(it["what"], it["difficult"], it["time"]);
        }
    }
    
    Then(~'^I should see following skills$') { 
        DataTable expected ->
        
        List<List<String>> actual = new ArrayList<List<String>>();
        dashboardUi.displayedEntries().each { row ->
            actual.add([row]);
        }
        
        expected.diff(actual);
    }
    
    Then(~'^I should see points for each skill$') { 
        DataTable expected ->
        
        List<List<String>> actual = new ArrayList<List<String>>();
        dashboardUi.displayedEntriesPoints().each{row -> 
            actual.add([row]);
        }
        
        expected.diff(actual);
    }
    
    Given(~'^I haven\'t learnt anything$') { 
        ->
        
    }
    
    Then(~'^I should no longer see encouragement message to record my skills$') {
        ->
        
        dashboardUi.assertEncouragementMessageIsNotPresent();
    }
        
    Then(~'^I should see encouragement message to record my skills$') {
        ->
        
        dashboardUi.assertEncouragementMessageIsPresent();
    }
    
    When(~'^I enter following skill details$') { DataTable skills ->
        
        data = skills.asMaps().get(0);
        dashboardUi.fillNewSkillForm(data["what"], data["difficult"], data["time"]);
    }
    
    When(~'^I try to submit my skill$') { ->

        dashboardUi.submitEntry();
    }
    
    Then(~'^new skill form should contain error \'(.*)\'$') { String error ->
        
        dashboardUi.assertNewEntryFormContainError(error);
    }
