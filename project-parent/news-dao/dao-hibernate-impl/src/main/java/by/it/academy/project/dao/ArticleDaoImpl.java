package by.it.academy.project.dao;

import by.it.academy.project.model.Article;
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
public class ArticleDaoImpl implements ArticleDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Long create(Article article) throws SQLException {
        Session session = sessionFactory.openSession();
        Long result = null;
        try (session) {
            session.getTransaction().begin();
            result = (Long) session.save(article);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while creating article", e);
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public Optional<Article> read(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Optional<Article> result = Optional.empty();
        try (session) {
            session.getTransaction().begin();
            result = Optional.ofNullable(session.get(Article.class, id));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while reading", e);
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public int update(Article article) throws SQLException {
        Optional<Article> optionalArticle = read(article.getId());
        Session session = sessionFactory.openSession();
        try (session) {
            session.getTransaction().begin();
            if (optionalArticle.isPresent()) {
                session.update(article);
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            log.error("error while updating", e);
            session.getTransaction().rollback();
        }
        return 1;
    }

    @Override
    public int delete(Long id) throws SQLException {
        Optional<Article> optionalArticle = read(id);
        Session session = sessionFactory.openSession();
        try (session) {
            session.getTransaction().begin();
            if (optionalArticle.isPresent()) {
                session.delete(new Article(id));
                session.getTransaction().commit();
            }
        } catch (HibernateException e) {
            log.error("error while deleting", e);
            session.getTransaction().rollback();
        }
        return 0;
    }

    @Override
    public List<Article> getAll() throws SQLException {
        Session session = sessionFactory.openSession();
        List<Article> result = new ArrayList<>();
        try (session) {
            session.getTransaction().begin();
            Query<Article> query = session.createQuery("FROM Article", Article.class);
            result = query.list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while getting all", e);
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public void addLike(Long articleId, Long userId) throws SQLException {
        Session session = sessionFactory.openSession();
        try (session) {
            session.getTransaction().begin();
            NativeQuery query = session.createNativeQuery("INSERT INTO article_user VALUES (?,?)");
            query.setParameter(1, articleId);
            query.setParameter(2, userId);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while adding like to article_user table", e);
            session.getTransaction().rollback();
        }
    }

    @Override
    public int deleteLike(Long articleId, Long userId) throws SQLException {
        Session session = sessionFactory.openSession();
        int result = -1;
        try (session) {
            session.getTransaction().begin();
            NativeQuery query = session.createNativeQuery("DELETE FROM article_user WHERE AU_article_id = ? AND AU_user_id");
            query.setParameter(1, articleId);
            query.setParameter(2, userId);
            result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while deleting like from article_user table", e);
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public int updateLikeInArticle(Long articleId, boolean isLiked) throws SQLException {
        Session session = sessionFactory.openSession();
        int result = -1;
        try (session) {
            session.getTransaction().begin();
            Long numberOfLikes = read(articleId).orElseThrow(() -> new RuntimeException("unknown article")).getNumberOfLikes();
            Query query = session.createQuery("UPDATE Article SET numberOfLikes =:likes WHERE id =: id");
            if (isLiked) {
                query.setParameter("likes", numberOfLikes - 1);
            } else {
                query.setParameter("likes", numberOfLikes + 1);
            }
            query.setParameter("id", articleId);
            result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while update like in article", e);
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public boolean findLike(Long articleId, Long userId) throws SQLException {
        Session session = sessionFactory.openSession();
        boolean result = false;
        try (session) {
            session.getTransaction().begin();
            NativeQuery query = session.createNativeQuery("SELECT * FROM article_user WHERE AU_article_id = ? AND  AU_user_id = ?;");
            query.setParameter(1, articleId);
            query.setParameter(2, userId);
            if (query.uniqueResult() != null) {
                result = true;
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while finding like in article", e);
            session.getTransaction().rollback();
        }
        return result;
    }
}
