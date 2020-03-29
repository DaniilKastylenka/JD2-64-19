package by.it.academy.project.dao;

import by.it.academy.project.model.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDao extends DAO<Article> {

    List<Article> getArticlesBySearchRequest(String request) throws SQLException;

    List<Article> getArticlesBySearchRequestAndUserId(String request, Long id) throws SQLException;

    List<Article> getLimitedNumberOfArticles(int start, int total) throws SQLException;

    int getCountOfArticles() throws SQLException;

    List<Article> getLimitedNumberOfArticlesBySectionId(int start, int total, int sectionId) throws SQLException;

    int getCountOfArticlesBySectionId(int sectionId) throws SQLException;

    List<Article> getLimitedNumberOfArticlesByUserId(int start, int total, Long userId) throws SQLException;

    int getCountOfArticlesByUserId(Long userId) throws SQLException;

    List<Article> getLimitedNumberOfArticlesByUserIdAndSectionId(int start, int total, Long userId, int sectionId) throws SQLException;

    int getCountOfArticlesByUserIdAndSectionId(Long userId, int sectionId) throws SQLException;

    List<Article> getAllBySectionId(int sectionId) throws SQLException;

    void addLike(Long articleId, Long userId) throws SQLException;

    void deleteLike(Long articleId, Long userId) throws SQLException;

    void updateLikeInArticle(Long articleId, boolean isLiked) throws SQLException;

    boolean findLike(Long articleId, Long userId) throws SQLException;

    void addDislike(Long articleId, Long userId) throws SQLException;

    void deleteDislike(Long articleId, Long userId) throws SQLException;

    void updateDislikeInArticle(Long articleId, boolean isDisliked) throws SQLException;

    boolean findDislike(Long articleId, Long userId) throws SQLException;

}
