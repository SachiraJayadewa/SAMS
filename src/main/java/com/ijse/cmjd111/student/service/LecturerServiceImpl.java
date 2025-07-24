package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.dao.LecturerDAO;
import com.ijse.cmjd111.student.dao.LecturerDAOImpl;
import com.ijse.cmjd111.student.model.Lecturer;
import com.ijse.cmjd111.student.service.LecturerService;

import java.util.List;

public class LecturerServiceImpl implements LecturerService {
    private final LecturerDAO dao = new LecturerDAOImpl();

    @Override
    public void addLecturer(Lecturer lecturer) {
        dao.saveLecturer(lecturer);
    }

    @Override
    public List<Lecturer> getAllLecturers() {
        return dao.getAllLecturers();
    }

    @Override
    public void updateLecturer(Lecturer lecturer) {
        dao.updateLecturer(lecturer);
    }

    @Override
    public void deleteLecturer(int id) {
        dao.deleteLecturer(id);
    }
}
