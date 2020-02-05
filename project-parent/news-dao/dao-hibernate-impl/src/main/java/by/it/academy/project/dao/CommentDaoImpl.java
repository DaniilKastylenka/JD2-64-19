package by.it.academy.project.dao;

import by.it.academy.project.model.Comment;
import by.it.academy.project.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class CommentDaoImpl implements CommentDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private static final CommentDaoImpl INSTANCE = new CommentDaoImpl();

    public static CommentDao getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Long create(Comment comment) {
        Session session = sessionFactory.openSession();
        Long result = null;
        try {
            session.getTransaction().begin();
            result = (Long) session.save(comment);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while creating comment", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Optional<Comment> read(Long id) {
        Session session = sessionFactory.openSession();
        Optional<Comment> result = Optional.empty();
        try (session) {
            result = Optional.ofNullable(session.get(Comment.class, id));
        } catch (HibernateException e) {
            log.error("error while reading comment", e);
        }
        return result;
    }

    @Override
    public int update(Comment comment) {
        Optional<Comment> optionalComment = read(comment.getId());
        Session session = sessionFactory.openSession();
        int result = 0;
        try {
            session.getTransaction().begin();
            if (optionalComment.isPresent()) {
                session.update(comment);
                session.getTransaction().commit();
                result = 1;
            }
        } catch (HibernateException e) {
            log.error("error while updating comment", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int delete(Long id) throws SQLException {
        Optional<Comment> optionalComment = read(id);
        Session session = sessionFactory.openSession();
        int result = 0;
        try {
            session.getTransaction().begin();
            if (optionalComment.isPresent()) {
                session.delete(new Comment(id));
                session.getTransaction().commit();
                result = 1;
            }
        } catch (HibernateException e) {
            log.error("error while deleting comment", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Comment> getAll() {
        List<Comment> result = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try (session) {
            Query<Comment> query = session.createQuery("FROM Comment ORDER BY date DESC", Comment.class);
            result = query.list();
        } catch (HibernateException e) {
            log.error("error while getting all comments", e);
        }
        return result;
    }

    @Override
    public void addLike(Long commentId, Long userId) {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            NativeQuery<Comment> query = session.createNativeQuery("INSERT INTO comment_user VALUE (?,?)", Comment.class);
            query.setParameter(1, commentId);
            query.setParameter(2, userId);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while adding like to comment", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public int deleteLike(Long commentId, Long userId) {
        Session session = sessionFactory.openSession();
        int result = 0;
        try {
            session.getTransaction().begin();
            NativeQuery query = session.createNativeQuery("DELETE FROM comment_user WHERE Comment_C_id = ? AND User_U_id = ?");
            query.setParameter(1, commentId);
            query.setParameter(2, userId);
            result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while deleting like from comment", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int updateLikeInComment(Long commentId, boolean isLiked) {
        Session session = sessionFactory.openSession();
        int result = 0;
        try {
            session.getTransaction().begin();
            Long numberOfLikes = read(commentId).orElseThrow(() -> new RuntimeException("unknown comment")).getNumberOfLikes();
            Query query = session.createQuery("UPDATE Comment SET numberOfLikes=:numberOfLikes WHERE id=:id");
            if (isLiked) {
                query.setParameter("numberOfLikes", numberOfLikes - 1);
            } else {
                query.setParameter("numberOfLikes", numberOfLikes + 1);
            }
            query.setParameter("id", commentId);
            result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while update like in comment", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean findLike(Long commentId, Long userId) throws SQLException {
        Session session = sessionFactory.openSession();
        boolean result = false;
        try (session) {
            NativeQuery query = session.createNativeQuery("SELECT * FROM comment_user WHERE Comment_C_id = ? AND User_U_id = ?");
            query.setParameter(1, commentId);
            query.setParameter(2, userId);
            if (query.uniqueResult() != null) {
                result = true;
            }
        } catch (HibernateException e) {
            log.error("error while finding like in comment", e);
        }
        return result;
    }
}
