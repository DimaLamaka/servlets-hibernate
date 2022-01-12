package by.lamaka.servlets.web;

import by.lamaka.servlets.dao.CarDAO;
import by.lamaka.servlets.dao.EntityDAO;
import by.lamaka.servlets.entity.Car;
import by.lamaka.servlets.mapper.RequestBodyMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

@WebServlet(name = "car", value = "/car")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarServlet extends HttpServlet {
    EntityDAO<Car> carDAO;

    @Override
    public void init() throws ServletException {
        carDAO = new CarDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            StringBuilder stringBuilder = new StringBuilder("<html><body>");
            if (request.getParameter("id") == null) {
                carDAO.getAll().forEach(c -> stringBuilder.append(c + "<br>"));
            } else {
                int id = Integer.parseInt(request.getParameter("id"));
                stringBuilder.append(carDAO.get(id));
            }
            stringBuilder.append("</html></body>");
            response.getWriter().print(stringBuilder.toString());
        } catch (SQLException | ClassNotFoundException e) {
            response.sendError(410, e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> paramsFormBody = RequestBodyMapper.map(request);
        String brand = paramsFormBody.get("brand");
        String model = paramsFormBody.get("model");
        int productionYear = Integer.parseInt(paramsFormBody.get("production_year"));
        Car car = new Car(brand, model, productionYear);

        try {
            carDAO.save(car);
        } catch (SQLException | ClassNotFoundException e) {
            response.sendError(410, e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> paramsFormBody = RequestBodyMapper.map(req);
        long id = Long.parseLong(paramsFormBody.get("id"));
        String brand = paramsFormBody.get("brand");
        String model = paramsFormBody.get("model");
        int productionYear = Integer.parseInt(paramsFormBody.get("production_year"));
        Car car = new Car(id, brand, model, productionYear);

        try {
            carDAO.update(car);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(410, e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        try {
            carDAO.delete(id);
        } catch (SQLException | ClassNotFoundException e) {
            resp.sendError(410, e.getMessage());
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "AND" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss"));
        Cookie cookie = new Cookie("lastChanges", dateTime);
        resp.addCookie(cookie);
        super.service(req, resp);
    }
}
