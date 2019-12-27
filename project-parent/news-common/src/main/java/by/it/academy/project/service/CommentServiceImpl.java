package by.it.academy.project.service;

import by.it.academy.project.model.Comment;

import java.util.List;
import java.util.Optional;

public class CommentServiceImpl implements CommentService {

    private final static CommentServiceImpl INSTANCE = new CommentServiceImpl();

    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public void updateComment(Comment comment) {

    }

    @Override
    public void deleteComment(Long id) {

    }

    @Override
    public List<Comment> getAllComments() {
        return null;
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        return Optional.empty();
    }

    public static CommentServiceImpl getINSTANCE() {
        return INSTANCE;
    }
}
