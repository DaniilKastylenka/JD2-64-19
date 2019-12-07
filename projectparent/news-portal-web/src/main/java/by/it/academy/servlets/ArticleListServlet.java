package by.it.academy.servlets;

import by.it.academy.service.ArticleService;
import by.it.academy.service.ArticleServiceImpl;
import by.it.academy.type.Article;
import by.it.academy.type.Comment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/articleList")
public class ArticleListServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Article> allArticles = articleService.getAllArticles();
        req.setAttribute("articleList", allArticles);

        req.getRequestDispatcher("/WEB-INF/articleList.jsp")
                .forward(req,resp);
    }
}
