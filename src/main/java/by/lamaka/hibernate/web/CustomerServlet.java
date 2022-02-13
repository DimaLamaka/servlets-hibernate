package by.lamaka.hibernate.web;

import by.lamaka.hibernate.entity.Customer;
import by.lamaka.hibernate.service.CustomerService;
import by.lamaka.hibernate.service.CustomerServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerServlet extends HttpServlet {
    CustomerService service;

    @Override
    public void init() throws ServletException {
        service = new CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Customer> customers = new ArrayList<>();
        if (req.getParameter("id") != null) {
            customers.add(service.getCustomerById(Integer.parseInt(req.getParameter("id"))));
        } else {
            customers = service.getAllCustomers();
        }
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("/WEB-INF/pages/customers.jsp").forward(req, resp);
    }
}
