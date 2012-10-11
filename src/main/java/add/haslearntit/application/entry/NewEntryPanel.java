package add.haslearntit.application.entry;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryRepository;

import org.apache.wicket.spring.injection.annot.SpringBean;

public class NewEntryPanel extends Panel {

    private static final long serialVersionUID = 2627832833454321010L;

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

        public NewEntryForm(String id) {
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

            entryRepository.store(new Entry(nameModel.getObject(), difficultyModel.getObject(), timeModel.getObject()));

            nameModel.setObject("");
            difficultyModel.setObject("");
            timeModel.setObject("");
        }
    }

}
