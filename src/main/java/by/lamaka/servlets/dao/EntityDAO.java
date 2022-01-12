package by.lamaka.servlets.dao;

import java.sql.SQLException;
import java.util.List;

public interface EntityDAO<T> {
    void save(T t) throws SQLException, ClassNotFoundException;

    void update(T t) throws SQLException, ClassNotFoundException;

    void delete(long id) throws SQLException, ClassNotFoundException;

    T get(long id) throws SQLException, ClassNotFoundException;

    List<T> getAll() throws SQLException, ClassNotFoundException;
}
