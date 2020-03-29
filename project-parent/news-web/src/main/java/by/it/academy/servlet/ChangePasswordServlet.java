package by.it.academy.servlet;

import by.it.academy.project.model.User;
import by.it.academy.project.security.EncryptUtils;
import by.it.academy.project.service.UserService;
import by.it.academy.project.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    private UserService userService = UserServiceImpl.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/jsp/changePassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");
        String repeatPassword = req.getParameter("repeatPassword");

        User user = (User) req.getSession().getAttribute("user");

        String encryptedOldPassword = EncryptUtils.getSHA256(oldPassword, user.getSalt());

        String error = "";
        boolean hasError = false;

        if (oldPassword == null || oldPassword.length() == 0 ||
                newPassword == null || newPassword.length() == 0 ||
                repeatPassword == null || repeatPassword.length() == 0) {
            hasError = true;
            error = "empty";
        } else if (!encryptedOldPassword.equals(user.getPassword())) {
            hasError = true;
            error = "old";
        } else if (!newPassword.equals(repeatPassword)) {
            hasError = true;
            error = "match";
        }

        if (hasError) {
            req.setAttribute("errorString", error);
            req.getRequestDispatcher("WEB-INF/jsp/changePassword.jsp").forward(req, resp);
        } else {
            String encryptedNewPassword = EncryptUtils.getSHA256(newPassword, user.getSalt());
            user.setPassword(encryptedNewPassword);
            userService.updateUser(user);
            resp.sendRedirect(req.getContextPath() + "/userPage");
        }
    }
}
