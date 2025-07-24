package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.model.Student;
import java.util.List;

public interface StudentService {
    void addStudent(Student s);
    List<Student> getAllStudents();
    void updateStudent(Student s);
    void deleteStudent(int id);
}
