package by.it.academy.project.dao;

import by.it.academy.project.model.Article;

import java.sql.SQLException;
import java.util.List;

public interface ArticleDao extends DAO<Article> {

    List<Article> getAllBySectionId(Long id) throws SQLException;

    void addLike(Long articleId, Long userId) throws SQLException;

    int deleteLike(Long articleId, Long userId) throws SQLException;

    int updateLikeInArticle(Long articleId, boolean isLiked) throws SQLException;

    boolean findLike(Long articleId, Long userId) throws SQLException;

    void addDislike(Long articleId, Long userId) throws SQLException;

    int deleteDislike(Long articleId, Long userId) throws SQLException;

    int updateDislikeInArticle(Long articleId, boolean isDisliked) throws SQLException;

    boolean findDislike(Long articleId, Long userId) throws SQLException;

}
