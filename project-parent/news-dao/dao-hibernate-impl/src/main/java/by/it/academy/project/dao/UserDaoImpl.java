package by.it.academy.project.dao;

import by.it.academy.project.model.User;
import by.it.academy.project.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private static UserDaoImpl INSTANCE = new UserDaoImpl();

    public static UserDaoImpl getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Long create(User user) {
        Session session = sessionFactory.openSession();
        Long result = null;
        try {
            session.getTransaction().begin();
            result = (Long) session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while creating user", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Optional<User> read(Long id) {
        Session session = sessionFactory.openSession();
        Optional<User> result = Optional.empty();
        try (session) {
            session.getTransaction().begin();
            result = Optional.ofNullable(session.get(User.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while reading user", e);
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public int update(User user) {
        Optional<User> optionalUser = read(user.getId());
        Session session = sessionFactory.openSession();
        try (session) {
            session.getTransaction().begin();
            if (optionalUser.isPresent()) {
                session.update(user);
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            log.error("error while updating user", e);
            session.getTransaction().rollback();
        }
        return 1;
    }

    @Override
    public int delete(Long id) {
        Optional<User> optionalUser = read(id);
        Session session = sessionFactory.openSession();
        try (session) {
            session.getTransaction().begin();
            if (optionalUser.isPresent()) {
                session.delete(new User(id));
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            log.error("error while deleting user", e);
            session.getTransaction().rollback();
        }
        return 0;
    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        List<User> result = new ArrayList<>();
        try (session) {
            session.getTransaction().begin();
            Query<User> query = session.createQuery("FROM User", User.class);
            result = query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while getting all users", e);
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.openSession();
        Optional<User> result = Optional.empty();
        try {
            session.getTransaction().begin();
            Query<User> query = session.createQuery("SELECT distinct u FROM User u WHERE username=:username and password=:password", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            result = Optional.ofNullable(query.getSingleResult());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while finding user by username and password", e);
            session.getTransaction().rollback();
        } catch (NoResultException e) {
            log.error("find user by username and password - no result", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        Session session = sessionFactory.openSession();
        Optional<User> result = Optional.empty();
        try {
            session.getTransaction().begin();
            Query<User> query = session.createQuery("SELECT distinct u FROM User u WHERE username=: username", User.class);
            query.setParameter("username", username);
            result = Optional.ofNullable(query.getSingleResult());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while finding user by username", e);
            session.getTransaction().rollback();
        } catch (NoResultException e) {
            log.error("find user by username - no result", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

}
