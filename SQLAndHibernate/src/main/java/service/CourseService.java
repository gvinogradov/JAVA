package service;

import dao.CourseDAO;
import entity.Course;
import entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import utils.SessionUtility;

import java.sql.SQLException;
import java.util.List;

public class CourseService extends SessionUtility implements CourseDAO {

    @Override
    public void add(Course course) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.persist(course);
        closeTransactionSession();
    }

    @Override
    public void addAll(List<Course> courses) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        courses.forEach(c -> session.persist(c));
        closeTransactionSession();
    }

    @Override
    public void remove(Course course) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.remove(course);
        closeTransactionSession();
    }

    @Override
    public void update(Course course) throws SQLException {
        openTransactionSession();
        Session session = getSession();
        session.merge(course);
        closeTransactionSession();
    }

    @Override
    public List<Course> getAll() throws SQLException {
        openTransactionSession();
        Session session = getSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Course> courseCriteriaQuery = builder.createQuery(Course.class);
        Root<Course> root = courseCriteriaQuery.from(Course.class);
        courseCriteriaQuery.select(root);
        List<Course> courseList = session.createQuery(courseCriteriaQuery).getResultList();
        closeTransactionSession();
        return courseList;
    }

    @Override
    public Integer getIdByName(List<Course> courses, String name) {
        for (Course course: courses) {
            if (course.getName().equals(name)) {
                return course.getId();
            }
        }
        return null;
    }
}
