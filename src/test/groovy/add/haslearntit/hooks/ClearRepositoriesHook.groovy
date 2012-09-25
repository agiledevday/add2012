package add.haslearntit.hooks

import add.haslearntit.infrastructure.transients.skills.TransientSkillsRepository;

this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

After{ scenario ->

	System.err.println("Clearing database...");
	TransientSkillsRepository.clear();
	
}