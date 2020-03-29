package by.it.academy.project.service;

import by.it.academy.project.dto.CommentDto;
import by.it.academy.project.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void addComment(Comment comment);
    void deleteComment(Long id);
    Optional<Comment> findCommentById(Long id);
    void like(Long commentId, Long userId);
    boolean isLiked(Long commentId, Long userId);
    void dislike(Long commentId, Long userId);
    boolean isDisliked(Long commentId, Long userId);
    List<CommentDto> getDtoComments();


}
