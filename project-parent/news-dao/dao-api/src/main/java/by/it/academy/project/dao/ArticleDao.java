package by.it.academy.project.dao;

import by.it.academy.project.model.Article;
import by.it.academy.project.model.Comment;
import by.it.academy.project.model.User;

import java.sql.SQLException;
import java.util.Set;

public interface ArticleDao extends DAO<Article> {

    Set<User> getUsersWhoLiked(Long articleId) throws SQLException;

    Set<Comment> getAllComments(Long articleId);

    Long addLike(Long article_id, Long user_id) throws SQLException;

    int deleteLike(Long article_id, Long user_id) throws SQLException;

    int updateLikeInArticle(Long article_id, boolean like) throws SQLException;

    boolean findLike(Long article_id, Long user_id) throws SQLException;

}
