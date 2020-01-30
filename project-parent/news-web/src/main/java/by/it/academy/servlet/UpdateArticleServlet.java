package by.it.academy.servlet;

import by.it.academy.project.model.Article;
import by.it.academy.project.model.Section;
import by.it.academy.project.model.User;
import by.it.academy.project.service.ArticleService;
import by.it.academy.project.service.ArticleServiceImpl;
import by.it.academy.project.service.SectionService;
import by.it.academy.project.service.SectionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@WebServlet(urlPatterns = "/updateArticle")

public class UpdateArticleServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getINSTANCE();
    private SectionService sectionService = SectionServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long articleId = Long.valueOf(req.getParameter("articleId"));
        Optional<Article> optionalArticle = articleService.findArticleById(articleId);
        Article oldArticle = optionalArticle.orElseThrow(() -> new RuntimeException("no article with id " + articleId));
        req.setAttribute("sectionId", oldArticle.getSection().getId());
        req.setAttribute("title", oldArticle.getTitle());
        req.setAttribute("text", oldArticle.getText());
        req.setAttribute("sections", sectionService.getSections());
        req.getRequestDispatcher("WEB-INF/jsp/updateArticle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long articleId = Long.valueOf(req.getParameter("articleId"));

        String sectionId = req.getParameter("sectionId");
        Section section = sectionService.getSections().stream()
                .filter(section1 -> section1.getId().equals(Integer.valueOf(sectionId)))
                .findFirst().orElseThrow(() -> new RuntimeException("no section with id " + sectionId));

        String title = req.getParameter("title");
        String text = req.getParameter("text");

        Optional<Article> optionalArticle = articleService.findArticleById(articleId);

        Article oldArticle = optionalArticle.orElseThrow(() -> new RuntimeException("no article with id " + articleId));
        Article newArticle = new Article(articleId, section, title, text, oldArticle.getAuthor(), oldArticle.getPublicationDate(), new Date(), oldArticle.getNumberOfLikes());

        articleService.update(newArticle);

        User user = (User) req.getSession().getAttribute("user");
        if (user.getRole().getName().equals("admin")) {
            resp.sendRedirect(req.getContextPath() + "/articleList");
        } else {
            resp.sendRedirect(req.getContextPath() + "/myArticles");
        }
    }

}
