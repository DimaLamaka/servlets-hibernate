package by.lamaka.hibernate.dao;

import by.lamaka.hibernate.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    Customer getCustomerById(int id);

    List<Customer> getAllCustomers();
}
