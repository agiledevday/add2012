package add.haslearntit.application.skills;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import add.haslearntit.application.HasLearntItApplication;
import add.haslearntit.application.TemplatePage;
import add.haslearntit.domain.skills.SkillsRepository;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class UserSkillsPage extends TemplatePage {

	@SpringBean
	private SkillsRepository skillsRepository;

	public UserSkillsPage(PageParameters params) {

		super();

		initializeComponents();
	}

	private void initializeComponents() {
		
		add(new NewSkillPanel("newSkillForm"));
		add(new LearntSkillsList("learntSkillsList", new AllLearntSkillsListModel(skillsRepository)));

	}
}
