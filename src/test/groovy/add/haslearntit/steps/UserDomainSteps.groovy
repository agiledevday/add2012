package add.haslearntit.steps

import add.haslearntit.domain.user.User
import add.haslearntit.domain.user.UserRepository
import add.haslearntit.hooks.Context
import cucumber.table.DataTable
this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

    Given(~'^there are users with following credentials$') { DataTable users ->
    
        users.asMaps().each { user ->
    
            createUser(user["login"], user["password"]);
            
        }
    }

