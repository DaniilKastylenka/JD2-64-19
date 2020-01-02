package by.it.academy.project.dao;

import by.it.academy.project.model.Article;

import java.sql.SQLException;

public interface ArticleDao extends DAO<Article> {

    Long addLike(Long article_id, Long user_id) throws SQLException;

    int deleteLike(Long article_id, Long user_id) throws SQLException;

    int updateLikeInArticle(Long article_id, boolean like) throws SQLException;

    boolean findLike(Long article_id, Long user_id) throws SQLException;

}
