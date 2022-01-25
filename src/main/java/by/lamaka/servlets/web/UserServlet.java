package by.lamaka.servlets.web;

import by.lamaka.servlets.entity.user.User;
import by.lamaka.servlets.exception.UserNotFoundException;
import by.lamaka.servlets.service.UserService;
import by.lamaka.servlets.service.UserServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", value = "/user")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServlet extends HttpServlet {
    UserService userService;

    public UserServlet() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/pages/userLogin.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            User user = userService.getUser(login, password);
            if ("ADMIN".equals(user.getRole().toString())) {
                response.sendRedirect("/admin");
            } else {
                request.getRequestDispatcher("WEB-INF/pages/userPage.html").forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException | UserNotFoundException e) {
            response.sendError(500, e.getMessage());
        }
    }
}
