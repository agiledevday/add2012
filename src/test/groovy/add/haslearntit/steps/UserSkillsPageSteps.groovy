package add.haslearntit.steps

import geb.Browser
import geb.report.ScreenshotAndPageSourceReporter;
import add.haslearntit.infrastructure.transients.skills.TransientSkillsRepository;
import add.haslearntit.pages.*
import cucumber.runtime.PendingException
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
		
		browser.to UserSkillsPage
		browser.page.recordNewSkill("how to bind cucumber features to groovy steps", "easy", "2 hours");
	}
	
	When(~'^I enter my home page$') {
		->
	
		browser.to UserSkillsPage
		browser.at UserSkillsPage
	}
	
	Then(~'^I should see skill I have learnt$') {
		->

		browser.at UserSkillsPage
		assert browser.page.recentlyLearntSkill()  == 
			"A User has learnt how to bind cucumber features to groovy steps, which was pretty easy, and it took him 2 hours."
	}
	
	Given(~'^I have learnt following skills$') { 
		DataTable table ->

		browser.to UserSkillsPage;
		
		table.asMaps().each { 
			
			browser.page.recordNewSkill(it["what"], it["difficult"], it["time"]);
		}
				
	}
	
	Then(~'^I should see following skills$') { 
		DataTable expected ->

		browser.at UserSkillsPage;
		
		List<List<String>> actual = new ArrayList<List<String>>();
		browser.page.learntSkills().each { row ->
			actual.add([row]);
		}
		
		expected.diff(actual);
	}
	
	Then(~'^I should see points for each skill$') { 
		DataTable expected ->
		
		browser.at UserSkillsPage;
		
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
		
		browser.at UserSkillsPage;
		assert !browser.page.encouragementIsPresent();
	}
		
	Then(~'^I should see encouragement message to record my skills$') {
		->
		
		browser.at UserSkillsPage;
		assert browser.page.encouragementIsPresent();
	}
	
	When(~'^I enter following skill details$') { DataTable skills ->
		
		browser.at UserSkillsPage;
		data = skills.asMaps().get(0);
		browser.page.fillNewSkillForm(data["what"], data["difficult"], data["time"]);
	}
	
	When(~'^I try to submit my skill$') { ->

		browser.at UserSkillsPage;
		assert browser.page.submitNewSkillForm();
	}
	
	Then(~'^I should be notified with message \'(.*)\'$') { String message ->
		
		assert browser.page.messages().contains(message);
	}
	
	Then(~'^skill should not be recorded$') { ->
	
		assert TransientSkillsRepository.get().loadAll().size() == 0;
	}
		
	