package by.it.academy.servlets;

import by.it.academy.service.ArticleService;
import by.it.academy.service.ArticleServiceImpl;
import by.it.academy.type.Article;
import by.it.academy.type.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/articleCreate")
public class ArticleCreateServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/articleCreate.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String author = req.getParameter("author");
        Article article = new Article(null, title, text, new Author(author), 0L, 0L, new ArrayList<>());
        articleService.addNewArticle(article);
        resp.sendRedirect(req.getContextPath() + "/articleList");
    }
}
