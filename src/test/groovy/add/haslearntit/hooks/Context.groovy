package add.haslearntit.hooks

import org.eclipse.jetty.webapp.WebAppContext
import org.springframework.context.ApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils

import add.haslearntit.CucumberAcceptanceIT;

class Context {

    public static ApplicationContext get(){
        
        WebAppContext handler = (WebAppContext) CucumberAcceptanceIT.server.getHandler();
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(handler.getServletContext());
        
        return context;
    }
    
}
