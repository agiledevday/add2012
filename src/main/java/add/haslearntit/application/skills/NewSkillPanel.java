package add.haslearntit.application.skills;

import ch.lambdaj.function.convert.StringPropertyExtractor;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.DefaultCssAutoCompleteTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepository;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static ch.lambdaj.Lambda.convert;
import static java.lang.String.format;

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

	private class NewSkillFrom extends Form<Void> {
		private static final long serialVersionUID = -3821156411930577907L;

		public NewSkillFrom(String id) {
			super(id);
			initializeComponents();
		}

		private void initializeComponents() {
			FormComponent<String> nameTextField = createSkillNamesAutoCompleteTextField();
			add(nameTextField);
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

	private TextField<String> createSkillNamesAutoCompleteTextField() {
		DefaultCssAutoCompleteTextField<String> skillNamesAutoCompleteTextField =
				new DefaultCssAutoCompleteTextField<String>("name", nameModel) {
			@Override
			protected Iterator<String> getChoices(String input) {
				if (input == null) {
					Collections.emptyIterator();
				}
				List<Skill> skills = skillsRepository.loadByNamePrefix(input);
				List<String> names = convert(skills, new StringPropertyExtractor<Skill>("name"));
				return names.iterator();
			}
		};
		return (TextField<String>) skillNamesAutoCompleteTextField.setRequired(true);
	}
}
