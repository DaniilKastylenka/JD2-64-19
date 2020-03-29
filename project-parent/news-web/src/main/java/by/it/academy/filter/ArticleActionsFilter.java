package by.it.academy.filter;

import by.it.academy.project.model.Article;
import by.it.academy.project.model.User;
import by.it.academy.project.service.ArticleService;
import by.it.academy.project.service.ArticleServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/deleteArticle", "/updateArticle"})

public class ArticleActionsFilter extends HttpFilter {

    private ArticleService articleService = ArticleServiceImpl.getINSTANCE();

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        Long articleId = Long.valueOf(req.getParameter("articleId"));

        User user = (User) req.getSession().getAttribute("user");

        Optional<Article> optionalArticle = articleService.findArticleById(articleId);

        Article article = optionalArticle.orElseThrow(() -> new RuntimeException("no article with id " + articleId));

        if (user == null || (!user.equals(article.getAuthor())
                && !user.getRole().getName().equals("admin"))) {
            res.sendRedirect(req.getContextPath() + "/articleList?page=1");
        } else {
            super.doFilter(req, res, chain);
        }
    }

}

