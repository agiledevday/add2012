package add.haslearntit

import geb.Browser
import geb.binding.BindingUpdater
import add.haslearntit.domain.EntryDomain
import add.haslearntit.domain.UserDomain
import add.haslearntit.ui.DashboardUi;
import add.haslearntit.ui.LoginUi

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    class HasLearntItWorld {
    
        def failures = 0;
        def browser;
        def world;
        
        public HasLearntItWorld(){
            browser = new Browser();
            world = this;
        }
            
    }
    
    World {
        
        HasLearntItWorld.mixin EntryDomain, UserDomain, LoginUi, DashboardUi
        return new HasLearntItWorld();
    }

    After{ scenario ->
    
        if(scenario.failed){
            browser.report("failed" + failures++);
        }
    }
    
//    def bindingUpdater;
//    
//    Before () {
//        bindingUpdater = new BindingUpdater (binding, world.browser)
//        bindingUpdater.initialize ()
//    }
//    
//    After () {
//        bindingUpdater.remove ()
//    }
    