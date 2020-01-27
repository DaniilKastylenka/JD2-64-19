package by.it.academy.filter;

import by.it.academy.project.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/deleteUser")

public class DeleteUserFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        User user = (User) req.getSession().getAttribute("user");

        Long id = Long.valueOf(req.getParameter("userId"));

        if (!user.getId().equals(id) && !user.getRole().equals("admin")) {
            res.sendRedirect(req.getContextPath() + "/home");
        } else {
            super.doFilter(req, res, chain);
        }

    }
}
