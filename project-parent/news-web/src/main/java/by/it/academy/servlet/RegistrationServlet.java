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

@WebServlet(urlPatterns = "/register")
public class RegistrationServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").toLowerCase();
        String password = req.getParameter("password");
        String repeatPass = req.getParameter("repeatPass");

        String errorMessage = "";
        boolean hasError = false;

        if (username.length() == 0 ||
                password == null || password.length() == 0 ||
                repeatPass == null || repeatPass.length() == 0) {
            hasError = true;
            errorMessage = "empty";
        } else if (userService.findUserByUsername(username)) {
            hasError = true;
            errorMessage = "user";
        } else if (!password.equals(repeatPass)) {
            hasError = true;
            errorMessage = "repeat";
        }

        if (hasError) {
            req.setAttribute("errorString", errorMessage);
            req.setAttribute("username", username);
            req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp").forward(req, resp);
        } else {
            userService.addUser(new User(username, password));
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}