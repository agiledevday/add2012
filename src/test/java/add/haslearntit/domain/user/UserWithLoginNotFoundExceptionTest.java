package add.haslearntit.domain.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UserWithLoginNotFoundExceptionTest {

    @Test
    public void shouldThrowPersonalizedException() throws Exception {
        // given:
        String name = "bolo";
        String message = "User with login 'bolo' not found!";
        // when:
        try {
            throw new UserWithLoginNotFoundException(name);
        } catch (UserWithLoginNotFoundException e) {
            // then:
            assertEquals(message, e.getMessage());
        }
    }
}
