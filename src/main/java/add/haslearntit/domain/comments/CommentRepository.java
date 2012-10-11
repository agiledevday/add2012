package add.haslearntit.domain.comments;

import java.util.List;

import add.haslearntit.domain.skills.Skill;

public interface CommentRepository {

    List<Comment> fetchCommentsFor(Skill skill);

    void store(Comment comment);
}
