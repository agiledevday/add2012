package add.haslearntit.infrastructure.hibernate.skills;

import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import add.haslearntit.domain.skills.Skill;
import add.haslearntit.domain.skills.SkillsRepository;

public class HibernateSkillsRepository implements SkillsRepository {

	private final SessionFactory sessionFactory;

	public HibernateSkillsRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	@Override
	public void store(Skill skill) {
		session().save(skill);
	}

	@Transactional(readOnly=true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> loadAll() {
		return Collections.unmodifiableList(session().createCriteria(Skill.class).list());
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Skill> loadByNamePrefix(String namePrefix) {
		return Collections.unmodifiableList(session()
				.createCriteria(Skill.class)
				.add(Restrictions.ilike("name", namePrefix + "%")
				).list());
	}

	private Session session() {
		return sessionFactory.getCurrentSession();
	}
	
}
