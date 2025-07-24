package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.dao.CourseDAO;
import com.ijse.cmjd111.student.model.Course;

import java.util.List;

public class CourseService {
    private final CourseDAO dao = new CourseDAO();

    public void addCourse(Course c) {
        dao.saveCourse(c);
    }

    public List<Course> getAllCourses() {
        return dao.getAllCourses();
    }

    public void updateCourse(Course c) {
        dao.updateCourse(c);
    }

    public void deleteCourse(int id) {
        dao.deleteCourse(id);
    }
}

