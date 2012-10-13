package add.haslearntit.application.entry;

import static ch.lambdaj.Lambda.*;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.extensions.ajax.markup.html.autocomplete.DefaultCssAutoCompleteTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import add.haslearntit.domain.entry.Difficulty;
import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryRepository;
import ch.lambdaj.function.convert.StringPropertyExtractor;

public class NewEntryPanel extends Panel {

    private static final long serialVersionUID = 2627832833454321010L;

    private Model<String> nameModel = Model.of();
    private Model<String> difficultyModel = Model.of();
    private Model<String> timeModel = Model.of();

    @SpringBean
    private EntryRepository entryRepository;
    RequiredTextField<String> difficultyTextField;
    TextField<String> skillNameTextField;

    public NewEntryPanel(String id) {

        super(id);
        add(new NewEntryForm("newSkillForm"));
    }


    private class NewEntryForm extends Form<Void> {



        public NewEntryForm(String id) {
            super(id);
            initializeComponents();
        }

        private void initializeComponents() {
            skillNameTextField = createSkillNamesAutoCompleteTextField();
            difficultyTextField = new RequiredTextField<String>("difficulty", difficultyModel);
            difficultyTextField.setOutputMarkupId(true);
            
            add(skillNameTextField);
            add(difficultyTextField);
            add(new RequiredTextField<String>("time", timeModel));
        }

        @Override
        protected void onSubmit() {

            entryRepository.store(new Entry(nameModel.getObject(), difficultyModel.getObject(), timeModel.getObject()));

            nameModel.setObject("");
            difficultyModel.setObject("");
            timeModel.setObject("");
        }
    }

    private TextField<String> createSkillNamesAutoCompleteTextField() {
        DefaultCssAutoCompleteTextField<String> skillNamesAutoCompleteTextField = new DefaultCssAutoCompleteTextField<String>("name", nameModel) {

            @Override
            protected Iterator<String> getChoices(String input) {
                if (input == null) {
                    Collections.emptyList().iterator();
                }
                List<Entry> skills = entryRepository.loadByNamePrefix(input);
                List<String> names = convert(skills, new StringPropertyExtractor<Entry>("name"));

                return names.iterator();
            }
        };
        skillNamesAutoCompleteTextField.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                Difficulty suggestedDifficulty = entryRepository.getMostCommonDifficultyBySkillName((String) nameModel.getObject());
                difficultyModel.setObject(emptyIfNull(suggestedDifficulty).toLowerCase());
                target.add(difficultyTextField);
            }

        });
        return (TextField<String>) skillNamesAutoCompleteTextField.setRequired(true);
    }

    protected String emptyIfNull(Difficulty suggestedDifficulty) {
        if (suggestedDifficulty == null) {
            return "";
        } else {
            return suggestedDifficulty.toString();
        }
    }

}
