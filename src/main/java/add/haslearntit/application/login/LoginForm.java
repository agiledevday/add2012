package add.haslearntit.application.login;

import org.apache.wicket.Application;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import add.haslearntit.application.entry.DashboardPage;
import add.haslearntit.domain.user.User;
import add.haslearntit.domain.user.UserRepository;
import add.haslearntit.domain.user.UserWithLoginNotFoundException;

public class LoginForm extends Panel{
    private static final long serialVersionUID = -8520281746316087565L;

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
            private static final long serialVersionUID = 1941687389356354112L;

            protected void onSubmit() {
	
			    authenticate();
			    
				if(!hasError()){
                    setCurrentUser(loginField.getValue());
					setResponsePage(DashboardPage.class);
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
