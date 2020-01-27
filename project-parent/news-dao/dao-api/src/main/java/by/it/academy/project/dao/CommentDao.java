package by.it.academy.project.dao;

import by.it.academy.project.model.Comment;

import java.sql.SQLException;

public interface CommentDao extends DAO<Comment>{

    void addLike(Long commentId, Long userId) throws SQLException;

    int deleteLike(Long commentId, Long userId) throws SQLException;

    int updateLikeInComment(Long commentId, boolean isLiked) throws SQLException;

    boolean findLike(Long commentId, Long userId) throws SQLException;

}
