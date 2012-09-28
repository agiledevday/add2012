package add.haslearntit.application.skills;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import add.haslearntit.application.HasLearntItApplication;
import add.haslearntit.application.TemplatePage;
import add.haslearntit.domain.skills.SkillsRepository;

public class UserSkillsPage extends TemplatePage {

	public UserSkillsPage(PageParameters params) {

		super();

		initializeComponents();
	}

	private void initializeComponents() {
		
		SkillsRepository repository = ((HasLearntItApplication)getApplication()).getSkillsRepository();;
		add(new NewSkillForm("newSkillForm", repository));
		add(new LearntSkillsList("learntSkillsList", new AllLearntSkillsListModel(repository)));

	}
}
