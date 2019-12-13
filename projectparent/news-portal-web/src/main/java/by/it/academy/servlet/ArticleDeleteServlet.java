package by.it.academy.servlet;

import by.it.academy.service.ArticleService;
import by.it.academy.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/articleDelete")
public class ArticleDeleteServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        articleService.deleteArticle(id);
        resp.sendRedirect(req.getContextPath() + "/articleList");
    }
}
