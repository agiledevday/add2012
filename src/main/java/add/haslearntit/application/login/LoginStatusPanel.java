package add.haslearntit.application.login;

import static add.haslearntit.utils.Utils.property;
import static ch.lambdaj.Lambda.on;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import add.haslearntit.domain.user.User;

public class LoginStatusPanel extends Panel {
    private static final long serialVersionUID = 3545715212541895521L;
    private final CompoundPropertyModel<User> model;

    public LoginStatusPanel(CurrentUserModel currentUserModel) {

        super("loginStatusPanel");

        this.model = new CompoundPropertyModel<User>(currentUserModel);

        add(new Label("currentUser", model.bind(property(on(User.class).getName()))));
    }

}
