package com.ijse.cmjd111.student.dao;

import com.ijse.cmjd111.student.model.Student;
import java.util.List;

public interface StudentDAO {
    void saveStudent(Student student);
    List<Student> getAllStudents();
    void updateStudent(Student student);
    void deleteStudent(int id);
}

