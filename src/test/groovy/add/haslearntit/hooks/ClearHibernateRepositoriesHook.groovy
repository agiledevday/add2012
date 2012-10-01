package add.haslearntit.hooks

import org.eclipse.jetty.webapp.WebAppContext
import org.springframework.context.ApplicationContext
import org.springframework.orm.hibernate3.LocalSessionFactoryBean
import org.springframework.web.context.support.WebApplicationContextUtils

import add.haslearntit.CucumberAcceptanceTest


this.metaClass.mixin(cucumber.runtime.groovy.Hooks)
this.metaClass.mixin(cucumber.runtime.groovy.EN)

Before{ scenario ->

	WebAppContext handler = (WebAppContext) CucumberAcceptanceTest.server.getHandler();
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(handler.getServletContext());
	
	LocalSessionFactoryBean localSessionFactoryBean = ctx.getBean(LocalSessionFactoryBean.class);
	recreateSchema(localSessionFactoryBean);
	
}

private recreateSchema(LocalSessionFactoryBean localSessionFactoryBean) {

	System.err.println("Recreating database schema...");
	
	localSessionFactoryBean.dropDatabaseSchema();
	localSessionFactoryBean.createDatabaseSchema()
}