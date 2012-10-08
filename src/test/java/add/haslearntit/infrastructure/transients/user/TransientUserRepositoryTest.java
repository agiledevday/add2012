package add.haslearntit.infrastructure.transients.user;


import org.junit.Before;

import add.haslearntit.domain.user.UserRepositoryContractTest;


public class TransientUserRepositoryTest extends UserRepositoryContractTest {

    @Before
    public void setUp() throws Exception {

        repository = new TransientUserRepository();
    }
    
}
