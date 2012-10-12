package add.haslearntit.steps

import add.haslearntit.domain.EntryDomain
import add.haslearntit.domain.entry.EntryRepository
import add.haslearntit.hooks.Context
import add.haslearntit.pages.*

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

//    class HasLearntItWorld {}
//
//    World {
//        HasLearntItWorld.mixin EntryDomain
//        new HasLearntItWorld()
//    }
    
	Before {
        
        entryRepository = Context.get().getBean(EntryRepository);
	}
	
    When(~'^skill should not be recorded$'){ ->
        
        assert entriesCount() == 0
        
    }
    