package add.haslearntit.pages

import geb.*


class UserSkillsPage extends Page {

	static url = "http://localhost:8080/user/";
	
	static at = { title == "Has Learnt It" }

	def recentlyLearntSkill = {
		return $("ul").find("li").text();
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
		
		return $("li")*.text();
	}
	
	def encouragementIsPresent = {
		
		return $(".encouragementMessage").size() > 0;
	}
}