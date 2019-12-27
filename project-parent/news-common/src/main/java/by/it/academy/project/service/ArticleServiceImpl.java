package by.it.academy.project.service;

import by.it.academy.project.dao.ArticleDao;
import by.it.academy.project.dao.ArticleDaoImpl;
import by.it.academy.project.model.Article;
import by.it.academy.project.model.User;
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
            logger.debug("result", articles);
        } catch (SQLException e) {
            logger.error("error while reading articles", e);
        }
        return articles;
    }

    @Override
    public void addNewArticle(Article article) {
        logger.debug("add new article", article);
        try {
            Long id = articleDao.save(article);
            article.setId(id);
            logger.debug("result", id);
        } catch (SQLException e) {
            logger.error("error while creating article", e);
        }
    }

    @Override
    public void deleteArticle(Long id) {
        logger.debug("delete article", id);
        try {
            int delete = articleDao.delete(id);
            logger.debug("result", delete);
        } catch (SQLException e) {
            logger.error("error while deleting article", e);
        }
    }

    @Override
    public void update(Article article) {
        logger.debug("update article");
        try {
            int result = articleDao.update(article);
            logger.debug("result" + result);
        } catch (SQLException e) {
            logger.error("error while updating");
        }
    }

    @Override
    public Optional<Article> findArticleById(Long id) {
        logger.debug("find article by id", id);
        try {
            Optional<Article> article = articleDao.read(id);
            logger.debug("result", id);
            return article;
        } catch (SQLException e) {
            logger.error("error while finding article by id", e);
        }
        return Optional.empty();
    }

    @Override
    public void like(Long article_id, Long user_id) {

        logger.debug("like article " + article_id);

        String result;

        Article article = findArticleById(article_id)
                .orElseThrow(() -> new RuntimeException("unknown article"));
        try {
            if (articleDao.findLike(article_id, user_id)) {

                articleDao.deleteLike(article_id, user_id);
                articleDao.updateLike(article_id, false);
                result = "remove like";

            } else {
                articleDao.addLike(article_id, user_id);
                articleDao.updateLike(article_id, true);
                result = "add like";

            }
            logger.debug("result " + result);
        } catch (SQLException e) {
            logger.error("error while like " + e);
        }
    }

    public static ArticleService getINSTANCE() {
        return INSTANCE;
    }
}
