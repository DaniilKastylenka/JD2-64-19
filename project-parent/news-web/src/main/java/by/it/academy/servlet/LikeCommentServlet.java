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

@WebServlet(urlPatterns = "/likeComment")
public class LikeCommentServlet extends HttpServlet {

    private CommentService commentService = CommentServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long commentId = Long.valueOf(req.getParameter("commentId"));

        User user = (User) req.getSession().getAttribute("user");

        commentService.like(commentId, user.getId());

        Optional<Comment> comment = commentService.findCommentById(commentId);

        Long likes = null;
        Long dislikes = null;
        boolean isLiked = false;
        boolean isDisliked = false;

        if (comment.isPresent()) {
            likes = comment.get().getLikes();
            dislikes = comment.get().getDislikes();
            isLiked = commentService.isLiked(comment.get().getId(), user.getId());
            isDisliked = commentService.isDisliked(comment.get().getId(), user.getId());
        }

        String result =
                likes + " like(s)" + ":" +
                        dislikes + " dislike(s)" + ":" +
                        isLiked + ":" +
                        isDisliked;
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("plain/text");
        resp.getWriter().write(result);

    }
}
