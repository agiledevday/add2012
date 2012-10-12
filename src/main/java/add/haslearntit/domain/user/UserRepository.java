package add.haslearntit.domain.user;

public interface UserRepository {

    public void store(User user);

    public User loadByLogin(String login);

}
