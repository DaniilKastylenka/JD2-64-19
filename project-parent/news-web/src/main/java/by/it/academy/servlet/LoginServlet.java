package by.it.academy.servlet;

import by.it.academy.project.model.User;
import by.it.academy.project.service.UserService;
import by.it.academy.project.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/login")

public class LoginServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String errMsg = "";
        boolean hasError = false;

        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            hasError = true;
            errMsg = "Username and password should not be empty.";
        } else {
            Optional<User> user = userService.findUserByUsernameAndPassword(username, password);
            if (user.isEmpty()) {
                hasError = true;
                errMsg = "Bad credentials.";
            } else {
                req.getSession().setAttribute("user", user.get());
            }
        }

        if (hasError) {
            req.setAttribute("errorString", errMsg);
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/home");
        }
    }
}
