package add.haslearntit.application.login;

import org.apache.wicket.Application;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.cycle.RequestCycle;

import add.haslearntit.domain.user.User;

public class SessionCurrentUserModel implements CurrentUserModel {
    private static final long serialVersionUID = -7922140395099348280L;
    private transient final Application application;

    public SessionCurrentUserModel(Application application) {
        this.application = application;
    }

    @Override
    public User getObject() {

        return anonymousIfUserEmpty(fetchUserFromSession());
    }

    private User anonymousIfUserEmpty(User currentUser) {
        return currentUser != null ? currentUser : User.ANONYMOUS;
    }

    private User fetchUserFromSession() {
        return (User) application.getSessionStore().getAttribute(getRequest(), "currentUser");
    }

    @Override
    public void setObject(User user) {
        application.getSessionStore().setAttribute(getRequest(), "currentUser", user);
    }

    private Request getRequest() {
        Request request = RequestCycle.get() != null ? RequestCycle.get().getRequest() : null;
        return request;
    }

    @Override
    public void detach() {

    }

}
