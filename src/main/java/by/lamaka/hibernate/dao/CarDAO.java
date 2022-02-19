package by.lamaka.hibernate.dao;

import by.lamaka.hibernate.entity.Car;
import by.lamaka.hibernate.entity.CarBrand;

import java.util.Date;
import java.util.List;

public interface CarDAO {

    void save(Car car);

    void update(Car car);

    void delete(int id);

    Car getCar(int id);

    List<Car> getByBrand(CarBrand carBrand);

    List<Car> getByDateCreated(Date date);

    List<Car> getAllExist();

    Car getByNumber(int number);

    List<Car> getAll();

    void changeExistStatus(int id, boolean status);

}
