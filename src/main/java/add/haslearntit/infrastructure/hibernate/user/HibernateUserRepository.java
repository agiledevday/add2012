package add.haslearntit.infrastructure.hibernate.user;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import add.haslearntit.domain.user.User;
import add.haslearntit.domain.user.UserRepository;
import add.haslearntit.domain.user.UserWithLoginAlreadyExistsException;
import add.haslearntit.domain.user.UserWithLoginNotFoundException;

public class HibernateUserRepository implements UserRepository {

    private final SessionFactory sessionFactory;

    public HibernateUserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    @Override
    public void store(User user) {

        String login = user.getName();
        
        if (findUserWithLogin(login) != null) {
            throw new UserWithLoginAlreadyExistsException(login);
        }
        
        session().saveOrUpdate(user);
    }

    @Transactional
    @Override
    public User loadByLogin(String login) {

        User userFound = findUserWithLogin(login);

        if (userFound == null) {
            throw new UserWithLoginNotFoundException(login);
        }

        return userFound;
    }

    private User findUserWithLogin(String login) {

        return (User) session().createCriteria(User.class).add(Restrictions.eq("name", login)).uniqueResult();
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

}
