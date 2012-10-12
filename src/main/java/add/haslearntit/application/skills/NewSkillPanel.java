package add.haslearntit.application.skills;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepository;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class NewSkillPanel extends Panel {

	private static final long serialVersionUID = 2627832833454321010L;

	private Model<String> nameModel = Model.of();
	private Model<String> difficultyModel = Model.of();
	private Model<String> timeModel = Model.of();

	@SpringBean
	private SkillsRepository skillsRepository;

	public NewSkillPanel(String id) {

		super(id);
		add(new NewSkillFrom("newSkillForm"));
	}

	private class NewSkillFrom extends Form {
		private static final long serialVersionUID = -3821156411930577907L;

		public NewSkillFrom(String id) {
			super(id);
			initializeComponents();
		}

		private void initializeComponents() {
			add(new RequiredTextField<String>("name", nameModel));
			add(new RequiredTextField<String>("difficulty", difficultyModel));
			add(new RequiredTextField<String>("time", timeModel));
		}

		@Override
		protected void onSubmit() {

			skillsRepository.store(
					new Skill(
							nameModel.getObject(),
							difficultyModel.getObject(),
							timeModel.getObject())
			);

			nameModel.setObject("");
			difficultyModel.setObject("");
			timeModel.setObject("");
		}
	}

}
