package by.it.academy.servlet;

import by.it.academy.project.model.Article;
import by.it.academy.project.model.User;
import by.it.academy.project.service.ArticleService;
import by.it.academy.project.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/searchMyArticles")

public class SearchMyArticlesServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchRequest = req.getParameter("searchRequest");
        User user = (User) req.getSession().getAttribute("user");
        List<Article> articleList = articleService.getArticlesBySearchRequestAndUserId(searchRequest, user.getId());
        req.setAttribute("articleList", articleList);
        req.getRequestDispatcher("/WEB-INF/jsp/myArticles.jsp").forward(req, resp);
    }
}
