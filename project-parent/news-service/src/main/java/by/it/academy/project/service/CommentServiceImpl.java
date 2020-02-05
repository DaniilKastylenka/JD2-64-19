package by.it.academy.project.service;

import by.it.academy.project.dao.CommentDao;
import by.it.academy.project.dao.CommentDaoImpl;
import by.it.academy.project.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    private final static CommentServiceImpl INSTANCE = new CommentServiceImpl();
    private final static CommentDao commentDao = CommentDaoImpl.getINSTANCE();
    private final static Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Override
    public void addComment(Comment comment) {
        logger.debug("add comment");
        try {
            Long id = commentDao.create(comment);
            comment.setId(id);
            logger.debug("result " + id);
        } catch (SQLException e) {
            logger.error("error while adding comment ", e);
        }
    }

    @Override
    public void updateComment(Comment comment) {
        logger.debug("update comment");
        try {
            int result = commentDao.update(comment);
            logger.debug("result " + result);
        } catch (SQLException e) {
            logger.debug("error while updating comment ", e);
        }
    }

    @Override
    public void deleteComment(Long id) {
        logger.debug("delete comment");
        try {
            int result = commentDao.delete(id);
            logger.debug("result " + result);
        } catch (SQLException e) {
            logger.error("error while deleting comment ", e);
        }
    }

    @Override
    public List<Comment> getAllComments() {
        logger.debug("get all comments");
        ArrayList<Comment> result = null;
        try {
            result = (ArrayList<Comment>) commentDao.getAll();
            logger.debug("result " + result);
        } catch (SQLException e) {
            logger.error("error while getting all comments ", e);
        }
        return result;
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        logger.debug("find comment by id");
        Optional<Comment> result = Optional.empty();
        try {
            result = commentDao.read(id);
            logger.debug("result " + result);
        } catch (SQLException e) {
            logger.error("error while finding comment ", e);
        }
        return result;
    }

    @Override
    public void like(Long commentId, Long userId) {
        logger.debug("like comment");
        String result;
        try {
            if (commentDao.findLike(commentId, userId)) {
                commentDao.deleteLike(commentId, userId);
                commentDao.updateLikeInComment(commentId, true);
                result = "remove like by user with id " + userId;
            } else {
                commentDao.addLike(commentId, userId);
                commentDao.updateLikeInComment(commentId, false);
                result = "add like by user with id " + userId;
            }
            logger.debug("result{}", result);
        } catch (SQLException e) {
            logger.debug("error while like comment", e);
        }

    }

    public static CommentServiceImpl getINSTANCE() {
        return INSTANCE;
    }
}
