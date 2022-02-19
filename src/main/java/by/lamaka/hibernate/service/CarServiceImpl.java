package by.lamaka.hibernate.service;

import by.lamaka.hibernate.dao.CarDAO;
import by.lamaka.hibernate.dao.CarDAOImpl;
import by.lamaka.hibernate.entity.Car;
import by.lamaka.hibernate.entity.CarBrand;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class CarServiceImpl implements CarService {

    CarDAO carDAO;

    public CarServiceImpl() {
        carDAO = new CarDAOImpl();
    }

    @Override
    public void save(Car car) {
        carDAO.save(car);
    }

    @Override
    public void update(Car car) {
        carDAO.update(car);
    }

    @Override
    public void delete(int id) {
        carDAO.delete(id);
    }

    @Override
    public Car getById(int id) {
        return carDAO.getCar(id);
    }

    @Override
    public List<Car> getByBrand(CarBrand carBrand) {
        return carDAO.getByBrand(carBrand);
    }

    @Override
    public List<Car> getByDateCreated(Date date) {
        return carDAO.getByDateCreated(date);
    }

    @Override
    public List<Car> getAllExist() {
        return carDAO.getAllExist();
    }

    @Override
    public Car getByNumber(int number) {
        return carDAO.getByNumber(number);
    }

    @Override
    public List<Car> getAll() {
        return carDAO.getAll();
    }

    @Override
    public void changeExistStatus(int id, boolean status) {
        carDAO.changeExistStatus(id, status);
    }
}
