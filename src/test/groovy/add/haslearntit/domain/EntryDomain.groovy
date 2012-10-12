package add.haslearntit.domain

import add.haslearntit.domain.entry.EntryRepository
import add.haslearntit.hooks.Context

class EntryDomain {

    EntryRepository entryRepository;
        
    public EntryDomain() {
        entryRepository = Context.get().getBean(EntryRepository);
    }

    public int entriesCount(){
        
        return entryRepository.loadAll().size();
    }
    
}
