package add.haslearntit.hooks

import org.eclipse.jetty.webapp.WebAppContext
import org.springframework.context.ApplicationContext
import org.springframework.orm.hibernate3.LocalSessionFactoryBean


this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

Before{ scenario ->

	ApplicationContext context = Context.get();
	
	LocalSessionFactoryBean localSessionFactoryBean = context.getBean(LocalSessionFactoryBean.class);
	recreateSchema(localSessionFactoryBean);
	
}

private recreateSchema(LocalSessionFactoryBean localSessionFactoryBean) {

	System.err.println("Recreating database schema...");
	
	localSessionFactoryBean.dropDatabaseSchema();
	localSessionFactoryBean.createDatabaseSchema()
}