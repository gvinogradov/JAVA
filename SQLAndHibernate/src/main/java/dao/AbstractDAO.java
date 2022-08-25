package dao;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<T> {
    void add(T item) throws SQLException;

    void addAll(List<T> items) throws SQLException;

    void remove(T item) throws SQLException;

    void update(T item) throws SQLException;

    List<T> getAll() throws SQLException;
}
