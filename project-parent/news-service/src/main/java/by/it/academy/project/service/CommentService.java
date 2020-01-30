package by.it.academy.project.service;

import by.it.academy.project.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void addComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(Long id);
    List<Comment> getAllComments();
    Optional<Comment> findCommentById(Long id);
    void like(Long commentId, Long userId);


}
