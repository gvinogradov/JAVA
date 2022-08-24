package service;

import dao.StudentDAO;
import entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import utils.SessionUtility;

import java.sql.SQLException;
import java.util.List;

public class StudentService extends SessionUtility implements StudentDAO {
    @Override
    public void add(Student student) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.persist(student);
        closeTransactionSession();
    }

    @Override
    public void addAll(List<Student> students) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        students.forEach(s -> session.persist(s));
        closeTransactionSession();
    }

    @Override
    public void remove(Student student) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(student);
        closeTransactionSession();
    }

    @Override
    public void update(Student student) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.merge(student);
        closeTransactionSession();
    }

    @Override
    public List<Student> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> studentCriteriaQuery = builder.createQuery(Student.class);
        Root<Student> root = studentCriteriaQuery.from(Student.class);
        studentCriteriaQuery.select(root);
        List<Student> studentList = session.createQuery(studentCriteriaQuery).getResultList();
        closeTransactionSession();
        return studentList;
    }

    @Override
    public Integer getIdByName(List<Student> students, String name) {
        for (Student student: students) {
            if (student.getName().equals(name)) {
                return student.getId();
            }
        }
        return null;
    }
}
