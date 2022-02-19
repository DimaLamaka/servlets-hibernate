package by.lamaka.hibernate.web;

import by.lamaka.hibernate.entity.Car;
import by.lamaka.hibernate.entity.CarBrand;
import by.lamaka.hibernate.service.CarService;
import by.lamaka.hibernate.service.CarServiceImpl;
import lombok.AccessLevel;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "hibernate", value = "/hibernate-car")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarServlet extends HttpServlet {
    CarService carService;

    @Override
    public void init() {
        carService = new CarServiceImpl();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String param = "";
        if (request.getParameterNames().hasMoreElements()) {
            param = request.getParameterNames().nextElement();
        }
        List<Car> result = new ArrayList<>();
        switch (param) {
            case "id":
                result.add(carService.getById(Integer.parseInt(request.getParameter(param))));
                break;
            case "number":
                result.add(carService.getByNumber(Integer.parseInt(request.getParameter(param))));
                break;
            case "brand":
                result = carService.getByBrand(CarBrand.valueOf(request.getParameter(param).toUpperCase()));
                break;
            case "date":
                result = carService.getByDateCreated(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter(param)));
                break;
            case "ifExist":
                result = carService.getAllExist();
                break;
            default:
                result = carService.getAll();
        }

        response.getWriter().println(result);

    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Car car = Car.builder()
                .carNumber(Integer.parseInt(request.getParameter("number")))
                .carBrand(CarBrand.valueOf(request.getParameter("brand").toUpperCase()))
                .dateOfManufactureCar(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")))
                .isExistOnWarehouse(Boolean.getBoolean(request.getParameter("ifExist")))
                .build();

        carService.save(car);

    }

    @SneakyThrows
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("changeExist") == null) {
            Car car = Car.builder()
                    .id(Integer.parseInt(request.getParameter("id")))
                    .carNumber(Integer.parseInt(request.getParameter("number")))
                    .carBrand(CarBrand.valueOf(request.getParameter("brand").toUpperCase()))
                    .dateOfManufactureCar(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date")))
                    .isExistOnWarehouse(Boolean.getBoolean(request.getParameter("ifExist")))
                    .build();
            carService.update(car);
        } else if (request.getParameter("changeExist") != null && request.getParameter("id") != null) {
            carService.changeExistStatus(Integer.parseInt(request.getParameter("id")), Boolean.getBoolean(request.getParameter("changeExist")));
        }


    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");

        carService.delete(Integer.parseInt(id));
    }

}
