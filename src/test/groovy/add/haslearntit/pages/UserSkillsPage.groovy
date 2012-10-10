package add.haslearntit.pages

import geb.*
import add.haslearntit.pages.modules.LoggedInUserModule
import add.haslearntit.pages.modules.MessagesModule


class UserSkillsPage extends Page {

    static url = "http://localhost:18081/user/";

    static at = { $("#userSkillsPage").displayed }

    static content = {
        learntSkill { $("ul").find("li").find("div",class:"skill") }
        loggedInAs { module LoggedInUserModule }
        messages { module MessagesModule}
    }

    def recentlyLearntSkill = {
        return learntSkill.text();
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

    def learntSkills = {
        return  learntSkill*.text();
    }

    def learntSkillsPoints = {

        return $("div", class:"points")*.text();
    }

    def encouragementIsPresent = {

        return $(".encouragementMessage").size() > 0;
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
}