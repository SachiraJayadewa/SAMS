package com.ijse.cmjd111.student.dao;

import com.ijse.cmjd111.student.model.Attendance;
import java.util.List;
import java.util.Map;

public interface AttendanceDAO {
    void markAttendance(Attendance attendance);
    List<Attendance> getAttendanceBySchedule(int scheduleId);
    List<Attendance> getAttendanceByStudent(int studentId);
    void deleteAttendance(int attendanceId);
    Map<String, Integer> getAttendanceSummaryBySchedule(int scheduleId);  // new method
}



