package by.lamaka.servlets.service;

import by.lamaka.servlets.dao.UserDAO;
import by.lamaka.servlets.entity.user.User;
import by.lamaka.servlets.exception.UserListIsEmptyException;
import by.lamaka.servlets.exception.UserNotFoundException;
import by.lamaka.servlets.exception.ValidateException;
import by.lamaka.servlets.service.validation.ValidateService;
import by.lamaka.servlets.service.validation.ValidateServiceImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    UserDAO userDAO;
    ValidateService<User> validateService;

    public UserServiceImpl() {
        userDAO = new UserDAO();
        validateService = new ValidateServiceImpl<>();
    }

    @Override
    public User getUser(String login, String password) throws SQLException, ClassNotFoundException, UserNotFoundException {
        User user = userDAO.get(login, password);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public void saveUser(User user) throws SQLException, ClassNotFoundException, ValidateException {
        try {
            getUser(user.getLogin(), user.getPassword());
        } catch (UserNotFoundException e) {
            validateService.validate(user);
            userDAO.save(user);
        }
    }

    @Override
    public void updateUser(User user) throws SQLException, ClassNotFoundException, ValidateException {
        validateService.validate(user);
        userDAO.update(user);
    }

    @Override
    public void deleteUser(long id) throws SQLException, ClassNotFoundException {
        userDAO.delete(id);
    }

    @Override
    public User getUser(long id) throws SQLException, ClassNotFoundException, UserNotFoundException {
        User user = userDAO.get(id);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException, UserListIsEmptyException {
        List<User> users = userDAO.getAll();
        if (users.isEmpty()) {
            throw new UserListIsEmptyException("User list is empty");
        }
        return null;
    }
}
