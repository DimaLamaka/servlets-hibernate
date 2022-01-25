package by.lamaka.servlets.web;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(filterName = "CarServletFilter", urlPatterns = "/car")
public class CarServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String admin = req.getHeader("admin");
        String method = req.getMethod();

        if (!method.equalsIgnoreCase("get") && Objects.isNull(admin)) {
            res.sendError(403, "no access rights");
            return;
        }

        chain.doFilter(req, res);
    }
}
