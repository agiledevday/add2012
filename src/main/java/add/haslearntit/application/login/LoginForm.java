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
import add.haslearntit.domain.user.UserRepository;
import add.haslearntit.domain.user.UserWithLoginNotFoundException;

public class LoginForm extends Panel{

    private final UserRepository userRepository;

    private TextField<String> loginField = new RequiredTextField<String>("login", Model.<String> of());
    private PasswordTextField passwordField = new PasswordTextField("password", Model.<String> of());

    private Form<Void> form;
	
	public LoginForm(UserRepository userRepository) {
		super("loginForm");
        this.userRepository = userRepository;
		
		buildForm();
	}

	private void buildForm() {
		
		form = new Form<Void>("form"){
			
			protected void onSubmit() {
	
			    authenticate();
			    
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

	private void authenticate() {

	    String login = loginField.getValue();
	    String password = passwordField.getValue();
	
	    try{
	        User user = userRepository.loadByLogin(login);
	        
	        if(!password.equals(user.getPassword())){
	            raiseAuthenticationError();
	        }
	        
	    } catch(UserWithLoginNotFoundException exception){
	        raiseAuthenticationError();
	    }
	}

    private void raiseAuthenticationError() {
        form.error("Invalid username or password!");
    }
	
    private void setCurrentUser(String userName) {
        SessionCurrentUserModel currentUser = new SessionCurrentUserModel(Application.get());
        currentUser.setObject(new User(userName, ""));
    };
}
