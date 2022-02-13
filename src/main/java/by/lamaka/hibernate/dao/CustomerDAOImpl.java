package by.lamaka.hibernate.dao;

import by.lamaka.hibernate.config.HibernateUtil;
import by.lamaka.hibernate.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            customer = session.get(Customer.class, id);
            transaction.commit();
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Customer> query = session.createQuery("from Customer", Customer.class);
            customers = query.getResultList();
            transaction.commit();
        }
        return customers;
    }
}
