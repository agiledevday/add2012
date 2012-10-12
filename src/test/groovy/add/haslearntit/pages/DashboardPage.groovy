package add.haslearntit.pages

import geb.*
import add.haslearntit.pages.modules.LoggedInUserModule
import add.haslearntit.pages.modules.MessagesModule


class DashboardPage extends Page {

    static url = "http://localhost:18081/home/";

    static at = { title == "Has Learnt It" }

    static content = {
        learntSkill { $("ul").find("li").find("div",class:"skill") }
        loggedInAs { module LoggedInUserModule }
        messages { module MessagesModule}
        skillSuggestionsDiv(required: false) { $("div", class: "wicket-aa") }
        skillSuggestions(required: false) { skillSuggestionsDiv().find("ul li") }
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

    def typeSkillPart(typedSkillPart) {
        $("form").name = typedSkillPart;
    }

    def displayedSkillSuggestions = {
        waitFor { skillSuggestionsDiv.present }
        return skillSuggestions()*.text()
    }
}
