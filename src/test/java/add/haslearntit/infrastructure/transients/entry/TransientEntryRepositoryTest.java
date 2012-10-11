package add.haslearntit.infrastructure.transients.entry;


import org.junit.Before;

import add.haslearntit.domain.entry.EntryRepositoryContractTest;
import add.haslearntit.infrastructure.transients.entry.TransientEntryRepository;


public class TransientEntryRepositoryTest extends EntryRepositoryContractTest {

	@Before
	public void setUp() throws Exception {

		repository = new TransientEntryRepository();
	}
	
}
