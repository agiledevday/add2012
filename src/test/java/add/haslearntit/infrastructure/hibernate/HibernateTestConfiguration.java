package add.haslearntit.infrastructure.hibernate;

import org.hibernate.cfg.Configuration;

public class HibernateTestConfiguration {
    
    public String mappingFile;

    public HibernateTestConfiguration(String mappingFile) {
        this.mappingFile = String.format("add/haslearntit/infrastructure/hibernate/%s", mappingFile);
    }

    public Configuration prepareConfiguration() {
    
        Configuration configuration = new Configuration();
    
        configuration.addResource(mappingFile);
    
    	configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
    	configuration.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
    	configuration.setProperty("hibernate.connection.url","jdbc:hsqldb:mem:hasLearntIt");
    	configuration.setProperty("hibernate.connection.username", "sa");
    	configuration.setProperty("hibernate.connection.password", "");
    	configuration.setProperty("hibernate.current_session_context_class","thread");
    	configuration.setProperty("hibernate.format_sql", "true");
    	configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
    
    	return configuration;
    }
}