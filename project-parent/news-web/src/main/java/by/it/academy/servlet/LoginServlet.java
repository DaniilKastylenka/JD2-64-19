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

    private UserService userService;

    public LoginServlet(){
        userService = UserServiceImpl.getINSTANCE();
    }

    public LoginServlet(UserService userService){
        this.userService = userService;
    }

    public static final String LOGIN_JSP = "/WEB-INF/jsp/login.jsp";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String SHOULD_NOT_BE_EMPTY = "Username and password should not be empty.";
    public static final String BAD_CREDENTIALS = "Bad credentials";
    public static final String ERROR_STRING_ATTRIBUTE = "errorString";
    public static final String USER_ATTRIBUTE = "user";
    public static final String ARTICLES = "/articleList?page=1";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);

        String errMsg = "";
        boolean hasError = false;

        if (username == null || password == null || username.length() == 0 || password.length() == 0) {
            hasError = true;
            errMsg = SHOULD_NOT_BE_EMPTY;
        } else {
            Optional<User> user = userService.findUserByUsernameAndPassword(username, password);
            if (user.isEmpty()) {
                hasError = true;
                errMsg = BAD_CREDENTIALS;
            } else {
                req.getSession().setAttribute(USER_ATTRIBUTE, user.get());
            }
        }

        if (hasError) {
            req.setAttribute(ERROR_STRING_ATTRIBUTE, errMsg);
            req.getRequestDispatcher(LOGIN_JSP).forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + ARTICLES);
        }
    }
}
