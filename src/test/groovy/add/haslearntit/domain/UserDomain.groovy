package add.haslearntit.domain

import add.haslearntit.domain.user.User
import add.haslearntit.domain.user.UserRepository


class UserDomain extends Domain{

    UserRepository userRepository;
    public static final String VALID_LOGIN = "valid user name"
    public static final VALID_PASSWORD = "valid pass"
    
    public UserDomain() {
        userRepository = getBean(UserRepository);
    }

    public User createUser(String login, String password){
    
        User user = new User(login, password);
        userRepository.store(user);
        return user;
    }
    
    public void createValidUser() {
        createUser(VALID_LOGIN, VALID_PASSWORD)
    }
    
}
