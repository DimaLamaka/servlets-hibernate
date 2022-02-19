package by.lamaka.hibernate.service;

import by.lamaka.hibernate.dao.CustomerDAO;
import by.lamaka.hibernate.dao.CustomerDAOImpl;
import by.lamaka.hibernate.entity.Customer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerServiceImpl implements CustomerService {
    CustomerDAO customerDAO;

    public CustomerServiceImpl() {
        customerDAO = new CustomerDAOImpl();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}
