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
    public void saveCar(Car car) {
        carDAO.saveCar(car);
    }

    @Override
    public void updateCar(Car car) {
        carDAO.updateCar(car);
    }

    @Override
    public void deleteCar(int id) {
        carDAO.deleteCar(id);
    }

    @Override
    public Car getCarById(int id) {
        return carDAO.getCarById(id);
    }

    @Override
    public List<Car> getCarsByBrand(CarBrand carBrand) {
        return carDAO.getCarsByBrand(carBrand);
    }

    @Override
    public List<Car> getCarsByDateCreated(Date date) {
        return carDAO.getCarsByDateCreated(date);
    }

    @Override
    public List<Car> getAllExistCars() {
        return carDAO.getAllExistCars();
    }

    @Override
    public Car getCarByNumber(int number) {
        return carDAO.getCarByNumber(number);
    }

    @Override
    public List<Car> getAllCars() {
        return carDAO.getAllCars();
    }

    @Override
    public void changeExistStatus(int id, boolean status) {
        carDAO.changeExistStatus(id, status);
    }
}
