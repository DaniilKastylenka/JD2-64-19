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
import java.util.Optional;

@WebServlet(urlPatterns = "/dislikeArticle")

public class DislikeArticleServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long articleId = Long.valueOf(req.getParameter("articleId"));

        User user = (User) req.getSession().getAttribute("user");

        articleService.dislike(articleId, user.getId());

        Optional<Article> article = articleService.findArticleById(articleId);

        Long dislikes = null;
        Long likes = null;
        boolean isDisliked = false;
        boolean isLiked = false;

        if (article.isPresent()) {
            dislikes = article.get().getDislikes();
            likes = article.get().getLikes();
            isDisliked = articleService.isDisliked(article.get().getId(), user.getId());
            isLiked = articleService.isLiked(article.get().getId(), user.getId());
        }

        String response =
                dislikes + " dislike(s)" + ":" +
                        likes + " like(s)" + ":" +
                        isDisliked + ":" +
                        isLiked;
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(response);

    }

}
