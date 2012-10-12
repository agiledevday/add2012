package add.haslearntit.domain.user;

public class UserWithLoginAlreadyExistsException extends RuntimeException{
    private static final long serialVersionUID = -2902748778987218607L;

    public UserWithLoginAlreadyExistsException(String login) {
        super(String.format("User with login '%s' already exists!", login));
    }

}
