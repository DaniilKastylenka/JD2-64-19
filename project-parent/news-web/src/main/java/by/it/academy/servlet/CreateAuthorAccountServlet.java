package by.it.academy.servlet;

import by.it.academy.project.model.Role;
import by.it.academy.project.model.User;
import by.it.academy.project.service.UserService;
import by.it.academy.project.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/createAuthorAccount")

public class CreateAuthorAccountServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/createAuthorAccount.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username").toLowerCase();
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPass");

        boolean hasError = false;
        String error = "";

        if (username.length() == 0 ||
                password == null || password.length() == 0 ||
                repeatPassword == null || repeatPassword.length() == 0) {
            hasError = true;
            error = "empty";
        } else if (!password.equals(repeatPassword)) {
            hasError = true;
            error = "repeat";
        } else if (userService.findUserByUsername(username)) {
            hasError = true;
            error = "user";
        }

        if (hasError) {
            req.setAttribute("errorString", error);
            req.getRequestDispatcher("WEB-INF/jsp/createAuthorAccount.jsp").forward(req, resp);
        } else {
            userService.addUser(new User(username, password, new Role(2, "author")));
            resp.sendRedirect(req.getContextPath() + "/userList");
        }
    }
}
