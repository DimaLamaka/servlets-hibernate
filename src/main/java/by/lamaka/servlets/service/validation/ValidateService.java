package by.lamaka.servlets.service.validation;

import by.lamaka.servlets.exception.ValidateException;

public interface ValidateService<T> {
    void validate(T t) throws ValidateException;
}
