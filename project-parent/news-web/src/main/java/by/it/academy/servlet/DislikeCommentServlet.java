package by.it.academy.servlet;


import by.it.academy.project.model.Comment;
import by.it.academy.project.model.User;
import by.it.academy.project.service.CommentService;
import by.it.academy.project.service.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/dislikeComment")
public class DislikeCommentServlet extends HttpServlet {

    private CommentService commentService = CommentServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long commentId = Long.valueOf(req.getParameter("commentId"));

        User user = (User) req.getSession().getAttribute("user");

        commentService.dislike(commentId, user.getId());

        Optional<Comment> comment = commentService.findCommentById(commentId);

        Long dislikes = null;
        Long likes = null;
        boolean isDisliked = false;
        boolean isLiked = false;

        if (comment.isPresent()) {
            dislikes = comment.get().getDislikes();
            likes = comment.get().getLikes();
            isDisliked = commentService.isDisliked(comment.get().getId(), user.getId());
            isLiked = commentService.isLiked(comment.get().getId(), user.getId());
        }

        String result =
                dislikes + " dislike(s)" + ":" +
                        likes + " like(s)" + ":" +
                        isDisliked + ":" +
                        isLiked;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("plain/text");
        resp.getWriter().write(result);

    }

}
