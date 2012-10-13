package add.haslearntit.infrastructure.hibernate.entry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;

import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryRepositoryContractTest;
import add.haslearntit.infrastructure.hibernate.HibernateTestConfiguration;

public class HibernateEntryRepositoryIT extends EntryRepositoryContractTest {

    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void setUp() throws Exception {

        buildSessionFactory();
        repository = new HibernateEntryRepository(sessionFactory) {
            @Override
            public void store(Entry entry) {
                super.store(entry);
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

        Configuration configuration = new HibernateTestConfiguration("entry/Entry.hbm.xml").prepareConfiguration();
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
