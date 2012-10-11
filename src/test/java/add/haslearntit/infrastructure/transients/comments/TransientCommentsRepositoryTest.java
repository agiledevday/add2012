package add.haslearntit.infrastructure.transients.comments;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import add.haslearntit.domain.comments.Comment;
import add.haslearntit.domain.comments.CommentRepository;
import add.haslearntit.domain.skills.Skill;

public class TransientCommentsRepositoryTest {

    private final CommentRepository repository = new TransientCommentsRepository();

    @Test
    public void shouldFetchCommentsForSkill() throws Exception {
        // given:
        Skill skill1 = createSkill("skill1");
        Skill skill2 = createSkill("skill2");
        Comment comment = new Comment("comment1", skill1);
        // when:
        repository.store(comment);
        repository.store(new Comment("comment2", skill2));
        // then:
        List<Comment> comments = repository.fetchCommentsFor(skill1);
        Assert.assertThat(comments.size(), Matchers.is(1));
        Assert.assertThat(comments.get(0), Matchers.is(comment));
    }

    private Skill createSkill(String skillName) {
        return new Skill(skillName, "hard", "10h");
    }
}
