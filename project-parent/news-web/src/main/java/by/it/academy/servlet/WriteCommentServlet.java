package by.it.academy.servlet;

import by.it.academy.project.model.Article;
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

@WebServlet(urlPatterns = "/writeComment")

public class WriteCommentServlet extends HttpServlet {

    private CommentService commentService = CommentServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/article.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Long articleId = Long.valueOf(req.getParameter("articleId"));

        User user = (User) req.getSession().getAttribute("user");
        String text = req.getParameter("text").trim();
        if (user == null) {
            req.setAttribute("text", text);
        }
        if (text.length() < 5) {
            req.getSession().setAttribute("commentText", text);
            req.getSession().setAttribute("errorLength", "length");
            resp.sendRedirect(req.getHeader("referer"));
        } else {
            Comment comment = new Comment(user, text, new Article(articleId));
            commentService.addComment(comment);
            resp.sendRedirect(req.getContextPath() + "/article?articleId=" + articleId);
        }
    }
}
