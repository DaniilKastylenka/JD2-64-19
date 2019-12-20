package by.it.academy.filter;

import by.it.academy.project.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/createArticle", "/myArticles"})

public class ArticlesFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        User user = (User) req.getSession().getAttribute("user");
        if (!user.getRole().equals("author")) {
            res.sendRedirect(req.getContextPath() + "/home");
        } else {
            super.doFilter(req, res, chain);
        }
    }
}
