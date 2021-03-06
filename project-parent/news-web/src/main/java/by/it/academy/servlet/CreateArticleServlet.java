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

        Integer sectionId = Integer.valueOf(req.getParameter("sectionId"));

        String error = "";
        boolean hasError = false;
        if (sectionId < 1 || sectionId > 7) {
            error = "choose section";
            hasError = true;
        }

        String title = req.getParameter("title");
        String text = req.getParameter("text");

        if (!hasError) {

            User author = (User) req.getSession().getAttribute("user");

            Section section = sectionService.getSections().stream()
                    .filter(section1 -> section1.getId().equals(sectionId))
                    .findFirst().orElseThrow(() -> new RuntimeException("no section with id " + sectionId));

            Article article = new Article(null, section, title, text, author);

            articleService.addNewArticle(article);

            resp.sendRedirect(req.getContextPath() + "/articleList?page=1");
        } else {
            req.setAttribute("sections", sectionService.getSections());
            req.setAttribute("error", error);
            req.setAttribute("title", title);
            req.setAttribute("text", text);
            req.getRequestDispatcher("/WEB-INF/jsp/createArticle.jsp")
                    .forward(req, resp);
        }
    }
}
