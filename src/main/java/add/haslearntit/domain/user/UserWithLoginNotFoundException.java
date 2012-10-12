package add.haslearntit.domain.user;

public class UserWithLoginNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -5319237808765610871L;

    public UserWithLoginNotFoundException(String login) {
        super(String.format("User with login '%s' not found!", login));
    }

}
