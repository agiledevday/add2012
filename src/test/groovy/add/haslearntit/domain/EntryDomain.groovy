package add.haslearntit.domain

import add.haslearntit.domain.entry.Entry
import add.haslearntit.domain.entry.EntryRepository


class EntryDomain extends Domain{

    EntryRepository entryRepository;
        
    public EntryDomain() {
        entryRepository = getBean(EntryRepository);
    }

    public int entriesCount(){
        
        return entryRepository.loadAll().size();
    }

    public Entry createEntry(String name, String difficulty, String time, Date creationDate){
        
        Entry entry = new Entry(name, difficulty, time, creationDate);
        entryRepository.store(entry);
        return entry;
        
    }    
    
}
