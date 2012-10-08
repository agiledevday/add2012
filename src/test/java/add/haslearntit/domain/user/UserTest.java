package add.haslearntit.domain.user;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserTest {

    @Test
    public void shouldUserWithSameLoginAndPasswordBeEqual() throws Exception {
        
        // given:
        User someUser = new User("login", "password"); 
        User sameUser = new User("login", "password"); 
        
        // when:
        boolean areEqual = someUser.equals(sameUser);
        
        // then:
        assertThat(areEqual, equalTo(true));
    }

    @Test
    public void shouldUserWithSameLoginAndPasswordHaveEqualHashCodes() throws Exception {
        
        // given:
        User someUser = new User("login", "password"); 
        User sameUser = new User("login", "password"); 
        
        // when:
        boolean areEqual = someUser.hashCode() == sameUser.hashCode();
        
        // then:
        assertThat(areEqual, equalTo(true));
    }

    
}
