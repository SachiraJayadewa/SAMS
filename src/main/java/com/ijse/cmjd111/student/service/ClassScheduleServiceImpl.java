package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.dao.ClassScheduleDAO;
import com.ijse.cmjd111.student.dao.ClassScheduleDAOImpl;
import com.ijse.cmjd111.student.model.ClassSchedule;

import java.util.List;

public class ClassScheduleServiceImpl implements ClassScheduleService {

    private final ClassScheduleDAO dao = new ClassScheduleDAOImpl();

    @Override
    public void addSchedule(ClassSchedule schedule) {
        dao.saveClassSchedule(schedule);
    }

    @Override
    public List<ClassSchedule> getAllSchedules() {
        return dao.getAllSchedules();
    }

    @Override
    public void updateSchedule(ClassSchedule schedule) {
        dao.updateClassSchedule(schedule);
    }

    @Override
    public void deleteSchedule(int id) {
        dao.deleteClassSchedule(id);
    }
}
