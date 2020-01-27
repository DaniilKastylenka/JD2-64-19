package by.it.academy.project.dao;

import by.it.academy.project.model.Article;

import java.sql.SQLException;

public interface ArticleDao extends DAO<Article> {

    void addLike(Long articleId, Long userId) throws SQLException;

    int deleteLike(Long articleId, Long userId) throws SQLException;

    int updateLikeInArticle(Long articleId, boolean like) throws SQLException;

    boolean findLike(Long articleId, Long userId) throws SQLException;

}
