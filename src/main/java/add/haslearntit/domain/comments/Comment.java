package add.haslearntit.domain.comments;

import add.haslearntit.domain.skills.Skill;

public class Comment {

    private final Skill skill;
    private final String text;

    public Comment(String text, Skill skill) {
        this.text = text;
        this.skill = skill;
    }

    public Skill getSkill() {
        return skill;
    }

    public String getText() {
        return text;
    }

}
