package add.haslearntit.domain.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserWithLoginAlreadyExistsExceptionTest {
    @Test
    public void shouldUserWithLoginAlreadyExistsException() throws Exception {
        // given:
        String name = "lolo";
        String message = "User with login 'lolo' already exists!";
        // when:
        try {
            throw new UserWithLoginAlreadyExistsException(name);
        } catch (UserWithLoginAlreadyExistsException e) {
            // then:
            assertEquals(message, e.getMessage());
        }
    }

}
