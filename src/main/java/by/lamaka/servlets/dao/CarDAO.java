package by.lamaka.servlets.dao;

import by.lamaka.servlets.config.ConnectionToDB;
import by.lamaka.servlets.entity.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements EntityDAO<Car> {
    @Override
    public void save(Car car) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO car (brand,model,production_year) values (?,?,?)";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getProductionYear());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(Car car) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE car SET brand=?, model=?,production_year=? WHERE id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setInt(3, car.getProductionYear());
            preparedStatement.setLong(4, car.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM car WHERE id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Car get(long id) throws SQLException, ClassNotFoundException {
        Car car = null;
        String sql = "SELECT * FROM car WHERE id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                car = Car.builder()
                        .id(resultSet.getLong("id"))
                        .brand(resultSet.getString("brand"))
                        .model(resultSet.getString("model"))
                        .productionYear(resultSet.getInt("production_year"))
                        .build();
            }
        }
        return car;
    }

    @Override
    public List<Car> getAll() throws SQLException, ClassNotFoundException {
        Car car = null;
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM car";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                car = Car.builder()
                        .id(resultSet.getLong("id"))
                        .brand(resultSet.getString("brand"))
                        .model(resultSet.getString("model"))
                        .productionYear(resultSet.getInt("production_year"))
                        .build();
                cars.add(car);
            }
        }
        return cars;
    }
}
