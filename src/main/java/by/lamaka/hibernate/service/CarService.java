package by.lamaka.hibernate.service;

import by.lamaka.hibernate.entity.Car;
import by.lamaka.hibernate.entity.CarBrand;

import java.util.Date;
import java.util.List;

public interface CarService {

    void save(Car car);

    void update(Car car);

    void delete(int id);

    Car getById(int id);

    List<Car> getByBrand(CarBrand carBrand);

    List<Car> getByDateCreated(Date date);

    List<Car> getAllExist();

    Car getByNumber(int number);

    List<Car> getAll();

    void changeExistStatus(int id, boolean status);
}
