package add.haslearntit.infrastructure.transients.comments;

import java.util.List;

import add.haslearntit.domain.comments.Comment;
import add.haslearntit.domain.comments.CommentRepository;
import add.haslearntit.domain.skills.Skill;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class TransientCommentsRepository implements CommentRepository {

    Multimap<Skill, Comment> skillComment = HashMultimap.create();

    @Override
    public List<Comment> fetchCommentsFor(Skill skill) {
        return Lists.newArrayList(skillComment.get(skill));
    }

    @Override
    public void store(Comment comment) {
        skillComment.put(comment.getSkill(), comment);
    }

}
