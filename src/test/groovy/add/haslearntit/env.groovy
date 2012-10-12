package add.haslearntit

import geb.Browser
import add.haslearntit.domain.EntryDomain
import add.haslearntit.domain.UserDomain

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    class HasLearntItWorld {
    
        def failures = 0;
        def browser;
        
        public HasLearntItWorld(){
            browser = new Browser();
        }
            
    }

    World {
        
        HasLearntItWorld.mixin EntryDomain, UserDomain
        new HasLearntItWorld()
    }

    After{ scenario ->
    
        if(scenario.failed){
            browser.report("failed" + failures++);
        }
    }
    
    