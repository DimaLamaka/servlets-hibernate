package by.lamaka.hibernate.dao;

import by.lamaka.hibernate.config.HibernateUtil;
import by.lamaka.hibernate.entity.Car;
import by.lamaka.hibernate.entity.CarBrand;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    @Override
    public void saveCar(Car car) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();
        }
    }

    @Override
    public void updateCar(Car car) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(car);
            transaction.commit();
        }
    }

    @Override
    public void deleteCar(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Car> query = session.createQuery("delete Car where id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public Car getCarById(int id) {
        Car car = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            car = session.get(Car.class, id);
            transaction.commit();
        }
        return car;
    }

    @Override
    public List<Car> getCarsByBrand(CarBrand carBrand) {
        List<Car> cars = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Car> query = session.createQuery("from Car where carBrand=:carBrand", Car.class);
            query.setParameter("carBrand", carBrand);
            cars = query.getResultList();
            transaction.commit();
        }
        return cars;
    }

    @Override
    public List<Car> getCarsByDateCreated(Date date) {
        List<Car> cars = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Car> query = session.createQuery("from Car where dateOfManufactureCar=:date", Car.class);
            query.setParameter("date", date);
            cars = query.getResultList();
            transaction.commit();
        }
        return cars;
    }

    @Override
    public List<Car> getAllExistCars() {
        List<Car> cars = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Car> query = session.createQuery("from Car where isExistOnWarehouse=true", Car.class);
            cars = query.getResultList();
            transaction.commit();
        }
        return cars;
    }

    @Override
    public Car getCarByNumber(int number) {
        Car car = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Car> query = session.createQuery("from Car where carNumber=:number", Car.class);
            query.setParameter("number", number);
            car = query.getSingleResult();
            transaction.commit();
        }
        return car;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Car> query = session.createQuery("from Car", Car.class);
            cars = query.getResultList();
            transaction.commit();
        }
        return cars;
    }

    @Override
    public void changeExistStatus(int id, boolean status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Car> query = session.createQuery("update Car set isExistOnWarehouse=:exist where id=:id");
            query.setParameter("exist", status);
            query.setParameter("id", id);
            query.executeUpdate();

            transaction.commit();
        }
    }
}
