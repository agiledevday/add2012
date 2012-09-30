package add.haslearntit.application.login;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import add.haslearntit.application.skills.UserSkillsPage;

public class LoginForm extends Panel{

	private static final String INVALID_PASSWORD = "Invalid password";
	private static final String INVALID_LOGIN = "Invalid login";
	private static final String FORM = "form";
	private static final String LOGIN_FORM = "loginForm";

	private TextField<String> loginField = new TextField<String>("loginField", Model.<String>of());
	private PasswordTextField passwordField = new PasswordTextField("passwordField", Model.<String>of());
	
	public LoginForm() {
		super(LOGIN_FORM);
		
		buildForm();
	}

	private void buildForm() {
		
		Form<Void> form = new Form<Void>(FORM){
			
			protected void onSubmit() {
				
				if(StringUtils.isBlank(loginField.getValue())){
					error(INVALID_LOGIN);
				}
				
				if(StringUtils.isBlank(passwordField.getValue())){
					error(INVALID_PASSWORD);
				}
				
				if(!hasError()){
					setResponsePage(UserSkillsPage.class);
				}
			};
		};
		
		passwordField.setRequired(false);
		form.add(loginField);
		form.add(passwordField);
		
		add(form);
	}
}
