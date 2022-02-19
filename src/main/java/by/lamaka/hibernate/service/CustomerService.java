package by.lamaka.hibernate.service;

import by.lamaka.hibernate.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(int id);

    List<Customer> getAllCustomers();
}
