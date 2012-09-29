package add.haslearntit.pages

import geb.*


class UserSkillsPage extends Page {

	static url = "http://localhost:18081/user/";
	
	static at = { title == "Has Learnt It" }

	static content = {
		learntSkill { $("ul").find("li").find("div",class:"skill") }
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
}