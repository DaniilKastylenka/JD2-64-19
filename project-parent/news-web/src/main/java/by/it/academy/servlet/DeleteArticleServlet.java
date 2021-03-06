package by.it.academy.servlet;

import by.it.academy.project.model.User;
import by.it.academy.project.service.ArticleService;
import by.it.academy.project.service.ArticleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteArticle")
public class DeleteArticleServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("articleId"));
        articleService.deleteArticle(id);

        User user = (User) req.getSession().getAttribute("user");
        if (user.getRole().getName().equals("author")) {
            resp.sendRedirect(req.getContextPath() + "/myArticles?page=1");
        } else {
            resp.sendRedirect(req.getContextPath() + "/articleList?page=1");
        }
    }
}
