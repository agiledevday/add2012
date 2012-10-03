package add.haslearntit.application.login;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import add.haslearntit.application.skills.UserSkillsPage;
import add.haslearntit.domain.user.User;

public class LoginForm extends Panel{

    private TextField<String> loginField = new RequiredTextField<String>("login", Model.<String> of());
    private PasswordTextField passwordField = new PasswordTextField("password", Model.<String> of());
	
	public LoginForm() {
		super("loginForm");
		
		buildForm();
	}

	private void buildForm() {
		
		Form<Void> form = new Form<Void>("form"){
			
			protected void onSubmit() {
				
				if(!hasError()){
                    setCurrentUser(loginField.getValue());
					setResponsePage(UserSkillsPage.class);
				}
			}

		};
		
		form.add(loginField);
		form.add(passwordField);
		
		add(form);
	}

    private void setCurrentUser(String userName) {
        SessionCurrentUserModel currentUser = new SessionCurrentUserModel(Application.get());
        currentUser.setObject(new User(userName));
    };
}
