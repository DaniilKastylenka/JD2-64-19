package by.it.academy.filter;

import by.it.academy.project.model.Article;
import by.it.academy.project.model.User;
import by.it.academy.project.service.ArticleService;
import by.it.academy.project.service.ArticleServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/deleteArticle", "/updateArticle"})

public class ArticleActionsFilter extends HttpFilter {

    private ArticleService articleService = ArticleServiceImpl.getINSTANCE();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        Long articleId = Long.valueOf(req.getParameter("articleId"));

        User user = (User) req.getSession().getAttribute("user");
        Article article = articleService.findArticleById(articleId);

        if (!user.equals(article.getAuthor())) {
            res.sendRedirect(req.getContextPath() + "/articleList");
        } else {
            super.doFilter(req, res, chain);
        }

    }
}
