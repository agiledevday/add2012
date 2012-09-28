package add.haslearntit.application.skills;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepository;

public class NewSkillForm extends Panel{

	private static final long serialVersionUID = 2627832833454321010L;

	private Model<String> nameModel = Model.of();
	private Model<String> difficultyModel = Model.of();
	private Model<String> timeModel = Model.of();

	private final SkillsRepository skillsRepository;

	public NewSkillForm(String id, SkillsRepository skillsRepository) {
		
		super(id);
		this.skillsRepository = skillsRepository;
		add(buildForm());
	}

	protected Form<Void> buildForm() {
		
		Form<Void> form = new Form<Void>("form"){

			private static final long serialVersionUID = -3821156411930577907L;

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
			
		};
		
		form.add(new RequiredTextField<String>("name", nameModel));
		form.add(new RequiredTextField<String>("difficulty", difficultyModel));
		form.add(new RequiredTextField<String>("time", timeModel));
		
		return form;
	}

}
