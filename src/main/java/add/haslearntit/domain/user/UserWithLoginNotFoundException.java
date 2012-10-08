package add.haslearntit.domain.user;

public class UserWithLoginNotFoundException extends RuntimeException{

    public UserWithLoginNotFoundException(String login) {
        super(String.format("User with login '%s' not found!", login));
    }

}
