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

@WebServlet(urlPatterns = "/deleteUser")

public class DeleteUserServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userId = Long.valueOf(req.getParameter("userId"));

        userService.deleteUser(userId);

        User currentUser = (User) req.getSession().getAttribute("user");

        if (userId.equals(currentUser.getId())) {
            resp.sendRedirect(req.getContextPath() + "/logout");
        } else {
            resp.sendRedirect(req.getContextPath() + "/userList");
        }
    }

}
