package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.model.Lecturer;
import java.util.List;

public interface LecturerService {
    void addLecturer(Lecturer lecturer);
    List<Lecturer> getAllLecturers();
    void updateLecturer(Lecturer lecturer);
    void deleteLecturer(int id);
}

