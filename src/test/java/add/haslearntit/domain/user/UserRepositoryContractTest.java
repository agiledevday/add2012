package add.haslearntit.domain.user;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import org.junit.Assert;
import org.junit.Test;

import add.haslearntit.domain.user.User;
import add.haslearntit.domain.user.UserRepository;
import add.haslearntit.domain.user.UserWithLoginAlreadyExistsException;
import add.haslearntit.domain.user.UserWithLoginNotFoundException;

public abstract class UserRepositoryContractTest {

    protected UserRepository repository;

    @Test
    public void shouldUserByLogin() throws Exception {
        
        // given:
        User user = new User("login", "");
        
        // when:
        repository.store(user);
        repository.store(new User("other", ""));
        
        // then:
        assertThat(repository.loadByLogin("login"), equalTo(user));
    }

    @Test
    public void shouldFailMeaningfullyIfAskedForUserThatDoesNotExist() throws Exception {
        
        // given:
        try{
            // when:
            repository.loadByLogin("missing user");
            Assert.fail("expected exception!");
    
        } catch (Exception exception){
            
            // then:
            assertThat(exception, instanceOf(UserWithLoginNotFoundException.class));
            assertThat(exception.getMessage(), containsString("missing user"));
        }
    }

    @Test
    public void shouldLoginBeUnique() throws Exception {
        
        // given:
        repository.store(new User("login already taken", ""));
        try{
            // when:
            repository.store(new User("login already taken", ""));
            Assert.fail("expected exception!");
    
        } catch (Exception exception){
            
            // then:
            assertThat(exception, instanceOf(UserWithLoginAlreadyExistsException.class));
            assertThat(exception.getMessage(), containsString("login already taken"));
        }
    }

}