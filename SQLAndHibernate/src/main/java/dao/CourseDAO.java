package dao;

import entity.Course;

import java.util.List;

public interface CourseDAO extends AbstractDAO<Course>{
    public Integer getIdByName(List<Course> courses, String name);
}
