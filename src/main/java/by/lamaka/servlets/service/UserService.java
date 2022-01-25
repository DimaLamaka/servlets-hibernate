package by.lamaka.servlets.service;

import by.lamaka.servlets.entity.user.User;
import by.lamaka.servlets.exception.UserListIsEmptyException;
import by.lamaka.servlets.exception.UserNotFoundException;
import by.lamaka.servlets.exception.ValidateException;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    User getUser(String login, String password) throws SQLException, ClassNotFoundException, UserNotFoundException;

    void saveUser(User user) throws SQLException, ClassNotFoundException, ValidateException;

    void updateUser(User user) throws SQLException, ClassNotFoundException, ValidateException;

    void deleteUser(long id) throws SQLException, ClassNotFoundException;

    User getUser(long id) throws SQLException, ClassNotFoundException, UserNotFoundException;

    List<User> getAll() throws SQLException, ClassNotFoundException, UserListIsEmptyException;
}
