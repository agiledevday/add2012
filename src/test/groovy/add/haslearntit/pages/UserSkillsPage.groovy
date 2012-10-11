package add.haslearntit.pages

import geb.*


class UserSkillsPage extends Page {

	static url = "http://localhost:18081/user/";
	
	static at = { title == "Has Learnt It" }

	static content = {
		learntSkill { $("ul").find("li").find("div",class:"skill") }
		//TODO: Could be probably changed with id selector and regexp
		skillSuggestionsDiv(required: false) { $("div", class: "wicket-aa") }
		skillSuggestions(required: false) { skillSuggestionsDiv().find("ul li") }
	}
	
	def recentlyLearntSkill = {
		return learntSkill.text();
	}
	
	def recordNewSkill = { 
		skill, difficult, time ->

		fillNewSkillForm(skill, difficult, time);
		submitNewSkillForm();
	}

	def submitNewSkillForm = {
		$("input", type: "submit").click();
	}
	
	def fillNewSkillForm = {
		skill, difficult, time ->

		$("form").name = skill;
		$("form").difficulty = difficult;
		$("form").time = time;
	}
	
	def messages = {
		return $(".messages li")*.text();
	}
	
	def learntSkills = {
		return  learntSkill*.text();
	}
	
	def learntSkillsPoints = {
		return $("div", class:"points")*.text();
	}

		
	def encouragementIsPresent = {
		return $(".encouragementMessage").size() > 0;
	}

	def typeSkillPart(typedSkillPart) {
		$("form").name = typedSkillPart //A short version of: $("form").find("input", name: "name") << typedSkillPart
	}

	def displayedSkillSuggestions = {
		//FIXME: MZA: It works when only one Feature is run, but not when all of them
		waitFor { skillSuggestionsDiv.present }
		return skillSuggestions()*.text()
	}
}
