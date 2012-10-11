package add.haslearntit.application.skills;

import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import add.haslearntit.domain.comments.Comment;
import add.haslearntit.domain.comments.CommentRepository;
import add.haslearntit.domain.skills.Skill;

public class LearntSkillsList extends Panel{

    public class CommentForm extends Form<Void> {

        private final Skill skill;
        private final RequiredTextField<String> newCommentTextField;

        public CommentForm(Skill skill) {
            super("commentForm");
            this.skill = skill;
            newCommentTextField = new RequiredTextField<String>("newComment", Model.<String> of());
            add(newCommentTextField);
        }

        @Override
        protected void onSubmit() {
            commentRepository.store(new Comment(newCommentTextField.getValue(), skill));
        }

    }

    public class CommentListView extends ListView<Comment> {

        public CommentListView(List<? extends Comment> comments) {
            super("comments", comments);
        }

        @Override
        protected void populateItem(ListItem<Comment> item) {
            item.add(new Label("comment", item.getModelObject().getText()));
        }

    }

    private static final long serialVersionUID = -9134642394089794933L;
	
	private final SkillsListModel model;

    private final CommentRepository commentRepository;

    public LearntSkillsList(String id, SkillsListModel model, CommentRepository commentRepository) {
	
		super(id);
		this.model = model;
        this.commentRepository = commentRepository;
		
		initializeComponents();
	}

	private void initializeComponents() {

		initializeEncouragement();
		initializeSkillsList();
	}

	private void initializeSkillsList() {
		
        ListView<Skill> skillList = new ListView<Skill>("list", model) {

			@Override
			protected void populateItem(ListItem<Skill> item) {
                Skill skill = item.getModelObject();
                item.add(new Label("skill", skill.asMessage()));
				item.add(new Label("skillPoints", String.valueOf(item.getModelObject().getEarnedPoints())));
                item.add(new CommentListView(commentRepository.fetchCommentsFor(skill)));
                item.add(new CommentForm(skill));
			}
        };
        skillList.setReuseItems(true);
        add(skillList);
	}

	private void initializeEncouragement() {
		
		add(new WebMarkupContainer("encouragement"){
			
			@Override
			public boolean isVisible() {
				return model.getObject().size() == 0;
			}
		});
	}

}
