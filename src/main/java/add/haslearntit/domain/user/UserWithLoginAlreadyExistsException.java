package add.haslearntit.domain.user;

public class UserWithLoginAlreadyExistsException extends RuntimeException{

    public UserWithLoginAlreadyExistsException(String login) {
        super(String.format("User with login '%s' already exists!", login));
    }

}
