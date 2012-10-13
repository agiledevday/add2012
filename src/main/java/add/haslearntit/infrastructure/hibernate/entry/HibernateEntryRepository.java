package add.haslearntit.infrastructure.hibernate.entry;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import add.haslearntit.domain.entry.Difficulty;
import add.haslearntit.domain.entry.Entry;
import add.haslearntit.domain.entry.EntryRepository;

public class HibernateEntryRepository implements EntryRepository, Serializable {

    private final SessionFactory sessionFactory;

    public HibernateEntryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void store(Entry entry) {
        session().save(entry);
    }

    @Override
    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public List<Entry> loadAll() {
        return Collections.unmodifiableList(session().createCriteria(Entry.class).list());
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<Entry> loadByNamePrefix(String namePrefix) {
        return Collections.unmodifiableList(session()
                .createCriteria(Entry.class)
                .add(Restrictions.ilike("name", namePrefix + "%")
                ).list());
    }
    
    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(readOnly = true)
    public Difficulty getMostCommonDifficultyBySkillName(String skillName) {
        return new MostCommonDifficulty().selectMostCommonDifficultyFrom(loadByName(skillName));
    }



    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    @Override
    public List<Entry> loadByName(String skillName) {
        return Collections.unmodifiableList(session().createCriteria(Entry.class).add(Restrictions.eq("name", skillName)).list());
    }

}
