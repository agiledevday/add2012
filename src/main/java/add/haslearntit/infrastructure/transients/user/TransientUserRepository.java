package add.haslearntit.infrastructure.transients.user;

import java.util.HashMap;
import java.util.Map;

import add.haslearntit.domain.user.User;
import add.haslearntit.domain.user.UserRepository;
import add.haslearntit.domain.user.UserWithLoginAlreadyExistsException;
import add.haslearntit.domain.user.UserWithLoginNotFoundException;

public class TransientUserRepository implements UserRepository {

    private Map<String, User> users = new HashMap<String, User>();

    @Override
    public void store(User user) {
        
        if(contains(user.getName()))
            throw new UserWithLoginAlreadyExistsException(user.getName());
        
        users.put(user.getName(), user);
    }

    @Override
    public User loadByLogin(String login) {
        
        if(!contains(login))
            throw new UserWithLoginNotFoundException(login);
        
        return users.get(login);
    }

    private boolean contains(String login) {
        return users.containsKey(login);
    }

    public void clear(){
        users.clear();
    }

}
