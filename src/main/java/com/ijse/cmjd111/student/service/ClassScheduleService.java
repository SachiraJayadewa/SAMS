package com.ijse.cmjd111.student.service;

import com.ijse.cmjd111.student.model.ClassSchedule;
import java.util.List;

public interface ClassScheduleService {
    void addSchedule(ClassSchedule schedule);
    List<ClassSchedule> getAllSchedules();
    void updateSchedule(ClassSchedule schedule);
    void deleteSchedule(int id);
}


