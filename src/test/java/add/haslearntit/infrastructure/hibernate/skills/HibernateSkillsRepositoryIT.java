package add.haslearntit.infrastructure.hibernate.skills;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepositoryContractTest;
import add.haslearntit.infrastructure.hibernate.HibernateTestConfiguration;

public class HibernateSkillsRepositoryIT extends SkillsRepositoryContractTest {

    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void setUp() throws Exception {

        buildSessionFactory();
        repository = new HibernateSkillsRepository(sessionFactory) {
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

        Configuration configuration = new HibernateTestConfiguration("skills/Skill.hbm.xml").prepareConfiguration();
        sessionFactory = configuration.buildSessionFactory();
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
