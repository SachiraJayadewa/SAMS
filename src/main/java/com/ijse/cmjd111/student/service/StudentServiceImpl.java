package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.dao.StudentDAO;
import com.ijse.cmjd111.student.dao.StudentDAOImpl;
import com.ijse.cmjd111.student.model.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentDAO dao = new StudentDAOImpl();

    @Override
    public void addStudent(Student s) {
        dao.saveStudent(s);
    }

    @Override
    public List<Student> getAllStudents() {
        return dao.getAllStudents();
    }

    @Override
    public void updateStudent(Student s) {
        dao.updateStudent(s);
    }

    @Override
    public void deleteStudent(int id) {
        dao.deleteStudent(id);
    }
}

