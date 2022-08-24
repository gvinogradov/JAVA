package dao;

import entity.Student;

import java.util.List;

public interface StudentDAO extends AbstractDAO<Student> {
    public Integer getIdByName(List<Student> students, String name);
}
