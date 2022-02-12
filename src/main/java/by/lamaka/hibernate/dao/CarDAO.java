package by.lamaka.hibernate.dao;

import by.lamaka.hibernate.entity.Car;
import by.lamaka.hibernate.entity.CarBrand;

import java.util.Date;
import java.util.List;

public interface CarDAO {

    void saveCar(Car car);

    void updateCar(Car car);

    void deleteCar(int id);

    Car getCarById(int id);

    List<Car> getCarsByBrand(CarBrand carBrand);

    List<Car> getCarsByDateCreated(Date date);

    List<Car> getAllExistCars();

    Car getCarByNumber(int number);

    List<Car> getAllCars();

    void changeExistStatus(int id, boolean status);

}
