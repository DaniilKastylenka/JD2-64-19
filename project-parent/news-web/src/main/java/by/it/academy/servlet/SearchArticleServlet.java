package by.it.academy.servlet;

import by.it.academy.project.model.Article;
import by.it.academy.project.service.ArticleService;
import by.it.academy.project.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/searchArticle")
public class SearchArticleServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchRequest = req.getParameter("searchRequest");
        List<Article> articleList = articleService.getArticlesBySearchRequest(searchRequest);
        req.setAttribute("articleList", articleList);
        req.getRequestDispatcher("/WEB-INF/jsp/articleList.jsp").forward(req, resp);
    }
}
