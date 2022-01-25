package by.lamaka.servlets.dao;

import by.lamaka.servlets.config.ConnectionToDB;
import by.lamaka.servlets.entity.user.Role;
import by.lamaka.servlets.entity.user.Sex;
import by.lamaka.servlets.entity.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements EntityDAO<User> {

    @Override
    public void save(User user) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO users (login, password, description, sex_id, role_id) VALUES (?,?,?,?,?);";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getDescription());
            preparedStatement.setInt(4, user.getSex().toString().equals("MALE") ? 1 : 2);
            preparedStatement.setInt(5, user.getRole().toString().equals("ADMIN") ? 1 : 2);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void update(User user) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE users SET login=?, password=?, descritption=?, sex_id=?, role_id=? WHERE id=?;";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getDescription());
            preparedStatement.setInt(4, user.getSex().toString().equals("MALE") ? 1 : 2);
            preparedStatement.setInt(5, user.getRole().toString().equals("ADMIN") ? 1 : 2);
            preparedStatement.setLong(6, user.getId());

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void delete(long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public User get(long id) throws SQLException, ClassNotFoundException {
        User user = null;
        String sql = "select u.id as id,login,password,description,sex,role from users u " +
                "join role r on r.id = u.role_id " +
                "join sex s on s.id = u.sex_id " +
                "WHERE u.id=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .description(resultSet.getString("description"))
                        .sex(Sex.valueOf(resultSet.getString("sex")))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .build();
            }
        }
        return user;
    }

    public User get(String login, String password) throws SQLException, ClassNotFoundException {
        User user = null;
        String sql = "select u.id as id,login,password,description,sex,role from users u " +
                "join role r on r.id = u.role_id " +
                "join sex s on s.id = u.sex_id " +
                "WHERE login=? AND password=?";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .description(resultSet.getString("description"))
                        .sex(Sex.valueOf(resultSet.getString("sex")))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .build();
            }
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        User user = null;
        List<User> users = new ArrayList<>();
        String sql = "select u.id as id,login,password,description,sex,role from users u " +
                "join role r on r.id = u.role_id " +
                "join sex s on s.id = u.sex_id ";
        try (Connection connection = ConnectionToDB.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .description(resultSet.getString("description"))
                        .sex(Sex.valueOf(resultSet.getString("sex")))
                        .role(Role.valueOf(resultSet.getString("role")))
                        .build();
                users.add(user);
            }
        }
        return users;
    }
}
