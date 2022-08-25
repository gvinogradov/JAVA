package service;

import dao.TeacherDAO;
import entity.Student;
import entity.Teacher;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import utils.SessionUtility;

import java.sql.SQLException;
import java.util.List;

public class TeacherService extends SessionUtility implements TeacherDAO {
    @Override
    public void add(Teacher teacher) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.persist(teacher);
        closeTransactionSession();
    }

    @Override
    public void addAll(List<Teacher> teachers) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        teachers.forEach(t -> session.persist(t));
        closeTransactionSession();
    }

    @Override
    public void remove(Teacher teacher) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(teacher);
        closeTransactionSession();
    }

    @Override
    public void update(Teacher teacher) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.merge(teacher);
        closeTransactionSession();
    }

    @Override
    public List<Teacher> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Teacher> teacherCriteriaQuery = builder.createQuery(Teacher.class);
        Root<Teacher> root = teacherCriteriaQuery.from(Teacher.class);
        teacherCriteriaQuery.select(root);
        List<Teacher> studentList = session.createQuery(teacherCriteriaQuery).getResultList();
        closeTransactionSession();
        return studentList;
    }
}
