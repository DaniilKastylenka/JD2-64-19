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

    private static CommentDaoImpl INSTANCE = new CommentDaoImpl();

    public static CommentDaoImpl getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Long create(Comment comment) throws SQLException {
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
    public Optional<Comment> read(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Optional<Comment> result = Optional.empty();
        try {
            session.getTransaction().begin();
            result = Optional.ofNullable(session.get(Comment.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while reading comment", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int update(Comment comment) throws SQLException {
        Optional<Comment> optionalComment = read(comment.getId());
        Session session = sessionFactory.openSession();
        try (session) {
            session.getTransaction().begin();
            if (optionalComment.isPresent()) {
                session.update(comment);
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            log.error("error while updating comment", e);
            session.getTransaction().rollback();
        }
        return 1;
    }

    @Override
    public int delete(Long id) throws SQLException {
        Optional<Comment> optionalComment = read(id);
        Session session = sessionFactory.openSession();
        try (session) {
            session.getTransaction().begin();
            if (optionalComment.isPresent()) {
                session.delete(new Comment(id));
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            log.error("error while deleting comment", e);
            session.getTransaction().rollback();
        }
        return 1;
    }

    @Override
    public List<Comment> getAll() throws SQLException {
        List<Comment> result = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try (session) {
            session.getTransaction().begin();
            Query<Comment> query = session.createQuery("FROM Comment", Comment.class);
            result = query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while getting all comments", e);
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public void addLike(Long commentId, Long userId) throws SQLException {
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
    public int deleteLike(Long commentId, Long userId) throws SQLException {
        Session session = sessionFactory.openSession();
        int result = -1;
        try {
            session.getTransaction().begin();
            NativeQuery<Comment> query = session.createNativeQuery("DELETE FROM comment_user WHERE CU_comment_id = ? AND CU_user_id = ?", Comment.class);
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
    public int updateLikeInComment(Long commentId, boolean isLiked) throws SQLException {
        Session session = sessionFactory.openSession();
        int result = -1;
        try {
            session.getTransaction().begin();
            Long numberOfLikes = read(commentId).orElseThrow(() -> new RuntimeException("unknown comment")).getNumberOfLikes();
            Query<Comment> query = session.createQuery("UPDATE Comment SET numberOfLikes=:numberOfLikes WHERE id=:id", Comment.class);
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
        return false;
    }
}
