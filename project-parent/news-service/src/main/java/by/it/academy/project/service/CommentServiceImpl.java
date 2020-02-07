package by.it.academy.project.service;

import by.it.academy.project.dao.CommentDao;
import by.it.academy.project.dao.CommentDaoImpl;
import by.it.academy.project.dto.CommentDto;
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
            if (isLiked(commentId, userId)) {
                commentDao.deleteLike(commentId, userId);
                commentDao.updateLikeInComment(commentId, true);
                result = "remove like by user with id " + userId;
            } else if (isDisliked(commentId, userId)) {
                commentDao.deleteDislike(commentId, userId);
                commentDao.addLike(commentId, userId);
                commentDao.updateLikeInComment(commentId, false);
                commentDao.updateDislikeInComment(commentId, true);
                result = "remove dislike and add like in comment by user with id " + userId;
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

    @Override
    public boolean isLiked(Long commentId, Long userId) {
        boolean result = false;
        try {
            result = commentDao.findLike(commentId, userId);
        } catch (SQLException e) {
            logger.error("error while checking isLiked", e);
        }
        return result;
    }

    @Override
    public void dislike(Long commentId, Long userId) {
        logger.debug("dislike comment");
        String result;
        try {
            if (isDisliked(commentId, userId)) {
                commentDao.deleteDislike(commentId, userId);
                commentDao.updateDislikeInComment(commentId, true);
                result = "remove dislike by user with id " + userId;
            } else if (isLiked(commentId, userId)) {
                commentDao.deleteLike(commentId, userId);
                commentDao.addDislike(commentId, userId);
                commentDao.updateDislikeInComment(commentId, false);
                commentDao.updateLikeInComment(commentId, true);
                result = "remove like and add dislike in comment by user with id " + userId;
            } else {
                commentDao.addDislike(commentId, userId);
                commentDao.updateDislikeInComment(commentId, false);
                result = "add like by user with id " + userId;
            }
            logger.debug("result{}", result);
        } catch (SQLException e) {
            logger.debug("error while dislike comment", e);
        }
    }

    @Override
    public boolean isDisliked(Long commentId, Long userId) {
        boolean result = false;
        try {
            result = commentDao.findDislike(commentId, userId);
        } catch (SQLException e) {
            logger.error("error while checking isDisliked", e);
        }
        return result;
    }

    @Override
    public List<CommentDto> getDtoComments() {
        logger.debug("get all dto comments");
        List<CommentDto> comments = new ArrayList<>();
        try {
            List<Comment> all = commentDao.getAll();
            for (Comment c : all) {
                comments.add(mapFromCommentIntoCommentDto(c));
            }
        } catch (SQLException e) {
            logger.error("error while getting dto comments", e);
        }
        return comments;
    }

    private CommentDto mapFromCommentIntoCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getUser(),
                comment.getText(),
                comment.getDate(),
                comment.getLikes(),
                comment.getDislikes(),
                comment.getArticle()
        );
    }

    public static CommentServiceImpl getINSTANCE() {
        return INSTANCE;
    }
}
