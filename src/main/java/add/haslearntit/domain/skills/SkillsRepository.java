package add.haslearntit.domain.skills;

import java.util.List;

public interface SkillsRepository {

	public abstract void store(Skill skill);

	public abstract List<Skill> loadAll();

	public abstract void addComment(Skill skill, Comment comment);

	public abstract List<Comment> fetchAllComments(Skill skill);

}