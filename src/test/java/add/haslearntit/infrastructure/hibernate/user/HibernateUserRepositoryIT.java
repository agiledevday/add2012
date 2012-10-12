package add.haslearntit.infrastructure.hibernate.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

import add.haslearntit.domain.user.User;
import add.haslearntit.domain.user.UserRepositoryContractTest;
import add.haslearntit.infrastructure.hibernate.HibernateTestConfiguration;

public class HibernateUserRepositoryIT extends UserRepositoryContractTest{

    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void setUp() throws Exception {

        buildSessionFactory();
        repository = new HibernateUserRepository(sessionFactory) {
            @Override
            public void store(User user) {
                super.store(user);
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

        Configuration configuration = new HibernateTestConfiguration("user/User.hbm.xml").prepareConfiguration();
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
