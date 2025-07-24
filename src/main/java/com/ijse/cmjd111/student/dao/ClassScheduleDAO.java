package com.ijse.cmjd111.student.dao;

import com.ijse.cmjd111.student.model.ClassSchedule;
import java.util.List;

public interface ClassScheduleDAO {
    void saveClassSchedule(ClassSchedule schedule);
    List<ClassSchedule> getAllSchedules();
    void updateClassSchedule(ClassSchedule schedule);
    void deleteClassSchedule(int id);
}


