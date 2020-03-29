package by.it.academy.project.dao;

import by.it.academy.project.model.Comment;

import java.sql.SQLException;

public interface CommentDao extends DAO<Comment>{

    void addLike(Long commentId, Long userId) throws SQLException;

    void deleteLike(Long commentId, Long userId) throws SQLException;

    void updateLikeInComment(Long commentId, boolean isLiked) throws SQLException;

    boolean findLike(Long commentId, Long userId) throws SQLException;

    void addDislike(Long commentId, Long userId) throws SQLException;

    void deleteDislike(Long commentId, Long userId) throws SQLException;

    void updateDislikeInComment(Long commentId, boolean isDisliked) throws SQLException;

    boolean findDislike(Long commentId, Long userId) throws SQLException;

}
