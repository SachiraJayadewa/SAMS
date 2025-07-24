package com.ijse.cmjd111.student.dao;

import com.ijse.cmjd111.student.model.Lecturer;
import java.util.List;

public interface LecturerDAO {
    void saveLecturer(Lecturer lecturer);
    List<Lecturer> getAllLecturers();
    void updateLecturer(Lecturer lecturer);
    void deleteLecturer(int id);
}



