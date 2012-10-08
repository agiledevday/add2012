package add.haslearntit.infrastructure.hibernate.skills;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepositoryContractTest;

public class HibernateSkillsRepositoryIT extends SkillsRepositoryContractTest {

	private SessionFactory sessionFactory;
	private Session session;

	@Before
	public void setUp() throws Exception {

		buildSessionFactory();
		repository = new HibernateSkillsRepository(sessionFactory){
			@Override
			public void store(Skill skill) {
				super.store(skill);
				detachSession();
			}

		};
		initializeSession();
	}

	@After
	public void tearDown() throws Exception {

		cleanupSession();
	}

	private void detachSession() {
		session.flush();
		session.clear();
	}
	
	private void buildSessionFactory() {

		Configuration configuration = prepareTestConfiguration();
		sessionFactory = configuration.buildSessionFactory();
	}

	private Configuration prepareTestConfiguration() {

		Configuration configuration = new Configuration();

		configuration.addResource("add/haslearntit/infrastructure/hibernate/skills/Skill.hbm.xml");

		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
		configuration.setProperty("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
		configuration.setProperty("hibernate.connection.url","jdbc:hsqldb:mem:hasLearntIt");
		configuration.setProperty("hibernate.connection.username", "sa");
		configuration.setProperty("hibernate.connection.password", "");
		configuration.setProperty("hibernate.current_session_context_class","thread");
//		//disabled - SQL logging was enabled in a logback configuration and it doesn't make a mesh on a console
//		configuration.setProperty("hibernate.show_sql", "true");
		configuration.setProperty("hibernate.format_sql", "true");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");

		return configuration;
	}

	private void initializeSession() {
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
	}

	private void cleanupSession() {
		session.getTransaction().rollback();
		session = null;
	}
}
