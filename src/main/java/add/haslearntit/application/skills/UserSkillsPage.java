package add.haslearntit.application.skills;

import org.apache.wicket.spring.injection.annot.SpringBean;

import add.haslearntit.application.TemplatePage;
import add.haslearntit.domain.skills.SkillsRepository;

public class UserSkillsPage extends TemplatePage {

	@SpringBean
	private SkillsRepository skillsRepository;

	public UserSkillsPage() {

		super();

		initializeComponents();
	}

	private void initializeComponents() {
		
		add(new NewSkillPanel("newSkillForm"));
		add(new LearntSkillsList("learntSkillsList", new AllLearntSkillsListModel(skillsRepository)));

	}
}
