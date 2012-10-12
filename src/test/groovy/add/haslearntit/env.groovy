package add.haslearntit

import add.haslearntit.domain.EntryDomain

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    class HasLearntItWorld {}

    World {
        
        HasLearntItWorld.mixin EntryDomain
        new HasLearntItWorld()
    }

    