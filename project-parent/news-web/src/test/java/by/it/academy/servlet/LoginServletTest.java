package by.it.academy.servlet;

import by.it.academy.project.model.Role;
import by.it.academy.project.model.User;
import by.it.academy.project.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)

public class LoginServletTest {

    public static final String USER_NAME = "TEST_USER_NAME";
    public static final String USER_PASSWORD = "TEST_USER_PASSWORD";
    public static final Long USER_ID = 101L;
    public static final Role USER_ROLE = new Role("USER_ROLE");
    public static final String CONTEXT_PATH = "";

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher dispatcher;
    @Mock
    private UserService userService;
    @Mock
    private HttpSession session;

    private LoginServlet loginServlet;

    @Before
    public void setUp() {
        Mockito.when(request.getRequestDispatcher(LoginServlet.LOGIN_JSP)).thenReturn(dispatcher);
        loginServlet = new LoginServlet(userService);
    }

    @Test
    public void testDoGetShouldForwardToLoginJSP() throws Exception {
        // when
        loginServlet.doGet(request, response);

        //then
        Mockito.verify(dispatcher).forward(request, response);

    }

    public void ShouldSetErrorMessageInRequestAndForward() throws ServletException, IOException {
        //when
        loginServlet.doPost(request, response);

        //then
        Mockito.verify(request).setAttribute(LoginServlet.ERROR_STRING_ATTRIBUTE, LoginServlet.SHOULD_NOT_BE_EMPTY);
        Mockito.verify(request).setAttribute(LoginServlet.USER_ATTRIBUTE, null);
        Mockito.verify(dispatcher).forward(request, response);

    }

    public void ShouldSetUserToSessionAndForwardToHome() throws ServletException, IOException {
        //given
        Mockito.when(request.getParameter(LoginServlet.USER_ATTRIBUTE)).thenReturn(USER_NAME);
        Mockito.when(request.getParameter(LoginServlet.PASSWORD)).thenReturn(USER_PASSWORD);
        User expectedUser = new User(USER_ID, USER_NAME, USER_PASSWORD, USER_ROLE);
        Mockito.when(userService.findUserByUsernameAndPassword(USER_NAME, USER_PASSWORD))
                .thenReturn(Optional.of(expectedUser));
        Mockito.when(request.getSession()).thenReturn(session);
        Mockito.when(request.getContextPath()).thenReturn(CONTEXT_PATH);

        //when
        loginServlet.doPost(request, response);

        //then
        Mockito.verify(session).setAttribute(LoginServlet.USER_ATTRIBUTE, Optional.of(expectedUser).get());
        Mockito.verify(dispatcher, Mockito.never()).forward(request, response);
        Mockito.verify(response).sendRedirect(CONTEXT_PATH + LoginServlet.HOME);

    }

}
