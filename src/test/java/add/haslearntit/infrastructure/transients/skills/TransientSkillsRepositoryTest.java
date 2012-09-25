package add.haslearntit.infrastructure.transients.skills;


import org.junit.Before;

import add.haslearntit.domain.skills.SkillsRepositoryContractTest;


public class TransientSkillsRepositoryTest extends SkillsRepositoryContractTest {

	@Before
	public void setUp() throws Exception {

		repository = new TransientSkillsRepository();
	}
	
}
