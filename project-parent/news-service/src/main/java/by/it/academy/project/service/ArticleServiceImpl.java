package by.it.academy.project.service;

import by.it.academy.project.dao.ArticleDao;
import by.it.academy.project.dao.ArticleDaoImpl;
import by.it.academy.project.model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ArticleServiceImpl implements ArticleService {

    private static final ArticleService INSTANCE = new ArticleServiceImpl();

    private static final ArticleDao articleDao = ArticleDaoImpl.getINSTANCE();

    private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private ArticleServiceImpl() {
    }

    @Override
    public List<Article> getAllArticles() {
        logger.debug("get all articles");
        ArrayList<Article> articles = null;
        try {
            articles = (ArrayList<Article>) articleDao.getAll();
            logger.debug("result {}", articles);
        } catch (SQLException e) {
            logger.error("error while reading articles", e);
        }
        return articles;
    }

    @Override
    public void addNewArticle(Article article) {
        logger.debug("add new article", article);
        try {
            Long id = articleDao.create(article);
            article.setId(id);
            logger.debug("result {}", id);
        } catch (SQLException e) {
            logger.error("error while creating article", e);
        }
    }

    @Override
    public void deleteArticle(Long id) {
        logger.debug("delete article", id);
        try {
            int delete = articleDao.delete(id);
            logger.debug("result {}", delete);
        } catch (SQLException e) {
            logger.error("error while deleting article", e);
        }
    }

    @Override
    public void update(Article article) {
        logger.debug("update article");
        try {
            int result = articleDao.update(article);
            logger.debug("result {}", result);
        } catch (SQLException e) {
            logger.error("error while updating", e);
        }
    }

    @Override
    public Optional<Article> findArticleById(Long id) {
        logger.debug("find article by id", id);
        try {
            Optional<Article> article = articleDao.read(id);
            logger.debug("result {}", id + " " + article);
            return article;
        } catch (SQLException e) {
            logger.error("error while finding article by id", e);
        }
        return Optional.empty();
    }

    @Override
    public void like(Long articleId, Long userId) {

        logger.debug("like article" + articleId);

        String result;

        try {
            if (articleDao.findLike(articleId, userId)) {

                articleDao.deleteLike(articleId, userId);
                articleDao.updateLikeInArticle(articleId, false);
                result = "remove like by user with id " + userId;

            } else {
                articleDao.addLike(articleId, userId);
                articleDao.updateLikeInArticle(articleId, true);
                result = "add like by user with id " + userId;

            }
            logger.debug("result {} ", result);
        } catch (SQLException e) {
            logger.error("error while like ", e);
        }
    }

    public static ArticleService getINSTANCE() {
        return INSTANCE;
    }
}
