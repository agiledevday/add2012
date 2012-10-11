package add.haslearntit.steps

import add.haslearntit.domain.entry.EntryRepository
import add.haslearntit.hooks.Context
import add.haslearntit.pages.*

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    EntryRepository entryRepository;
	
	Before {
        
        entryRepository = Context.get().getBean(EntryRepository);
	}
	
    When(~'^skill should not be recorded$'){ ->
        
        assert entryRepository.loadAll().size() == 0
        
    }
    