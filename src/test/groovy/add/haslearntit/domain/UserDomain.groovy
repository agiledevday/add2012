package add.haslearntit.domain

import add.haslearntit.domain.user.User
import add.haslearntit.domain.user.UserRepository


class UserDomain extends Domain{

    UserRepository userRepository;
        
    public UserDomain() {
        userRepository = getBean(UserRepository);
    }

    public User createUser(String login, String password){
    
        User user = new User(login, password);
        userRepository.store(user);
        return user;
    }
    
}
