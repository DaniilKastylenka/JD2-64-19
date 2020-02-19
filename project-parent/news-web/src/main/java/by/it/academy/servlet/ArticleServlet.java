package by.it.academy.servlet;

import by.it.academy.project.dto.CommentDto;
import by.it.academy.project.model.Article;
import by.it.academy.project.model.User;
import by.it.academy.project.service.ArticleService;
import by.it.academy.project.service.ArticleServiceImpl;
import by.it.academy.project.service.CommentService;
import by.it.academy.project.service.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/article")

public class ArticleServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getINSTANCE();
    private CommentService commentService = CommentServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long articleId = Long.valueOf(req.getParameter("articleId"));
        User user = (User) req.getSession().getAttribute("user");

        List<CommentDto> allComments = commentService.getDtoComments();

        if (user != null) {
            for (CommentDto c : allComments) {
                c.setLiked(commentService.isLiked(c.getId(), user.getId()));
                c.setDisliked(commentService.isDisliked(c.getId(), user.getId()));
            }
        }

        req.setAttribute("commentList", allComments);
        Article article = articleService.findArticleById(articleId).orElseThrow(() -> new RuntimeException("no article with id " + articleId));
        if (user != null) {
            req.setAttribute("isLiked", articleService.isLiked(article.getId(), user.getId()));
            req.setAttribute("isDisliked", articleService.isDisliked(article.getId(), user.getId()));
        }
        req.setAttribute("article", article);
        req.getRequestDispatcher("/WEB-INF/jsp/article.jsp").forward(req, resp);
        req.getSession().removeAttribute("errorString");
        req.getSession().removeAttribute("errorLength");
        req.getSession().removeAttribute("commentText");
    }
}
