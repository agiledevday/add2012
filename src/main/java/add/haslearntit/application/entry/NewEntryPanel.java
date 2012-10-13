package add.haslearntit.application.entry;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.ajax.markup.html.autocomplete.DefaultCssAutoCompleteTextField;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import add.haslearntit.domain.entry.Difficulty;
import add.haslearntit.application.entry.validators.TimeValidator;
import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryRepository;

import com.google.common.annotations.VisibleForTesting;

public class NewEntryPanel extends Panel {

    private static final long serialVersionUID = 2627832833454321010L;
    private static final String DEFAULT_DIFICULTY = Difficulty.MEDIUM.name();
    

    @VisibleForTesting
    static final int MINIMAL_REQUIRED_LETTER_TO_SUGGEST = 2;

    private Model<String> nameModel = Model.of();
    private Model<String> difficultyModel = Model.of();
    private Model<String> timeModel = Model.of();

    @SpringBean
    private EntryRepository entryRepository;

    public NewEntryPanel(String id) {

        super(id);
        add(new NewEntryForm("newSkillForm"));
    }

    private class NewEntryForm extends Form<Void> {
        private static final long serialVersionUID = 7468136832009985979L;

        public NewEntryForm(String id) {
            super(id);
            initializeComponents();
        }

        private void initializeComponents() {
            add(createSkillNamesAutoCompleteTextField());
            difficultyModel.setObject(DEFAULT_DIFICULTY);
            add(new DropDownChoice<String>("difficulty", difficultyModel, getListOfDificulties()).setNullValid(false));
            add(new RequiredTextField<String>("time", timeModel).add(new TimeValidator()));
        }

		private List<String> getListOfDificulties() {
			return Arrays.asList(Difficulty.EASY.name(),Difficulty.MEDIUM.name(),Difficulty.HARD.name());
		}

        @Override
        protected void onSubmit() {

            entryRepository.store(new Entry(nameModel.getObject(), difficultyModel.getObject(), timeModel.getObject()));

            nameModel.setObject("");
            difficultyModel.setObject(DEFAULT_DIFICULTY);
            timeModel.setObject("");
        }
    }

    private TextField<String> createSkillNamesAutoCompleteTextField() {
        DefaultCssAutoCompleteTextField<String> skillNamesAutoCompleteTextField =
                new DefaultCssAutoCompleteTextField<String>("name", nameModel) {
                    private static final long serialVersionUID = 2326158862363900977L;

                    @Override
                    protected Iterator<String> getChoices(String input) {
                        if (input == null || input.length() < MINIMAL_REQUIRED_LETTER_TO_SUGGEST) {
                            return Collections.<String> emptyList().iterator();
                        }
                        List<Entry> skills = entryRepository.loadByNamePrefix(input);
                        List<String> names = extract(skills, on(Entry.class).getName());
                        return names.iterator();
                    }
                };
        return (TextField<String>) skillNamesAutoCompleteTextField.setRequired(true);
    }

}
