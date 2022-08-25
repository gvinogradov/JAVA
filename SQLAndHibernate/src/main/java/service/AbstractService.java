package service;

import dao.AbstractDAO;
import jakarta.persistence.Entity;
import org.hibernate.Session;
import utils.SessionUtility;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractService<T extends Entity> extends SessionUtility implements AbstractDAO<T> {

    public void add(T item) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.persist(item);
        closeTransactionSession();
    }

    public void remove(T item) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(item);
        closeTransactionSession();
    }

    public void update(T item) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.merge(item);
        closeTransactionSession();
    }

    public abstract List<T> getAll() throws SQLException;
}
