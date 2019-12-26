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
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

@WebServlet(urlPatterns = "/createArticle")
public class CreateArticleServlet extends HttpServlet {

    private ArticleService articleService = ArticleServiceImpl.getINSTANCE();

    private SectionService sectionService = SectionServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("sections", sectionService.getSections());
        req.getRequestDispatcher("/WEB-INF/jsp/createArticle.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sectionId = req.getParameter("sectionId");
        String title = req.getParameter("title");
        String text = req.getParameter("text");

        User user = (User) req.getSession().getAttribute("user");

        Optional<Section> optionalSection = sectionService.getSections().stream()
                .filter(section1 -> section1.getId().equals(Long.valueOf(sectionId)))
                .findFirst();
        Section section = optionalSection.orElseThrow(()-> new RuntimeException("no section with id" + sectionId));

        Article article = new Article(null, title, section, user, text);

        articleService.addNewArticle(article);

        resp.sendRedirect(req.getContextPath() + "/myArticles");
    }
}
