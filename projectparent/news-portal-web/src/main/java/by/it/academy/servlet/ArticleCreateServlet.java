package by.it.academy.servlet;

import by.it.academy.model.User;
import by.it.academy.service.ArticleService;
import by.it.academy.service.ArticleServiceImpl;
import by.it.academy.model.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/createArticle")
public class ArticleCreateServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/createArticle.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String section = req.getParameter("section");
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        Article article = new Article(null, section, title, text, new User("petya", "123", "author"), 0L, 0L, new ArrayList<>());
        articleService.addNewArticle(article);
        resp.sendRedirect(req.getContextPath() + "/articleList");
    }
}
