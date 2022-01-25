package by.lamaka.servlets.web;

import by.lamaka.servlets.entity.user.Role;
import by.lamaka.servlets.entity.user.Sex;
import by.lamaka.servlets.entity.user.User;
import by.lamaka.servlets.exception.ValidateException;
import by.lamaka.servlets.service.UserService;
import by.lamaka.servlets.service.UserServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    UserService userService;

    public AdminServlet() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/adminPage.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("sex"));
        User user = User.builder()
                .login(request.getParameter("login"))
                .password(request.getParameter("password"))
                .description(request.getParameter("description"))
                .sex(Sex.valueOf(request.getParameter("sex").trim()))
                .role(Role.valueOf(request.getParameter("role").trim()))
                .build();
        try {
            userService.saveUser(user);
            response.sendRedirect("/admin");
        } catch (SQLException | ClassNotFoundException | ValidateException e) {
            response.sendError(500, e.getMessage());
        }
    }
}
