package add.haslearntit.application.registration;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import add.haslearntit.application.entry.DashboardPage;
import add.haslearntit.application.login.SessionCurrentUserModel;
import add.haslearntit.domain.user.User;
import add.haslearntit.domain.user.UserRepository;

public class RegistrationPage extends WebPage{
	
	@SpringBean
	UserRepository userRepository;
	
	TextField<String> loginField = new TextField<String>("login-input",new Model<String>());
	PasswordTextField passwordField = new PasswordTextField("password-input",new Model<String>());
	Button registerButton = new Button("register-button");
	Form registerForm = new Form("register-form") {
		protected void onSubmit() {
			User userToRegister = new User(loginField.getModelObject(),passwordField.getModelObject());
			userRepository.store(userToRegister);
			new SessionCurrentUserModel(getApplication()).setObject(userToRegister);
			setResponsePage(DashboardPage.class);
		};
	};
	
	public RegistrationPage() {
		registerForm.add(loginField,passwordField,registerButton);
		add(registerForm);
	}

	public TextField<String> getLoginField() {
		return loginField;
	}

	public PasswordTextField getPasswordField() {
		return passwordField;
	}

	public Button getRegisterButton() {
		return registerButton;
	}

	public Form getRegisterForm() {
		return registerForm;
	}

	
	
}
