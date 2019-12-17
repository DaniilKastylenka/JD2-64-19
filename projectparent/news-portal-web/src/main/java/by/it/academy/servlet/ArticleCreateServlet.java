package by.it.academy.servlet;

import by.it.academy.model.Section;
import by.it.academy.model.User;
import by.it.academy.service.ArticleService;
import by.it.academy.service.ArticleServiceImpl;
import by.it.academy.model.Article;
import by.it.academy.service.SectionService;
import by.it.academy.service.SectionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/createArticle")
public class ArticleCreateServlet extends HttpServlet {

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
        Section section = sectionService.getSections().stream()
                .filter(section1 -> section1.getId().equals(Long.valueOf(sectionId)))
                .findFirst().get();
        Article article = new Article(null, section, title, text, user, 0L, 0L, new ArrayList<>());
        articleService.addNewArticle(article);
        resp.sendRedirect(req.getContextPath() + "/articleList");
    }
}
