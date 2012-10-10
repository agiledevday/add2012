package add.haslearntit.hooks

import org.eclipse.jetty.webapp.WebAppContext
import org.springframework.context.ApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils

import add.haslearntit.CucumberAcceptanceTest

class Context {

    public static ApplicationContext get(){
        
        WebAppContext handler = (WebAppContext) CucumberAcceptanceTest.server.getHandler();
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(handler.getServletContext());
        
        return context;
    }
    
}
