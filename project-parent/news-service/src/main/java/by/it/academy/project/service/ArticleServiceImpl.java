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
        List<Article> result = new ArrayList<>();
        try {
            result = articleDao.getAll();
            logger.debug("result {}", result);
        } catch (SQLException e) {
            logger.error("error while reading articles", e);
        }
        return result;
    }

    @Override
    public List<Article> getArticlesBySearchRequest(String request) {
        logger.debug("get articles by search request");
        List<Article> result = new ArrayList<>();
        try {
            result = articleDao.getArticlesBySearchRequest(request);
            logger.debug("result{}", result);
        } catch (SQLException e){
            logger.error("error while getting articles by search request", e);
        }
        return result;
    }

    @Override
    public List<Article> getArticlesBySearchRequestAndUserId(String request, Long id) {
        logger.debug("get articles by search request and user id");
        List<Article> result = new ArrayList<>();
        try{
            result = articleDao.getArticlesBySearchRequestAndUserId(request, id);
            logger.debug("result{}", result);
        } catch (SQLException e){
            logger.error("error while getting articles by search request and user id", e);
        }
        return result;
    }

    @Override
    public List<Article> getLimitedNumberOfArticles(int start, int total) {
        logger.debug("get limited number articles");
        List<Article> result = new ArrayList<>();
        try {
            result = articleDao.getLimitedNumberOfArticles(start, total);
            logger.debug("result {}", result);
        } catch (SQLException e) {
            logger.error("error while reading articles", e);
        }
        return result;
    }

    @Override
    public int getCountOfPages(int countOfArticlesOnPage) {
        logger.debug("get count of pages");
        int result = 0;
        try {
            int countOfArticles = articleDao.getCountOfArticles();
            if (countOfArticles % countOfArticlesOnPage == 0) {
                result = countOfArticles / countOfArticlesOnPage;
            } else {
                result = (countOfArticles / countOfArticlesOnPage) + 1;
            }
        } catch (SQLException e) {
            logger.error("error while getting count of articles");
        }
        return result;
    }

    @Override
    public List<Article> getLimitedNumberOfArticlesBySectionId(int start, int total, int sectionId) {
        logger.debug("get limited number articles by section id");
        List<Article> result = new ArrayList<>();
        try {
            result = articleDao.getLimitedNumberOfArticlesBySectionId(start, total, sectionId);
            logger.debug("result {}", result);
        } catch (SQLException e) {
            logger.error("error while reading articles", e);
        }
        return result;
    }

    @Override
    public int getCountOfPagesWithArticlesBySectionId(int countOfArticlesOnPage, int sectionId) {
        logger.debug("get count of pages");
        int result = 0;
        try {
            int countOfArticles = articleDao.getCountOfArticlesBySectionId(sectionId);
            if (countOfArticles % countOfArticlesOnPage == 0) {
                result = countOfArticles / countOfArticlesOnPage;
            } else {
                result = (countOfArticles / countOfArticlesOnPage) + 1;
            }
        } catch (SQLException e) {
            logger.error("error while getting count of articles");
        }
        return result;
    }

    @Override
    public List<Article> getLimitedNumberOfArticlesByUserId(int start, int total, Long userId) {
        logger.debug("get limited number articles by user id");
        List<Article> result = new ArrayList<>();
        try {
            result = articleDao.getLimitedNumberOfArticlesByUserId(start, total, userId);
            logger.debug("result {}", result);
        } catch (SQLException e) {
            logger.error("error while reading articles", e);
        }
        return result;
    }

    @Override
    public int getCountOfPagesWithArticlesByUserId(int countOfArticlesOnPage, Long userId) {
        logger.debug("get count of pages");
        int result = 0;
        try {
            int countOfArticles = articleDao.getCountOfArticlesByUserId(userId);
            if (countOfArticles % countOfArticlesOnPage == 0) {
                result = countOfArticles / countOfArticlesOnPage;
            } else {
                result = (countOfArticles / countOfArticlesOnPage) + 1;
            }
        } catch (SQLException e) {
            logger.error("error while getting count of articles");
        }
        return result;
    }

    @Override
    public List<Article> getLimitedNumberOfArticlesByUserIdAndSectionId(int start, int total, Long userId, int sectionId) {
        logger.debug("get limited number articles by user id and section id");
        List<Article> result = new ArrayList<>();
        try {
            result = articleDao.getLimitedNumberOfArticlesByUserIdAndSectionId(start, total, userId, sectionId);
            logger.debug("result {}", result);
        } catch (SQLException e) {
            logger.error("error while reading articles", e);
        }
        return result;
    }

    @Override
    public int getCountOfPagesWithArticlesByUserIdAndSectionId(int countOfArticlesOnPage, Long userId, int sectionId) {
        logger.debug("get count of pages");
        int result = 0;
        try {
            int countOfArticles = articleDao.getCountOfArticlesByUserIdAndSectionId(userId, sectionId);
            if (countOfArticles % countOfArticlesOnPage == 0) {
                result = countOfArticles / countOfArticlesOnPage;
            } else {
                result = (countOfArticles / countOfArticlesOnPage) + 1;
            }
        } catch (SQLException e) {
            logger.error("error while getting count of articles");
        }
        return result;
    }

    @Override
    public List<Article> getAllBySectionId(int sectionId) {
        logger.debug("get all articles by section");
        List<Article> result = new ArrayList<>();
        try {
            result = articleDao.getAllBySectionId(sectionId);
            logger.debug("result{}", result);
        } catch (SQLException e) {
            logger.error("error while getting all articles by section id", e);
        }
        return result;
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
            if (isLiked(articleId, userId)) {

                articleDao.deleteLike(articleId, userId);
                articleDao.updateLikeInArticle(articleId, true);
                result = "remove like by user with id " + userId;

            } else if (isDisliked(articleId, userId)) {

                articleDao.deleteDislike(articleId, userId);
                articleDao.addLike(articleId, userId);
                articleDao.updateLikeInArticle(articleId, false);
                articleDao.updateDislikeInArticle(articleId, true);
                result = "remove dislike and add like by user with id " + userId;

            } else {
                articleDao.addLike(articleId, userId);
                articleDao.updateLikeInArticle(articleId, false);
                result = "add like by user with id " + userId;

            }
            logger.debug("result {} ", result);
        } catch (SQLException e) {
            logger.error("error while like article ", e);
        }
    }

    @Override
    public boolean isLiked(Long articleId, Long userId) {
        boolean result = false;
        try {
            result = articleDao.findLike(articleId, userId);
        } catch (SQLException e) {
            logger.error("error while checking isLiked");
        }
        return result;
    }

    @Override
    public void dislike(Long articleId, Long userId) {

        logger.debug("dislike article" + articleId);

        String result;

        try {
            if (isDisliked(articleId, userId)) {

                articleDao.deleteDislike(articleId, userId);
                articleDao.updateDislikeInArticle(articleId, true);
                result = "remove dislike by user with id " + userId;

            } else if (isLiked(articleId, userId)) {

                articleDao.deleteLike(articleId, userId);
                articleDao.addDislike(articleId, userId);
                articleDao.updateDislikeInArticle(articleId, false);
                articleDao.updateLikeInArticle(articleId, true);
                result = "remove like and add dislike by user with id " + userId;

            } else {
                articleDao.addDislike(articleId, userId);
                articleDao.updateDislikeInArticle(articleId, false);
                result = "add dislike by user with id " + userId;

            }
            logger.debug("result {} ", result);
        } catch (SQLException e) {
            logger.error("error while dislike article ", e);
        }
    }

    @Override
    public boolean isDisliked(Long articleId, Long userId) {
        boolean result = false;
        try {
            result = articleDao.findDislike(articleId, userId);
        } catch (SQLException e) {
            logger.error("error while checking isDisliked");
        }
        return result;
    }

    public static ArticleService getINSTANCE() {
        return INSTANCE;
    }
}
