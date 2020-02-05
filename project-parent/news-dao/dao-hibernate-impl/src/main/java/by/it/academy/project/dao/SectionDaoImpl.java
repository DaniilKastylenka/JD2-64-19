package by.it.academy.project.dao;

import by.it.academy.project.model.Section;
import by.it.academy.project.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class SectionDaoImpl implements SectionDao {

    private static final SectionDaoImpl INSTANCE = new SectionDaoImpl();

    public static SectionDao getINSTANCE() {
        return INSTANCE;
    }

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Long create(Section section) {
        return null;
    }

    @Override
    public Optional<Section> read(Long id) {
        Session session = sessionFactory.openSession();
        Optional<Section> result = Optional.empty();
        try (session) {
            result = Optional.ofNullable(session.get(Section.class, id));
        } catch (HibernateException e) {
            log.error("error while reading section", e);
        }
        return result;
    }

    @Override
    public int update(Section section) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public List<Section> getAll() {
        Session session = sessionFactory.openSession();
        List<Section> result = new ArrayList<>();
        try (session) {
            Query<Section> query = session.createQuery("FROM Section", Section.class);
            result = query.list();
        } catch (HibernateException e) {
            log.error("error while getting all sections", e);
        }
        return result;
    }
}
