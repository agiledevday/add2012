package add.haslearntit.hooks


this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

After{ scenario ->

	System.err.println("Clearing transient repositorories...");
//    Context.get().getBean(TransientEntryRepository.class).clear();
//    Context.get().getBean(TransientUserRepository.class).clear();
	
}