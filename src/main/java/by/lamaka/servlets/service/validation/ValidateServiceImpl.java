package by.lamaka.servlets.service.validation;

import by.lamaka.servlets.entity.user.User;
import by.lamaka.servlets.exception.ValidateException;

public class ValidateServiceImpl<T> implements ValidateService<User> {
    @Override
    public void validate(User user) throws ValidateException {
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidateException("Wrong login!!!");
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new ValidateException("Wrong password!!!");
        }
    }
}
