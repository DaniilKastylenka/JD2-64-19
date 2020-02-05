package by.it.academy.project.dao;

import by.it.academy.project.model.Article;
import by.it.academy.project.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
public class ArticleDaoImpl implements ArticleDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private static ArticleDaoImpl INSTANCE = new ArticleDaoImpl();

    @Override
    public Long create(Article article) {
        Session session = sessionFactory.openSession();
        Long result = null;
        try {
            session.getTransaction().begin();
            result = (Long) session.save(article);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while creating article", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Optional<Article> read(Long id) {
        Session session = sessionFactory.openSession();
        Optional<Article> result = Optional.empty();
        try (session) {
            result = Optional.ofNullable(session.get(Article.class, id));
        } catch (HibernateException e) {
            log.error("error while reading", e);
        }
        return result;
    }

    @Override
    public int update(Article article) {
        Optional<Article> optionalArticle = read(article.getId());
        Session session = sessionFactory.openSession();
        int result = 0;
        try {
            session.getTransaction().begin();
            if (optionalArticle.isPresent()) {
                session.update(article);
                session.getTransaction().commit();
                result = 1;
            }
        } catch (HibernateException e) {
            log.error("error while updating", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int delete(Long id) {
        Optional<Article> optionalArticle = read(id);
        Session session = sessionFactory.openSession();
        int result = 0;
        try {
            session.getTransaction().begin();
            if (optionalArticle.isPresent()) {
                session.delete(new Article(id));
                session.getTransaction().commit();
                result = 1;
            }
        } catch (HibernateException e) {
            log.error("error while deleting", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Article> getAll() {
        Session session = sessionFactory.openSession();
        List<Article> result = new ArrayList<>();
        try (session) {
            Query<Article> query = session.createQuery("FROM Article ORDER BY publicationDate DESC", Article.class);
            result = query.list();
        } catch (HibernateException e) {
            log.error("error while getting all", e);
        }
        return result;
    }

    @Override
    public void addLike(Long articleId, Long userId) {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();
            NativeQuery query = session.createNativeQuery("INSERT INTO user_article VALUES (?,?)");
            query.setParameter(1, articleId);
            query.setParameter(2, userId);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while adding like to article_user table", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public int deleteLike(Long articleId, Long userId) {
        Session session = sessionFactory.openSession();
        int result = 0;
        try {
            session.getTransaction().begin();
            NativeQuery query = session.createNativeQuery("DELETE FROM user_article WHERE Article_A_id = ? AND User_U_id = ?");
            query.setParameter(1, articleId);
            query.setParameter(2, userId);
            result = query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error("error while deleting like from article_user table", e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public int updateLikeInArticle(Long articleId, boolean isLiked) {
        Session session = sessionFactory.openSession();
        int result = 0;
        try {
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
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean findLike(Long articleId, Long userId) {
        Session session = sessionFactory.openSession();
        boolean result = false;
        try (session) {
            NativeQuery query = session.createNativeQuery("SELECT * FROM user_article WHERE Article_A_id = ? AND  User_U_id = ?;");
            query.setParameter(1, articleId);
            query.setParameter(2, userId);
            if (query.uniqueResult() != null) {
                result = true;
            }
        } catch (HibernateException e) {
            log.error("error while finding like in article", e);
        }
        return result;
    }

    public static ArticleDao getINSTANCE() {
        return INSTANCE;
    }

}
