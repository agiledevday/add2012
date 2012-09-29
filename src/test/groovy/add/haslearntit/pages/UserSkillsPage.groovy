package add.haslearntit.pages

import geb.*


class UserSkillsPage extends Page {

	static url = "http://localhost:18081/user/";

	static at = { title == "Has Learnt It" }

	def recentlyLearntSkill = {
		return $("ul").find("li .skill").text();
	}

	def recordNewSkill = {  skill, difficult, time ->

		fillNewSkillForm(skill, difficult, time);
		submitNewSkillForm();
	}

	def submitNewSkillForm = {
		$("input", type: "submit").click();
	}

	def fillNewSkillForm = { skill, difficult, time ->

		$("form").name = skill;
		$("form").difficulty = difficult;
		$("form").time = time;
	}

	def messages = {
		return $(".messages li")*.text();
	}

	def learntSkills = {
		return $("li .skill")*.text();
	}

	def commentExistsForSkill = { comment, skillName ->
		return commentsOfSkill(skillName).find {it.text == comment} != null
	}

	private commentsOfSkill = { skillName ->
		return findSkillByName(skillName).parent().find(".comment").allElements();
	}

	private findSkillByName = { skillName ->
		return $(".skill", text: contains(skillName))
	}

	def encouragementIsPresent = {

		return $(".encouragementMessage").size() > 0;
	}
}