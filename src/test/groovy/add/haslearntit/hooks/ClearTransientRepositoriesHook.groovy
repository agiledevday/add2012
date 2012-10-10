package add.haslearntit.hooks

import add.haslearntit.infrastructure.transients.skills.TransientSkillsRepository
import add.haslearntit.infrastructure.transients.user.TransientUserRepository

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

After{ scenario ->

	System.err.println("Clearing transient repositorories...");
//    Context.get().getBean(TransientSkillsRepository.class).clear();
    Context.get().getBean(TransientUserRepository.class).clear();
	
}