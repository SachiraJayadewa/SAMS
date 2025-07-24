package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.dao.StudentDAO;
import com.ijse.cmjd111.student.model.Student;

import java.util.List;

public class StudentService {
    private final StudentDAO dao = new StudentDAO();

    public void addStudent(Student s) {
        dao.saveStudent(s);
    }

    public List<Student> getAllStudents() {
        return dao.getAllStudents();
    }

    public void updateStudent(Student s) {
        dao.updateStudent(s);
    }

    public void deleteStudent(int id) {
        dao.deleteStudent(id);
    }
}

